package by.jwd.xmlparser.logic.parser.dom;

import by.jwd.xmlparser.bean.Answer;
import by.jwd.xmlparser.bean.Question;
import by.jwd.xmlparser.bean.Test;
import by.jwd.xmlparser.bean.TestGroup;
import by.jwd.xmlparser.logic.parser.TestTagName;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class DOMTestParser {

    private final static String TIME_FORMATTER="HH:mm:ss";
    private final static String ATTRIBUTE_ID="id";

    public static Set<Test> getTests(String path) throws IOException, SAXException {

        DOMParser domParser = new DOMParser();
        domParser.parse(path);
        Document document = domParser.getDocument();
        Element root = document.getDocumentElement();

        Set<Test> setTests = new HashSet<>();
        Test test;
        TestGroup testGroup;
        Set<Question> setQuestions;
        Question question;
        Set<Answer> setAnswers;
        Answer answer;

        NodeList testNodeList = root.getElementsByTagName(TestTagName.TEST.toString().toLowerCase());

        for (int i = 0; i < testNodeList.getLength(); i++) {
            test = new Test();
            Element testItem = (Element) testNodeList.item(i);
            test.setId(testItem.getAttribute(ATTRIBUTE_ID));
            test.setTitle(getSingleChild(testItem, TestTagName.TITLE).getTextContent().trim());
            test.setKey(Integer.parseInt(getSingleChild(testItem, TestTagName.KEY).getTextContent().trim()));
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern(TIME_FORMATTER);
            test.setTime(LocalTime.parse(getSingleChild(testItem, TestTagName.TIME).getTextContent().trim(), formatterTime));

            testGroup = new TestGroup();
            Element testGroupElem = getSingleChild(testItem, TestTagName.TEST_GROUP);
            testGroup.setId(Integer.parseInt(testGroupElem.getAttribute(ATTRIBUTE_ID)));
            testGroup.setGroupTitle(getSingleChild(testGroupElem, TestTagName.GROUP_TITLE).getTextContent().trim());
            test.setTestGroup(testGroup);

            NodeList questItems = testItem.getElementsByTagName(TestTagName.QUESTION.toString().toLowerCase());
            setQuestions = new HashSet<>();

            for (int j = 0; j < questItems.getLength(); j++) {

                Element qItem = (Element) questItems.item(j);
                question = new Question();
                question.setId(qItem.getAttribute(ATTRIBUTE_ID));
                question.setQuestionItem(getSingleChild(qItem, TestTagName.QUESTION_TEXT).getTextContent().trim());


                NodeList answItems = qItem.getElementsByTagName(TestTagName.ANSWER.toString().toLowerCase());
                setAnswers = new HashSet<>();

                for (int k = 0; k < answItems.getLength(); k++) {

                    Element aItem = (Element) answItems.item(k);
                    answer = new Answer();
                    answer.setId(aItem.getAttribute(ATTRIBUTE_ID));
                    answer.setAnswerItem(getSingleChild(aItem, TestTagName.ANSWER_TEXT).getTextContent().trim());
                    answer.setResult(Boolean.valueOf(getSingleChild(aItem, TestTagName.RESULT).getTextContent().trim()));

                    setAnswers.add(answer);
                    question.setAnswers(setAnswers);
                }
                setQuestions.add(question);
                test.setQuestions(setQuestions);
            }
            setTests.add(test);


        }

        return setTests;

    }

    private static Element getSingleChild(Element element, TestTagName testTagName) {
        String childName=testTagName.toString().toLowerCase().replace("_","-");
        NodeList elementsByTagName = element.getElementsByTagName(childName);
        return (Element) elementsByTagName.item(0);

    }

}

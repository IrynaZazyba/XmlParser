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

    public static Set<Test> getTests(String path) throws IOException, SAXException {

        DOMParser domParser = new DOMParser();
        domParser.parse(path);
        Document document = domParser.getDocument();
        Element root = document.getDocumentElement();

        Set<Test> setTests = new HashSet<>();
        Test test = null;
        TestGroup testGroup = null;
        Set<Question> setQuestions = null;
        Question question = null;
        Set<Answer> setAnswers = null;
        Answer answer = null;

        NodeList testNodeList = root.getElementsByTagName(TestTagName.TEST.toString().toLowerCase());

        for (int i = 0; i < testNodeList.getLength(); i++) {
            test = new Test();
            Element testItem = (Element) testNodeList.item(i);
            test.setId(testItem.getAttribute("id"));
            test.setTitle(getSingleChild(testItem, "title").getTextContent().trim());
            test.setKey(Integer.parseInt(getSingleChild(testItem, "key").getTextContent().trim()));
            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern(TIME_FORMATTER);
            test.setTime(LocalTime.parse(getSingleChild(testItem, "time").getTextContent().trim(), formatterTime));

            testGroup = new TestGroup();
            Element testGroupElem = getSingleChild(testItem, "test-group");
            testGroup.setId(Integer.parseInt(testGroupElem.getAttribute("id")));
            testGroup.setGroupTitle(getSingleChild(testGroupElem, "group-title").getTextContent().trim());
            test.setTestGroup(testGroup);

            NodeList questItems = testItem.getElementsByTagName("question");
            setQuestions = new HashSet<>();

            for (int j = 0; j < questItems.getLength(); j++) {

                Element qItem = (Element) questItems.item(j);
                question = new Question();
                question.setId(qItem.getAttribute("id"));
                question.setQuestionItem(getSingleChild(qItem, "question-text").getTextContent().trim());


                NodeList answItems = qItem.getElementsByTagName("answer");
                setAnswers = new HashSet<>();

                for (int k = 0; k < answItems.getLength(); k++) {

                    Element aItem = (Element) answItems.item(k);
                    answer = new Answer();
                    answer.setId(aItem.getAttribute("id"));
                    answer.setAnswerItem(getSingleChild(aItem, "answer-text").getTextContent().trim());
                    answer.setResult(Boolean.valueOf(getSingleChild(aItem, "result").getTextContent().trim()));

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

    private static Element getSingleChild(Element element, String childName) {
        NodeList elementsByTagName = element.getElementsByTagName(childName);
        return (Element) elementsByTagName.item(0);

    }

}

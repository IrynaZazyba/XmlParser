package by.jwd.xmlparser.logic.parser.sax;

import by.jwd.xmlparser.bean.Answer;
import by.jwd.xmlparser.bean.Question;
import by.jwd.xmlparser.bean.Test;
import by.jwd.xmlparser.bean.TestGroup;
import by.jwd.xmlparser.logic.parser.TestTagName;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class TestSaxHandler extends DefaultHandler {

    private final static String DATE_FORMAT="yyyy-MM-dd";
    private final static String TIME_FORMAT="HH:mm:ss";
    private final static String ATTRIBUTE_ID="id";


    private Set<Test> tests = new HashSet<>();
    private Set<Question> questions;
    private Set<Answer> answers;
    private Test test;
    private TestGroup testGroup;
    private Question question;
    private Answer answer;
    private StringBuilder text;

    public Set<Test> getTests() {
        return tests;
    }


    public void startDocument() throws SAXException {
    }

    public void endDocument() throws SAXException {
    }

    public void startElement(String uri, String localName,
                             String qName, Attributes attributes) throws SAXException {

        text = new StringBuilder();
        if (qName.equals("test")) {
            test = new Test();
            test.setId(attributes.getValue(ATTRIBUTE_ID));
        } else if (qName.equals("questions")) {
            questions = new HashSet<>();
        } else if (qName.equals("question")) {
            question = new Question();
            question.setId(attributes.getValue(ATTRIBUTE_ID));
        } else if (qName.equals("answers")) {
            answers = new HashSet<>();
        } else if (qName.equals("answer")) {
            answer = new Answer();
            answer.setId(attributes.getValue(ATTRIBUTE_ID));
        } else if (qName.equals("test-group")) {
            testGroup = new TestGroup();
            testGroup.setId(Integer.parseInt(attributes.getValue(ATTRIBUTE_ID)));

        }
    }

    public void characters(char[] buffer, int start, int length) {
        text.append(buffer, start, length);
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {
        TestTagName tagName;
        if (qName.equals("tc:tests")) {
            tagName = TestTagName.valueOf(TestTagName.TESTS.toString());
        } else {
            tagName = TestTagName.valueOf(qName.toUpperCase().replace("-", "_"));
        }
        switch (tagName) {
            case TITLE:
                test.setTitle(text.toString());
                break;
            case KEY:
                test.setKey(Integer.parseInt(text.toString()));
                break;
            case TIME:
                DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern(TIME_FORMAT);
                LocalTime time = LocalTime.parse(text, formatterTime);
                test.setTime(time);
                break;
            case DELETED_AT:
                DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(DATE_FORMAT);
                LocalDate date = LocalDate.parse(text, formatterDate);
                test.setDeletedAt(date);
                break;
            case TEST:
                tests.add(test);
                test = null;
                break;
            case QUESTIONS:
                test.setQuestions(questions);
                questions = null;
                break;
            case QUESTION_TEXT:
                question.setQuestionItem(text.toString());
                break;
            case QUESTION:
                questions.add(question);
                question = null;
                break;
            case ANSWERS:
                question.setAnswers(answers);
                answers = null;
                break;
            case ANSWER_TEXT:
                answer.setAnswerItem(text.toString());
                break;
            case RESULT:
                answer.setResult(Boolean.valueOf(text.toString()));
                break;
            case ANSWER:
                answers.add(answer);
                answer = null;
                break;
            case GROUP_TITLE:
                testGroup.setGroupTitle(text.toString());
                break;
            case TEST_GROUP:
                test.setTestGroup(testGroup);
                testGroup = null;
                break;
        }
    }
}

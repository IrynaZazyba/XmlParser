package by.jwd.xmlparser.dao.parser.stax;

import by.jwd.xmlparser.bean.Answer;
import by.jwd.xmlparser.bean.Question;
import by.jwd.xmlparser.bean.Test;
import by.jwd.xmlparser.bean.TestGroup;
import by.jwd.xmlparser.dao.parser.TestTagName;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class StaxTestParser {

    private static final String TIME_FORMAT="HH:mm:ss";
    private final static String ATTRIBUTE_ID="id";


    public static Set<Test> process(XMLStreamReader reader) throws XMLStreamException {

        Set<Test> tests = new HashSet<>();
        Test test = null;
        TestGroup testGroup = null;
        Set<Question> questions = null;
        Question question = null;
        Set<Answer> answers = null;
        Answer answer = null;
        TestTagName elementName = null;

        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    elementName = TestTagName.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
                    switch (elementName) {
                        case TEST:
                            test = new Test();
                            String id = reader.getAttributeValue(null, ATTRIBUTE_ID);
                            test.setId(id);
                            break;
                        case TEST_GROUP:
                            testGroup = new TestGroup();
                            int testGroupId =
                                    Integer.parseInt(reader.getAttributeValue(null, ATTRIBUTE_ID));
                            testGroup.setId(testGroupId);
                            break;
                        case QUESTIONS:
                            questions=new HashSet<>();
                            break;
                        case QUESTION:
                            question=new Question();
                            String questionId = reader.getAttributeValue(null, ATTRIBUTE_ID);
                            question.setId(questionId);
                            break;
                        case ANSWERS:
                            answers=new HashSet<>();
                            break;
                        case ANSWER:
                            answer=new Answer();
                            String answerId = reader.getAttributeValue(null, ATTRIBUTE_ID);
                            answer.setId(answerId);
                            break;

                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    String text = reader.getText().trim();
                    if (text.isEmpty()) {
                        break;
                    }
                    switch (elementName) {
                        case TITLE:
                            test.setTitle(text);
                            break;
                        case KEY:
                            test.setKey(Integer.parseInt(text));
                            break;
                        case TIME:
                            DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern(TIME_FORMAT);
                            LocalTime time = LocalTime.parse(text, formatterTime);
                            test.setTime(time);
                            break;
                        case GROUP_TITLE:
                            testGroup.setGroupTitle(text);
                            break;
                        case QUESTION_TEXT:
                            question.setQuestionItem(text);
                            break;
                        case ANSWER_TEXT:
                            answer.setAnswerItem(text);
                            break;
                        case RESULT:
                            answer.setResult(Boolean.valueOf(text));
                            break;

                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    elementName = TestTagName.valueOf(reader.getLocalName().toUpperCase().replace("-", "_"));
                    switch (elementName) {
                        case TEST:
                            tests.add(test);
                            test=null;
                            break;
                        case TEST_GROUP:
                            test.setTestGroup(testGroup);
                            break;
                        case RESULT:
                            answers.add(answer);
                            answer=null;
                            break;
                        case ANSWERS:
                            question.setAnswers(answers);
                            answers=null;
                            break;
                        case QUESTION:
                            questions.add(question);
                            question=null;
                            break;
                        case QUESTIONS:
                            test.setQuestions(questions);
                            questions=null;
                            break;
                    }
            }
        }

        return tests;
    }
}

package by.jwd.xmlparser.dao.parser;

public enum TestTagName {

    TEST, TITLE, KEY, TIME, TEST_GROUP, QUESTIONS, GROUP_TITLE, QUESTION, QUESTION_TEXT, ANSWERS, ANSWER, RESULT,
    ANSWER_TEXT, DELETED_AT, TESTS;

    public static String getLowerCase(TestTagName testTagName) {

       return testTagName.toString().toLowerCase().replace("_", "-");
    }
}

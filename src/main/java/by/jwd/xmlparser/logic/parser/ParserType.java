package by.jwd.xmlparser.logic.parser;

public enum ParserType {

    SAX, STAX, DOM;

    public static ParserType getParserType(String type) {
        switch (type) {
            case "sax":
                return SAX;
            case "stax":
                return STAX;
            case "dom":
                return DOM;
            default:
                throw new EnumConstantNotPresentException(ParserType.class, type);

        }
    }


}

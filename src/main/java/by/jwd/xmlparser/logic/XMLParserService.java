package by.jwd.xmlparser.logic;

import by.jwd.xmlparser.bean.Test;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Set;

public interface XMLParserService {

    Set<Test> parse(String path, ParserType parserType) throws SAXException, IOException, XMLStreamException;
}

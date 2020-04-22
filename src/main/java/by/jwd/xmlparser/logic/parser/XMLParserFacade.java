package by.jwd.xmlparser.logic.parser;

import by.jwd.xmlparser.bean.Test;
import by.jwd.xmlparser.logic.XmlValidatorService;
import by.jwd.xmlparser.logic.factory.ServiceFactory;
import by.jwd.xmlparser.logic.parser.dom.DOMTestParser;
import by.jwd.xmlparser.logic.parser.sax.TestSaxHandler;
import by.jwd.xmlparser.logic.parser.stax.StaxTestParser;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class XMLParserFacade {

    private final static String XSD_SCHEMA_LOCATION = "/xsd/tests.xsd";
    private Set<Test> testSet;

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private XmlValidatorService xmlValidatorService = serviceFactory.getXmlValidatorService();


    public XMLParserFacade() {
    }

    public Set<Test> parse(String path, ParserType parserType) throws SAXException, IOException, XMLStreamException {

        boolean validate = xmlValidatorService.validate(path, XSD_SCHEMA_LOCATION);

        if (validate) {

            switch (parserType) {
                case DOM:
                    testSet = DOMTestParser.getTests(path);
                    break;
                case SAX:
                    XMLReader reader = XMLReaderFactory.createXMLReader();
                    TestSaxHandler handler = new TestSaxHandler();
                    reader.setContentHandler(handler);
                    reader.parse(new InputSource(path));
                    testSet = handler.getTests();
                    break;
                case STAX:
                    XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
                    InputStream inputStream = new FileInputStream(path);
                    XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
                    testSet = StaxTestParser.process(xmlStreamReader);
                    break;
            }
        }

        return testSet;
    }
}

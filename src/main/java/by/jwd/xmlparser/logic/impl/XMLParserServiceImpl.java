package by.jwd.xmlparser.logic.impl;

import by.jwd.xmlparser.bean.Test;
import by.jwd.xmlparser.dao.DAOFactory;
import by.jwd.xmlparser.dao.XmlTestDAO;
import by.jwd.xmlparser.logic.ParserType;
import by.jwd.xmlparser.logic.XMLParserService;
import by.jwd.xmlparser.logic.XmlValidatorService;
import by.jwd.xmlparser.logic.factory.ServiceFactory;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Set;

public class XMLParserServiceImpl implements XMLParserService {

    private final static String XSD_SCHEMA_LOCATION = "/xsd/tests.xsd";

    public XMLParserServiceImpl() {
    }

    @Override
    public Set<Test> parse(String fileLocation, ParserType parserType) throws SAXException, IOException, XMLStreamException {

        Set<Test> testSet;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        XmlValidatorService xmlValidatorService = serviceFactory.getXmlValidatorService();

        boolean validate = xmlValidatorService.validate(fileLocation, XSD_SCHEMA_LOCATION);

        if (!validate) {
            return null;
        }

        DAOFactory daoFactory = DAOFactory.getInstance();

        XmlTestDAO xmlDAOParser = null;
        switch (parserType) {
            case DOM:
                xmlDAOParser = daoFactory.getXmlTestDOMDao();
                break;
            case SAX:
                xmlDAOParser = daoFactory.getXmlTestSaxDao();
                break;
            case STAX:
                xmlDAOParser = daoFactory.getXmlTestStaxDao();
                break;
        }

        testSet = xmlDAOParser.getAllTests(fileLocation);


        return testSet;
    }
}

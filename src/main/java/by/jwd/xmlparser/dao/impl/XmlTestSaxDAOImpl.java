package by.jwd.xmlparser.dao.impl;

import by.jwd.xmlparser.bean.Test;
import by.jwd.xmlparser.dao.XmlTestDAO;
import by.jwd.xmlparser.dao.parser.sax.TestSaxHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class XmlTestSaxDAOImpl implements XmlTestDAO {

    @Override
    public Set<Test> getAllTests(String path) throws IOException, SAXException {
        XMLReader reader = XMLReaderFactory.createXMLReader();
        TestSaxHandler handler = new TestSaxHandler();
        reader.setContentHandler(handler);
        reader.parse(new InputSource(path));
        return handler.getTests();
    }
}

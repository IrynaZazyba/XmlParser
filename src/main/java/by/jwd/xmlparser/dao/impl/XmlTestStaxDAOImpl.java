package by.jwd.xmlparser.dao.impl;

import by.jwd.xmlparser.bean.Test;
import by.jwd.xmlparser.dao.XmlTestDAO;
import by.jwd.xmlparser.dao.parser.stax.StaxTestParser;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class XmlTestStaxDAOImpl implements XmlTestDAO {

    @Override
    public Set<Test> getAllTests(String path) throws IOException, SAXException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        InputStream inputStream = new FileInputStream(path);
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(inputStream);
        return StaxTestParser.process(xmlStreamReader);
    }
}

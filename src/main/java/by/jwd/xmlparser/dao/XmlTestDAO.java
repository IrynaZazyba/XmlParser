package by.jwd.xmlparser.dao;

import by.jwd.xmlparser.bean.Test;
import org.xml.sax.SAXException;

import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Set;

public interface XmlTestDAO {

    Set<Test> getAllTests(String path) throws IOException, SAXException, XMLStreamException;
}

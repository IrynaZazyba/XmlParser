package by.jwd.xmlparser.logic.impl;

import by.jwd.xmlparser.logic.XmlValidatorService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlValidatorServiceImpl implements XmlValidatorService {

    private static final Logger logger = LogManager.getLogger();
    private static final String XML_SCHEMA="http://www.w3.org/2001/XMLSchema";

    public XmlValidatorServiceImpl(){}

    @Override
    public boolean validate(String path, String xsdSchemaName) {

        SchemaFactory factory = SchemaFactory.newInstance(XML_SCHEMA);
        File fileLocation = new File(path);

        try {
            Schema schema = factory.newSchema(XmlValidatorServiceImpl.class.getResource(xsdSchemaName));
            Validator validator= schema.newValidator();
            Source source = new StreamSource(fileLocation);
            validator.validate(source);
            return true;
        } catch (SAXException | IOException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            return false;
        }

    }
}

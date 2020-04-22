package by.jwd.xmlparser.logic.factory;

import by.jwd.xmlparser.logic.UploadFileService;
import by.jwd.xmlparser.logic.XMLParserService;
import by.jwd.xmlparser.logic.XmlValidatorService;
import by.jwd.xmlparser.logic.impl.UploadFileServiceImpl;
import by.jwd.xmlparser.logic.impl.XMLParserServiceImpl;
import by.jwd.xmlparser.logic.impl.XmlValidatorServiceImpl;

public class ServiceFactory {

    private final static ServiceFactory instance = new ServiceFactory();

    private final UploadFileService uploadFileService = new UploadFileServiceImpl();
    private final XmlValidatorService xmlValidatorService = new XmlValidatorServiceImpl();
    private final XMLParserServiceImpl xmlParserService=new XMLParserServiceImpl();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UploadFileService getUploadFileService() {
        return uploadFileService;
    }

    public XmlValidatorService getXmlValidatorService() {
        return xmlValidatorService;
    }

    public XMLParserService getXmlParserService(){
        return xmlParserService;
    }
}

package by.jwd.xmlparser.dao;

import by.jwd.xmlparser.dao.impl.XMLTestDOMDAOImpl;
import by.jwd.xmlparser.dao.impl.XmlTestSaxDAOImpl;
import by.jwd.xmlparser.dao.impl.XmlTestStaxDAOImpl;

public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    private XmlTestDAO xmlTestSaxDAO = new XmlTestSaxDAOImpl();
    private XmlTestDAO xmlTestStaxDAO = new XmlTestStaxDAOImpl();
    private XmlTestDAO xmlTestDOMDAO = new XMLTestDOMDAOImpl();


    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public XmlTestDAO getXmlTestSaxDao() {
        return xmlTestSaxDAO;
    }

    public XmlTestDAO getXmlTestStaxDao() {
        return xmlTestStaxDAO;
    }

    public XmlTestDAO getXmlTestDOMDao() {
        return xmlTestDOMDAO;
    }
}

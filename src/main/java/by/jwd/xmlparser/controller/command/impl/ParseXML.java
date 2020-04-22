package by.jwd.xmlparser.controller.command.impl;

import by.jwd.xmlparser.bean.Test;
import by.jwd.xmlparser.controller.JspPageName;
import by.jwd.xmlparser.controller.command.Command;
import by.jwd.xmlparser.logic.UploadFileService;
import by.jwd.xmlparser.logic.UploadServiceException;
import by.jwd.xmlparser.logic.factory.ServiceFactory;
import by.jwd.xmlparser.logic.parser.ParserType;
import by.jwd.xmlparser.logic.parser.XMLParserFacade;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Set;


public class ParseXML implements Command {

    private final static Logger logger = LogManager.getLogger();

    private final static String XML_PARSER_TYPE = "type";
    private final static String REQUEST_ATTRIBUTE_SET_TESTS = "tests";
    private final static String REQUEST_ATTRIBUTE_INVALID_XML = "invalid_xml_message";
    private final static String INVALID_XML_MESSAGE = "Your XML file has not passed validation. Upload a valid file.";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String type = request.getParameter(XML_PARSER_TYPE);
        String realPath = request.getServletContext().getRealPath("");

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UploadFileService uploadFileService = serviceFactory.getUploadFileService();

        String fileName;
        try {
            fileName = uploadFileService.uploadFile(realPath, request.getParts());

            XMLParserFacade xmlParserFacade = new XMLParserFacade();
            Set<Test> tests = xmlParserFacade.parse(fileName, ParserType.getParserType(type));

            if (tests != null) {

                request.setAttribute(REQUEST_ATTRIBUTE_SET_TESTS, tests);
                request.getRequestDispatcher(JspPageName.RESULT_JSP).forward(request, response);

            } else {
                request.setAttribute(REQUEST_ATTRIBUTE_INVALID_XML, INVALID_XML_MESSAGE);
                request.getRequestDispatcher(JspPageName.INDEX_JSP).forward(request, response);
            }


        } catch (UploadServiceException | SAXException | XMLStreamException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            response.sendRedirect(JspPageName.INDEX_JSP);
        }


    }
}

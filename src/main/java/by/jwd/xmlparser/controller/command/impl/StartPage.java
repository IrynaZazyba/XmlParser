package by.jwd.xmlparser.controller.command.impl;

import by.jwd.xmlparser.controller.JspPageName;
import by.jwd.xmlparser.controller.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StartPage implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect(JspPageName.INDEX_JSP);

    }
}

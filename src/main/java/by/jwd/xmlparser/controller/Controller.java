package by.jwd.xmlparser.controller;

import by.jwd.xmlparser.controller.command.Command;
import by.jwd.xmlparser.controller.command.CommandProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/xml-parser", name = "FrontController")
@MultipartConfig

public class Controller extends HttpServlet {

    private static final long serialVersionUID = 2114054049422733828L;
    private static final String REQUEST_PARAMETER="command";

    public Controller(){
        super();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(JspPageName.INDEX_JSP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter(REQUEST_PARAMETER);
        CommandProvider commandProvider = CommandProvider.getInstance();
        Command command = commandProvider.getCommand(commandName);
        command.execute(req, resp);
    }

}

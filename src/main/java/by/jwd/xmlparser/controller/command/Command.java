package by.jwd.xmlparser.controller.command;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public interface Command {

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;




}
package com.epam.web.controller;

import com.epam.web.command.ActionCommand;
import com.epam.web.factory.ActionFactory;
import com.epam.web.requestContent.SessionRequestContent;
import com.epam.web.resource.ConfigurationManager;
import com.epam.web.resource.MessageManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    private static final String INDEX_PAGE_PATH = "path.page.index";
    private static final String NULLPAGE = "nullpage";
    private static final String NULLPAGE_MSG = "message.nullpage";
    private static final Logger logger = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
        //SessionRequestContent requestContent = new SessionRequestContent(request);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page;
        ActionFactory client = new ActionFactory();
        SessionRequestContent requestContent = new SessionRequestContent(request);
        ActionCommand command = client.defineCommand(requestContent);
        page = command.execute(requestContent);
        requestContent.insertValues(request);
        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty(INDEX_PAGE_PATH);
            request.getSession().setAttribute(NULLPAGE, MessageManager.getProperty(NULLPAGE_MSG));
            response.sendRedirect(request.getContextPath() + page);
        }
    }
}

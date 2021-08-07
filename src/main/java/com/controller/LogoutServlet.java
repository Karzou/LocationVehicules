package com.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Vanconingsloo Kevin
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(LogoutServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel de la methode doGet servletLogout");
        }

        HttpSession session = request.getSession(false);

        if(session!=null) {
            if(logger.isInfoEnabled()){
                logger.info("Session destroy. " + session.getAttributeNames());
            }

            session.invalidate();
            request.setAttribute("errMessage", "Vous êtes bien déconnecté");

            response.sendRedirect("login");
        }
    }
}
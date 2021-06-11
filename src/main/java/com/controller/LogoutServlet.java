package com.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Vanconingsloo Kevin
 */

public class LogoutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false); //Fetch session object

        if(session!=null) { //If session is not null

            session.invalidate(); //removes all session attributes bound to the session
            request.setAttribute("errMessage", "Vous etes bien deconnecté");

            response.sendRedirect("login");
        }
    }
}
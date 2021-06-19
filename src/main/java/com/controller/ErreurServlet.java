package com.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * @author Vanconingsloo Kevin
 */

@WebServlet("/erreur")
public class ErreurServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ErreurServlet.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel de la methode doGet ErreurServlet.");
        }

        HttpSession session = request.getSession();

        logger.warn("Erreur : " + session.getAttribute("erreur"));

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/erreur.jsp").forward(request, response);
        session.removeAttribute("erreur");
        session.removeAttribute("retour");
    }
}

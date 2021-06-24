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
 * @author Wets Jeoffroy
 */

@WebServlet("/optionSup")
public class OptionSupServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(OptionSupServlet.class);

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"OptionSupServlet\"");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/optionSup.jsp").forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"OptionSupServlet\"");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
    }
}
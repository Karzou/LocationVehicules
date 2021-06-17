package com.controller;

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

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

            /* Affichage de la page d'inscription */
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/optionSup.jsp").forward( request, response );

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /* Transmission de la paire d'objets request/response à notre JSP */

            this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
        }

}
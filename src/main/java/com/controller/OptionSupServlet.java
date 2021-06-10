package com.controller;

import com.connection.EMF;
import com.entity.*;
import com.exception.ServiceException;
import com.service.EntrepotService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "OptionSupServlet")
public class OptionSupServlet extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        if (null == session.getAttribute("idUtilisateur")) {

            response.sendRedirect("login");
        } else {

            /* Affichage de la page d'inscription */
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/optionSup.jsp").forward( request, response );
        }

    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /* Transmission de la paire d'objets request/response à notre JSP */
        HttpSession session = request.getSession(true);

        if (null == session.getAttribute("idUtilisateur")) {

            response.sendRedirect("login");
        }else{

            this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
        }
    }
}
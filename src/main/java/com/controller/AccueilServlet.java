package com.controller;

import com.entity.*;
import com.exception.ServiceException;
import com.connection.EMF;
import com.service.EntrepotService;

import java.io.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        EntityManager em = EMF.getEMF().createEntityManager();
        EntrepotService entrepotService = new EntrepotService(em);
        List<Entrepot> entrepotList = null;

        try {
            entrepotList = entrepotService.lister();
        } catch (ServiceException e) {
            e.printStackTrace();
        } finally {

            em.close();
        }
        request.setAttribute( "entrepotList", entrepotList );

        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

    }
}
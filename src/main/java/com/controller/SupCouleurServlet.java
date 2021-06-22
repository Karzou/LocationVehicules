package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.exception.ServiceException;
import com.service.CouleurService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/supCouleur")
public class SupCouleurServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int idSup = Integer.parseInt(request.getParameter("idSup"));

        CouleurService couleurService = new CouleurService(em);
        Couleur couleur = null;

        try {

            couleur = couleurService.trouver(idSup);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            transaction.begin();

            couleurService.suppressionLogique(couleur);

            transaction.commit();
        } catch ( Exception e ) {

            throw new ServletException( e );
        } finally {

            if (transaction.isActive()) {

                transaction.rollback();
            }

            em.close();
        }

        response.sendRedirect("gestionCouleur");
    }
}

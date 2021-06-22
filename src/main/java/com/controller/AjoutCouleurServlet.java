package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.service.CouleurService;
import com.service.Validation;

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

@WebServlet("/ajoutCouleur")
public class AjoutCouleurServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        String nomCouleur = Validation.ucFirst(request.getParameter("nomCouleur"));

        // Instanciation
        CouleurService couleurService = new CouleurService(em);

        Couleur couleur = new Couleur(nomCouleur);

        try {

            transaction.begin();

            couleurService.creer(couleur);

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

package com.controller;

import com.connection.EMF;
import com.entity.Marque;
import com.service.MarqueService;
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

@WebServlet("/ajoutMarque")
public class AjoutMarqueServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        String nomMarque = Validation.ucFirst(request.getParameter("nomMarque"));

        // Instanciation
        MarqueService marqueService = new MarqueService(em);
        Marque marque = new Marque(nomMarque);

        try {

            transaction.begin();

            marqueService.creer(marque);

            transaction.commit();
        } catch ( Exception e ) {

            throw new ServletException( e );
        } finally {

            if (transaction.isActive()) {

                transaction.rollback();
            }

            em.close();
        }

        response.sendRedirect("gestionMarqueModele");
    }
}

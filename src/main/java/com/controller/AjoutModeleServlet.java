package com.controller;

import com.connection.EMF;
import com.entity.Marque;
import com.entity.Modele;
import com.service.MarqueService;
import com.service.ModeleService;

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

@WebServlet("/ajoutModele")
public class AjoutModeleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        int idMarque = Integer.parseInt(request.getParameter("idMarque"));
        String nomModele = request.getParameter("nomModele");

        // Instanciation
        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        Marque marque = null;

        try {

            marque = marqueService.trouver(idMarque);
        } catch ( Exception e ) {

            throw new ServletException( e );
        }

        Modele modele = new Modele(nomModele, marque);

        try {

            transaction.begin();

            modeleService.creer(modele);

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

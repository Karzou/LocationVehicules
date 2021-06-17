package com.controller;

import com.connection.EMF;
import com.entity.Vehicule;
import com.service.Validation;
import com.service.VehiculeService;

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

@WebServlet("/ajoutVehicule")
public class AjoutVehiculeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        idMarque
                idModele
        cylindree
                puissance
        numChassis
                immatriculation
        dateAchat
                prixJournalier
        actifVehicule


        // Récupération des données
        String nomEntrepot = Validation.ucFirst(request.getParameter("nomEntrepot"));
        int nombrePlaceEntrepot = Integer.parseInt(request.getParameter("nombrePlace"));
        String rueEntrepot = request.getParameter("rue");
        String numeroEntrepot = request.getParameter("numero");
        String boiteEntrepot = request.getParameter("boite");
        int idModele = Integer.parseInt(request.getParameter("idVille"));

        // Instanciation
        VehiculeService vehiculeService = new VehiculeService(em);
        Vehicule vehicule = new Vehicule();

        try {

            transaction.begin();

            vehiculeService.creer(vehicule);

            transaction.commit();
        } catch ( Exception e ) {

            throw new ServletException( e );
        } finally {

            if (transaction.isActive()) {

                transaction.rollback();
            }

            em.close();
        }

        response.sendRedirect("gestionVehicule");
    }
}

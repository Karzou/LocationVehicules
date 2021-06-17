package com.controller;

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

        /*EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        String nomEntrepot = Validation.ucFirst(request.getParameter("nomEntrepot"));
        int nombrePlaceEntrepot = Integer.parseInt(request.getParameter("nombrePlace"));
        String rueEntrepot = request.getParameter("rue");
        String numeroEntrepot = request.getParameter("numero");
        String boiteEntrepot = request.getParameter("boite");
        int idVilleEntrepot = Integer.parseInt(request.getParameter("idVille"));

        // Instanciation
        VehiculeService vehiculeService = new VehiculeService(em);
        Vehicule vehicule = null;

        Entrepot entrepot = new Entrepot(nomEntrepot, nombrePlaceEntrepot, adresse);

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

        response.sendRedirect("gestionEntrepot");*/
    }
}

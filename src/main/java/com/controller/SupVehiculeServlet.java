package com.controller;

import com.connection.EMF;
import com.entity.Vehicule;
import com.exception.ServiceException;
import com.service.VehiculeService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */
@WebServlet("/supVehicule")
public class SupVehiculeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        String id = request.getParameter("idSup");
        int idSup = Integer.parseInt(id);

        VehiculeService vehiculeService = new VehiculeService(em);
        Vehicule vehicule = null;
        String message = "";

        try {

            vehicule = vehiculeService.trouver(idSup);
            message = "Supression de l id " + idSup + " !!";
        } catch (ServiceException e) {

            e.printStackTrace();
            message = " erreur ";
        }

        try {

            transaction.begin();

            vehiculeService.suppressionLogique(vehicule);

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

package com.controller;

import com.connection.EMF;
import com.entity.Vehicule;
import com.exception.ServiceException;
import com.service.VehiculeService;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SupVehiculeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        String id = request.getParameter("idSup");
        int idSup = Integer.parseInt(id);

        VehiculeService vehiculeService = new VehiculeService(em);
        Vehicule vehicule = null;
        List<Vehicule>vehiculeList = null;
        String message = "";

        try {

            vehicule = vehiculeService.trouver(idSup);
            message = "Supression de l id " + idSup + " !!";
        } catch (ServiceException e) {

            e.printStackTrace();
            message = " erreur ";
        }

        try {

            vehiculeService.suppressionLogique(vehicule);
            vehiculeList = vehiculeService.lister();
        } catch (Exception e) {

            //message erreur
        } finally {

            em.close();
        }

        //request.setAttribute("vehiculeList", vehiculeList);

        //this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionVehicule.jsp").forward(request, response);

        response.sendRedirect("gestionVehicule");
    }
}

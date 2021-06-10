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

/**
 * @author Wets Jeoffroy
 */

public class ModifVehiculeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        String idReq = request.getParameter("idModif");
        int id = Integer.parseInt(idReq);

        VehiculeService vehiculeService = new VehiculeService(em);
        Vehicule vehicule = null;

        try {

            vehicule = vehiculeService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("vehicule", vehicule);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifVehicule.jsp").forward(request, response);
    }
}

package com.controller;

import com.entity.Vehicule;
import com.exception.ServiceException;
import com.connection.EMF;
import com.service.Validation;
import com.service.VehiculeService;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/vehicule")
public class VehiculeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        VehiculeService vehiculeService = new VehiculeService(em);
        List<Vehicule> vehiculeList = null;
        int periodeLocation = 0;

        Date dateDebut = Validation.dateFormat(request.getParameter("dateTimeDepart"));
        Date dateFin = Validation.dateFormat(request.getParameter("dateTimeRetour"));
        int idEntrepot = Integer.parseInt(request.getParameter("LieuDepart"));

        try {

            vehiculeList = vehiculeService.rechercher(idEntrepot, dateDebut, dateFin);
            periodeLocation = (int) vehiculeService.periodeLocation(dateDebut, dateFin);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("periodeLocation", periodeLocation);
        request.setAttribute("vehiculeList", vehiculeList);

        /* Affichage de la page d'inscription */
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/vehicule.jsp").forward( request, response );
    }
}

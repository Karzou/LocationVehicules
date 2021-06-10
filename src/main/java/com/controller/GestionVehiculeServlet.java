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
import java.sql.Date;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class GestionVehiculeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        List<Vehicule> vehiculeList = null;
        VehiculeService vehiculeService = new VehiculeService(em);

        try {

            vehiculeList = vehiculeService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("vehiculeList", vehiculeList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionVehicule.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        String idReq = request.getParameter("idModif");
        int id = Integer.parseInt(idReq);

        List<Vehicule> vehiculeList = null;
        VehiculeService vehiculeService = new VehiculeService(em);
        Vehicule vehicule = null;

        try {

            vehicule = vehiculeService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        vehicule.getModelesByIdModele().getMarquesByIdMarque().setNomMarque(request.getParameter("marque"));
        vehicule.getModelesByIdModele().setNomModele(request.getParameter("modele"));
        vehicule.setPuissance(Integer.parseInt(request.getParameter("puissance")));
        vehicule.setCylindree(Integer.parseInt(request.getParameter("cylindree")));
        vehicule.setImmatriculation(request.getParameter("immatriculation"));
        vehicule.setDateAchat(Date.valueOf(request.getParameter("dateAchat")));
        vehicule.setNumChassis(request.getParameter("numChassis"));
        vehicule.setPrixJournalier(Float.parseFloat(request.getParameter("prixJournalier")));
        vehicule.setActifVehicule(Boolean.parseBoolean(request.getParameter("status")));

        try {

            vehiculeService.update(vehicule);
            vehiculeList = vehiculeService.lister();
        } catch (Exception e) {

            //message erreur
        } finally {

            em.close();
        }

        request.setAttribute("vehiculeList", vehiculeList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionVehicule.jsp" ).forward( request, response );
    }
}

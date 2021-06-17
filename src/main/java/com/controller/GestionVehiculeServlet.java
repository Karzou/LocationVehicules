package com.controller;

import com.connection.EMF;
import com.entity.Marque;
import com.entity.Modele;
import com.entity.Vehicule;
import com.exception.ServiceException;
import com.service.MarqueService;
import com.service.ModeleService;
import com.service.VehiculeService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet(value = "/gestionVehicule")
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
        EntityTransaction transaction = em.getTransaction();

        int id = Integer.parseInt(request.getParameter("idModif"));
        int idMarque = Integer.parseInt(request.getParameter("idMarque"));
        int idModele = Integer.parseInt(request.getParameter("idModele"));
        String status = request.getParameter("actifVehicule");

        VehiculeService vehiculeService = new VehiculeService(em);
        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        Vehicule vehicule = null;
        Marque marque = null;
        Modele modele = null;

        try {

            marque = marqueService.trouver(idMarque);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            modele = modeleService.trouver(idModele);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            vehicule = vehiculeService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            transaction.begin();

            vehicule.getModelesByIdModele().setMarquesByIdMarque(marque);
            vehicule.setModelesByIdModele(modele);
            vehicule.setPuissance(Integer.parseInt(request.getParameter("puissance")));
            vehicule.setCylindree(Integer.parseInt(request.getParameter("cylindree")));
            vehicule.setImmatriculation(request.getParameter("immatriculation"));
            vehicule.setDateAchat(Date.valueOf(request.getParameter("dateAchat")));
            vehicule.setNumChassis(request.getParameter("numChassis"));
            vehicule.setPrixJournalier(Float.parseFloat(request.getParameter("prixJournalier")));

            if (status == null) {
                vehicule.setActifVehicule(false);
            } else {
                vehicule.setActifVehicule(true);
            }

            vehiculeService.update(vehicule);

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

package com.controller;

import com.connection.EMF;
import com.entity.*;
import com.exception.ServiceException;
import com.service.*;

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
import java.util.Set;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/gestionVehicule")
public class GestionVehiculeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        boolean newVehicleFlag = Boolean.parseBoolean(request.getParameter("newVehicleFlag"));

        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        OptionVehiculeService optionVehiculeService = new OptionVehiculeService(em);
        EntrepotService entrepotService = new EntrepotService(em);
        CouleurService couleurService = new CouleurService(em);
        VehiculeService vehiculeService = new VehiculeService(em);
        Marque marque = null;
        List<Marque> marqueList = null;
        List<Modele> modeleList = null;
        List<OptionVehicule> optionVehiculesList = null;
        List<Vehicule> vehiculeList = null;
        List<Entrepot> entrepotList = null;
        List<Couleur> couleurList = null;

        try {

            marqueList = marqueService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        if (newVehicleFlag) {

            int idMarque = Integer.parseInt(request.getParameter("idMarque"));

            try {

                marque = marqueService.trouver(idMarque);
            } catch (ServiceException e) {

                e.printStackTrace();
            }

            try {

                modeleList = modeleService.lister();
            } catch (ServiceException e) {

                e.printStackTrace();
            }
        }

        try {

            couleurList = couleurService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            entrepotList = entrepotService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            optionVehiculesList = optionVehiculeService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            vehiculeList = vehiculeService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("newVehicleFlag", newVehicleFlag);
        request.setAttribute("marque", marque);
        request.setAttribute("marqueList", marqueList);
        request.setAttribute("modeleList", modeleList);
        request.setAttribute("optionVehiculesList", optionVehiculesList);
        request.setAttribute("entrepotList", entrepotList);
        request.setAttribute("couleurList", couleurList);
        request.setAttribute("vehiculeList", vehiculeList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionVehicule.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int idVehicule = Integer.parseInt(request.getParameter("idModif"));
        int idModele = Integer.parseInt(request.getParameter("idModele"));
        int idCouleur = Integer.parseInt(request.getParameter("idCouleur"));
        int idEntrepot = Integer.parseInt(request.getParameter("idEntrepot"));
        String status = request.getParameter("actifVehicule");

        VehiculeService vehiculeService = new VehiculeService(em);
        ModeleService modeleService = new ModeleService(em);
        CouleurService couleurService = new CouleurService(em);
        EntrepotService entrepotService = new EntrepotService(em);
        ContientService contientService = new ContientService(em);
        Vehicule vehicule = null;
        Modele modele = null;
        Couleur couleur = null;
        Entrepot entrepot = null;
        List<Contient> contientList;

        try {

            modele = modeleService.trouver(idModele);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            vehicule = vehiculeService.trouver(idVehicule);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            couleur = couleurService.trouver(idCouleur);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            entrepot = entrepotService.trouver(idEntrepot);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            transaction.begin();

            vehicule.setModelesByIdModele(modele);
            vehicule.setPuissance(Integer.parseInt(request.getParameter("puissance")));
            vehicule.setCylindree(Integer.parseInt(request.getParameter("cylindree")));
            vehicule.setImmatriculation(request.getParameter("immatriculation"));
            vehicule.setDateAchat(Date.valueOf(request.getParameter("dateAchat")));
            vehicule.setNumChassis(request.getParameter("numChassis"));
            vehicule.setPrixJournalier(Float.parseFloat(request.getParameter("prixJournalier")));
            vehicule.setCouleursByIdCouleur(couleur);
            vehicule.setEntrepotsByIdEntrepot(entrepot);

            if (status == null) {
                vehicule.setActifVehicule(false);
            } else {
                vehicule.setActifVehicule(true);
            }

            vehiculeService.update(vehicule);

            contientList = contientService.lister();

            for (Contient contient: contientList) {

                if (contient.getVehiculesByIdVehicule().getIdVehicule() == idVehicule) {

                    contientService.supprimer(contient);
                }
            }

            // get all parameter names
            Set<String> paramNames = request.getParameterMap().keySet();

            // iterating over parameter names and get its value
            for (String name : paramNames) {

                if (name.contains("option")) {

                    int idOption = Integer.parseInt(request.getParameter(name));
                    OptionVehiculeService optionVehiculeService = new OptionVehiculeService(em);
                    OptionVehicule optionVehicule;

                    try {

                        optionVehicule = optionVehiculeService.trouver(idOption);
                    } catch ( Exception e ) {

                        throw new ServletException( e );
                    }

                    Contient contient = new Contient(optionVehicule, vehicule);

                    contientService.creer(contient);
                }
            }

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

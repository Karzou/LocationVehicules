package com.controller;

import com.connection.EMF;
import com.entity.*;
import com.exception.ServiceException;
import com.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Set;

/**
 * @author Wets Jeoffroy
 */

@WebServlet(value = "/gestionVehicule")
public class GestionVehiculeServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionVehiculeServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"GestionVehiculeServlet\"");
        }

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

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

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

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"GestionVehiculeServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        int idVehicule = Integer.parseInt(request.getParameter("idModif"));
        int idModele = Integer.parseInt(request.getParameter("idModele"));
        int idCouleur = Integer.parseInt(request.getParameter("idCouleur"));
        int idEntrepot = Integer.parseInt(request.getParameter("idEntrepot"));
        String strcylindree = request.getParameter("cylindree");
        String strpuissance = request.getParameter("puissance");
        String numChassis = request.getParameter("numChassis");
        String immatriculation = request.getParameter("immatriculation");
        String strdateAchat = request.getParameter("dateAchat");
        String strprixJournalier = request.getParameter("prixJournalier");
        String status = request.getParameter("actifVehicule");

        boolean checkModifVehicule = true;

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

            vehicule = vehiculeService.trouver(idVehicule);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        boolean errFlag = false;

        if (Validation.checkValueIsEmpty(strcylindree)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "Veuillez insérer la cylindrée du véhicule");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        } else if (Validation.checkValueIsZero(strcylindree)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "Veuillez insérer un nombre supérieur à 0 pour la cylindrée du véhicule");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        } else if (!Validation.checkValueIsInteger(strcylindree)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "La cylindrée du véhicule doit être une valeur numérique");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        } else if (!Validation.checkValueLenght(strcylindree, 2, 10)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "La cylindrée du véhicule doit contenir au maximum 10 chiffres");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(strpuissance)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Veuillez insérer la puissance du véhicule");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        } else if (Validation.checkValueIsZero(strpuissance)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Veuillez insérer un nombre supérieur à 0 pour la puissance du véhicule");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        } else if (!Validation.checkValueIsInteger(strpuissance)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "La puissance du véhicule doit être une valeur numérique");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        } else if (!Validation.checkValueLenght(strpuissance, 2, 10)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "La puissance du véhicule doit contenir au maximum 10 chiffres");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(numChassis)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage3", "Veuillez insérer le numéro de chassis du véhicule");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        } else if (vehiculeService.checkOtherNumeroChassisExist(numChassis, idVehicule)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage3", "Ce numéro de chassis existe déjà");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        } else if (Validation.checkValueLenght(numChassis, 14, 14)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage3", "Le numéro de chassis doit contenir 14 caractères");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(immatriculation)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage4", "Veuillez insérer l'immatriculation du véhicule");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        }

        if (Validation.checkValueIsEmptyorNull(strdateAchat)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage5", "Veuillez selectionner la date d'achat du véhicule");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(strprixJournalier)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage6", "Veuillez insérer un prix journalier pour la location du véhicule");
            session.setAttribute("idModif", idVehicule);
            session.setAttribute("idMarque", vehicule.getModelesByIdModele().getMarquesByIdMarque().getIdMarque());
            session.setAttribute("checkModifVehicule", checkModifVehicule);

            errFlag = true;
        }

        if (errFlag) {

            response.sendRedirect("modifVehicule");
        } else {

            try {

                modele = modeleService.trouver(idModele);
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
                vehicule.setPuissance(Integer.parseInt(strpuissance));
                vehicule.setCylindree(Integer.parseInt(strcylindree));
                vehicule.setImmatriculation(immatriculation);
                vehicule.setDateAchat(Date.valueOf(strdateAchat));
                vehicule.setNumChassis(numChassis);
                vehicule.setPrixJournalier(Float.parseFloat(strprixJournalier));
                vehicule.setCouleursByIdCouleur(couleur);
                vehicule.setEntrepotsByIdEntrepot(entrepot);

                if (status == null) {
                    vehicule.setActifVehicule(false);
                } else {
                    vehicule.setActifVehicule(true);
                }

                vehiculeService.update(vehicule);

                contientList = contientService.lister();

                for (Contient contient : contientList) {

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
                        } catch (Exception e) {

                            throw new ServletException(e);
                        }

                        Contient contient = new Contient(optionVehicule, vehicule);

                        contientService.creer(contient);
                    }
                }

                transaction.commit();
            } catch (Exception e) {

                throw new ServletException(e);
            } finally {

                if (transaction.isActive()) {

                    transaction.rollback();
                }

                if (logger.isInfoEnabled()) {

                    logger.info("Fermeture de l'EntityManager");
                }

                em.close();
            }

            response.sendRedirect("gestionVehicule");
        }
    }
}

package com.controller;

import com.connection.EMF;
import com.entity.*;
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
import java.util.Set;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/ajoutVehicule")
public class AjoutVehiculeServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(AjoutVehiculeServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"AjoutVehiculeServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"AjoutVehiculeServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        AutoriseService autoriseService = new AutoriseService(em);
        HttpSession session = request.getSession();

        if ((autoriseService.hasPermission((int)session.getAttribute("idRole"), "all")) || (autoriseService.hasPermission((int)session.getAttribute("idRole"), "vehicules:write"))) {

            // Récupération des données
            String stridMarque = request.getParameter("idMarque");
            String stridModele = request.getParameter("idModele");
            String stridCouleur = request.getParameter("idCouleur");
            String stridEntrepot = request.getParameter("idEntrepot");
            String strcylindree = request.getParameter("cylindree");
            String strpuissance = request.getParameter("puissance");
            String numChassis = request.getParameter("numChassis");
            String immatriculation = request.getParameter("immatriculation");
            String strdateAchat = request.getParameter("dateAchat");
            String strprixJournalier = request.getParameter("prixJournalier");

            // Instanciation
            VehiculeService vehiculeService = new VehiculeService(em);
            EntrepotService entrepotService = new EntrepotService(em);
            CouleurService couleurService = new CouleurService(em);
            ModeleService modeleService = new ModeleService(em);
            OptionVehiculeService optionVehiculeService = new OptionVehiculeService(em);
            ContientService contientService = new ContientService(em);
            Entrepot entrepot = null;
            Couleur couleur = null;
            Modele modele = null;
            OptionVehicule optionVehicule = null;

            boolean errFlag = false;

            if (Validation.checkValueIsEmptyorNull(stridMarque)) {

                session.setAttribute("errMessage1", "Veuillez selectionner une marque");

                errFlag = true;
            }

            if (Validation.checkValueIsEmptyorNull(stridModele)) {

                session.setAttribute("errMessage2", "Veuillez selectionner un modèle");

                errFlag = true;
            }

            if (Validation.checkValueIsEmpty(strcylindree)) {

                session.setAttribute("errMessage3", "Veuillez insérer la cylindrée du véhicule");

                errFlag = true;
            } else if (Validation.checkValueIsZero(strcylindree)) {

                session.setAttribute("errMessage3", "Veuillez insérer un nombre supérieur à 0 pour la cylindrée du véhicule");

                errFlag = true;
            } else if (!Validation.checkValueIsInteger(strcylindree)) {

                session.setAttribute("errMessage3", "La cylindrée du véhicule doit être une valeur numérique");

                errFlag = true;
            } else if (!Validation.checkValueLenght(strcylindree, 2, 10)) {

                session.setAttribute("errMessage3", "La cylindrée du véhicule doit contenir au maximum 10 chiffres");

                errFlag = true;
            }

            if (Validation.checkValueIsEmpty(strpuissance)) {

                session.setAttribute("errMessage4", "Veuillez insérer la puissance du véhicule");

                errFlag = true;
            } else if (Validation.checkValueIsZero(strpuissance)) {

                session.setAttribute("errMessage4", "Veuillez insérer un nombre supérieur à 0 pour la puissance du véhicule");

                errFlag = true;
            } else if (!Validation.checkValueIsInteger(strpuissance)) {

                session.setAttribute("errMessage4", "La puissance du véhicule doit être une valeur numérique");

                errFlag = true;
            } else if (!Validation.checkValueLenght(strpuissance, 2, 10)) {

                session.setAttribute("errMessage4", "La puissance du véhicule doit contenir au maximum 10 chiffres");

                errFlag = true;
            }

            if (Validation.checkValueIsEmpty(numChassis)) {

                session.setAttribute("errMessage5", "Veuillez insérer le numéro de chassis du véhicule");

                errFlag = true;
            } else if (vehiculeService.checkNumeroChassisExist(numChassis)) {

                session.setAttribute("errMessage5", "Ce numéro de chassis existe déjà");

                errFlag = true;
            } else if (Validation.checkValueLenght(numChassis, 14, 14)) {

                session.setAttribute("errMessage5", "Le numéro de chassis doit contenir 14 caractères");

                errFlag = true;
            }

            if (Validation.checkValueIsEmpty(immatriculation)) {

                session.setAttribute("errMessage6", "Veuillez insérer l'immatriculation du véhicule");

                errFlag = true;
            }

            if (Validation.checkValueIsEmptyorNull(strdateAchat)) {

                session.setAttribute("errMessage7", "Veuillez selectionner la date d'achat du véhicule");

                errFlag = true;
            }

            if (Validation.checkValueIsEmpty(strprixJournalier)) {

                session.setAttribute("errMessage8", "Veuillez insérer un prix journalier pour la location du véhicule");

                errFlag = true;
            } else if (!Validation.checkValueIsIFloat(strprixJournalier)) {

                session.setAttribute("errMessage8", "Le prix journalier pour la location du véhicule doit être un nombre décimal");

                errFlag = true;
            }

            if (Validation.checkValueIsEmptyorNull(stridCouleur)) {

                session.setAttribute("errMessage9", "Veuillez selectionner une couleur");

                errFlag = true;
            }

            if (Validation.checkValueIsEmptyorNull(stridEntrepot)) {

                session.setAttribute("errMessage10", "Veuillez selectionner un entrepôt");

                errFlag = true;
            }

            if (errFlag) {

                response.sendRedirect("gestionVehicule");
            } else {

                int idModele = Integer.parseInt(stridModele);
                int idCouleur = Integer.parseInt(stridCouleur);
                int idEntrepot = Integer.parseInt(stridEntrepot);
                int cylindree = Integer.parseInt(strcylindree);
                int puissance = Integer.parseInt(strpuissance);
                Date dateAchat = Date.valueOf(strdateAchat);
                float prixJournalier = Float.parseFloat(strprixJournalier);

                try {

                    modele = modeleService.trouver(idModele);
                } catch (Exception e) {

                    throw new ServletException(e);
                }

                try {

                    entrepot = entrepotService.trouver(idEntrepot);
                } catch (Exception e) {

                    throw new ServletException(e);
                }

                try {

                    couleur = couleurService.trouver(idCouleur);
                } catch (Exception e) {

                    throw new ServletException(e);
                }

                Vehicule vehicule = new Vehicule(numChassis, cylindree, puissance, dateAchat,
                        immatriculation, prixJournalier, entrepot, couleur, modele);

                try {

                    transaction.begin();

                    vehiculeService.creer(vehicule);

                    transaction.commit();
                } catch (Exception e) {

                    throw new ServletException(e);
                } finally {

                    if (transaction.isActive()) {

                        transaction.rollback();
                    }
                }

                try {

                    // get all parameter names
                    Set<String> paramNames = request.getParameterMap().keySet();

                    // iterating over parameter names and get its value
                    for (String name : paramNames) {

                        if (name.contains("option")) {

                            int idOption = Integer.parseInt(request.getParameter(name));

                            try {

                                optionVehicule = optionVehiculeService.trouver(idOption);
                            } catch (Exception e) {

                                throw new ServletException(e);
                            }

                            Contient contient = new Contient(optionVehicule, vehicule);

                            transaction.begin();

                            contientService.creer(contient);

                            transaction.commit();
                        }
                    }
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

                    session.setAttribute("succMessage", "Le véhicule immatriculé '" + immatriculation + "' a été ajouté avec succès");
                }

                response.sendRedirect("gestionVehicule");
            }
        } else {

            logger.info("hasPermission non OK");

            session.setAttribute("erreur", "Vous n'avez pas les droits requis ! ");
            session.setAttribute("retour", "/gestionVehicule");

            response.sendRedirect("erreur");
        }
    }
}

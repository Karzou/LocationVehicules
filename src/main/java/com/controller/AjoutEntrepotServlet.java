package com.controller;

import com.connection.EMF;
import com.entity.Adresse;
import com.entity.Entrepot;
import com.entity.Ville;
import com.exception.ServiceException;
import com.service.EntrepotService;
import com.service.Validation;
import com.service.VilleService;
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

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/ajoutEntrepot")
public class AjoutEntrepotServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(AjoutEntrepotServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"AjoutEntrepotServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"AjoutEntrepotServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        String nomEntrepot = request.getParameter("nomEntrepot");
        String nombrePlaceEntrepot = request.getParameter("nombrePlace");
        String rueEntrepot = request.getParameter("rue");
        String numeroEntrepot = request.getParameter("numero");
        String boiteEntrepot = request.getParameter("boite");
        int idVilleEntrepot = Integer.parseInt(request.getParameter("idVille"));

        // Instanciation
        EntrepotService entrepotService = new EntrepotService(em);
        VilleService villeService = new VilleService(em);
        Ville ville = null;

        boolean errFlag = true;

        if (Validation.checkNomEntrepotIsEmpty(nomEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "Veuillez insérer un nom pour l'entrepôt");

            errFlag = false;

        } else if (!Validation.checkNomEntrepotLenght(nomEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "Le nom de l'entrepôt doit être composé d'au minimum 2 caractères et au maximum 50 caractères");

            errFlag = false;
        }

        if (Validation.checkNombrePlaceEntrepotIsEmpty(nombrePlaceEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Veuillez insérer le nombre de place de l'entrepôt");

            errFlag = false;
        } else if (Validation.checkNombrePlaceEntrepotIsZero(nombrePlaceEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Veuillez insérer un nombre supérieur à 0 pour le nombre de place de l'entrepôt");

            errFlag = false;
        } else if (!Validation.checkNombrePlaceEntrepotIsNumeric(nombrePlaceEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Le nombre de place de l'entrepôt doit être une valeur numérique");

            errFlag = false;
        } else if (!Validation.checkNombrePlaceEntrepotLenght(nomEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Le nombre de place de l'entrepôt doit contenir au maximum 10 chiffres");

            errFlag = false;
        }

        if (Validation.checkRueEntrepotIsEmpty(rueEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage3", "Veuillez insérer le nom de la rue de l'entrepôt");

            errFlag = false;
        } else if (!Validation.checkRueEntrepotLenght(rueEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage3", "Le nom de la rue de l'entrepôt doit être composé d'au minimum 2 caractères et au maximum 100 caractères");

            errFlag = false;
        }

        if (Validation.checkNumeroEntrepotIsEmpty(numeroEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage4", "Veuillez insérer le numéro de la rue de l'entrepôt");

            errFlag = false;
        } else if (!Validation.checkNumeroEntrepotLenght(numeroEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage4", "Le numéro de la rue de l'entrepôt doit être composé d'au maximum 10 caractères");

            errFlag = false;
        }

        if (!Validation.checkBoiteEntrepotLenght(boiteEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage5", "Le boîte de l'entrepôt doit être composé d'au maximum 10 caractères");

            errFlag = false;
        }

        if (!errFlag) {

            response.sendRedirect("gestionEntrepot");
        } else {

            nomEntrepot = Validation.upperCase(nomEntrepot);
            int nombrePlaceEntrepot2 = Integer.parseInt(nombrePlaceEntrepot);

            try {

                ville = villeService.trouver(idVilleEntrepot);
            } catch (ServiceException e) {

                e.printStackTrace();
            }

            Adresse adresse = new Adresse(rueEntrepot, numeroEntrepot, boiteEntrepot, ville);
            Entrepot entrepot = new Entrepot(nomEntrepot, nombrePlaceEntrepot2, adresse);

            try {

                transaction.begin();

                entrepotService.creer(entrepot);

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

                HttpSession session = request.getSession();

                session.setAttribute("succMessage", "L'entrepôt '" + nomEntrepot + "' a été ajouté avec succès");
            }

            response.sendRedirect("gestionEntrepot");
        }
    }
}

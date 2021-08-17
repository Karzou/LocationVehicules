package com.controller;

import com.connection.EMF;
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
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/gestionEntrepot")
public class GestionEntrepotServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionEntrepotServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"GestionEntrepotServlet\"");
        }

        EntityManager em = EMF.getEM();

        EntrepotService entrepotService = new EntrepotService(em);
        VilleService villeService = new VilleService(em);
        List<Entrepot> entrepotList = null;
        List<Ville> villeList = null;

        try {

            villeList = villeService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            entrepotList = entrepotService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("villeList", villeList);
        request.setAttribute("entrepotList", entrepotList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionEntrepot.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"GestionEntrepotServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int idModif = Integer.parseInt(request.getParameter("idModif"));
        int idVille = Integer.parseInt(request.getParameter("idVille"));
        String nomEntrepot = request.getParameter("nomEntrepot");
        String nombrePlaceEntrepot = request.getParameter("nombrePlace");
        String rueEntrepot = request.getParameter("rue");
        String numeroEntrepot = request.getParameter("numero");
        String boiteEntrepot = request.getParameter("boite");
        String status = request.getParameter("actifEntrepot");

        EntrepotService entrepotService = new EntrepotService(em);
        VilleService villeService = new VilleService(em);
        Entrepot entrepot = null;
        Ville ville = null;

        boolean errFlag = false;

        if (Validation.checkValueIsEmpty(nomEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "Veuillez insérer un nom pour l'entrepôt");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        } else if (entrepotService.checkOtherEntrepotExist(nomEntrepot, idModif)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "Ce nom d'entrepôt existe déjà");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        } else if (!Validation.checkValueLenght(nomEntrepot, 2, 50)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "Le nom de l'entrepôt doit être composé d'au minimum 2 caractères et au maximum 50 caractères");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(nombrePlaceEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Veuillez insérer le nombre de place de l'entrepôt");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        } else if (Validation.checkValueIsZero(nombrePlaceEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Veuillez insérer un nombre supérieur à 0 pour le nombre de place de l'entrepôt");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        } else if (!Validation.checkValueIsInteger(nombrePlaceEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Le nombre de place de l'entrepôt doit être une valeur numérique");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        } else if (!Validation.checkValueLenghtMax(nombrePlaceEntrepot, 10)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Le nombre de place de l'entrepôt doit contenir au maximum 10 chiffres");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(rueEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage3", "Veuillez insérer le nom de la rue de l'entrepôt");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        } else if (!Validation.checkValueLenght(rueEntrepot, 2, 100)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage3", "Le nom de la rue de l'entrepôt doit être composé d'au minimum 2 caractères et au maximum 100 caractères");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(numeroEntrepot)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage4", "Veuillez insérer le numéro de la rue de l'entrepôt");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        } else if (!Validation.checkValueLenghtMax(numeroEntrepot, 10)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage4", "Le numéro de la rue de l'entrepôt doit être composé d'au maximum 10 caractères");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        }

        if (!Validation.checkValueLenghtMax(boiteEntrepot, 10)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage5", "La boîte de l'entrepôt doit être composé d'au maximum 10 caractères");
            session.setAttribute("idModif", idModif);
            session.setAttribute("idVille", idVille);

            errFlag = true;
        }

        if (errFlag) {

            response.sendRedirect("modifEntrepot");
        } else {

            try {

                ville = villeService.trouver(idVille);
            } catch (ServiceException e) {

                e.printStackTrace();
            }

            try {

                entrepot = entrepotService.trouver(idModif);
            } catch (ServiceException e) {

                e.printStackTrace();
            }

            try {

                transaction.begin();

                entrepot.setNomEntrepot(nomEntrepot);
                entrepot.setNombrePlace(Integer.parseInt(nombrePlaceEntrepot));
                entrepot.getAdressesByIdAdresse().setRue(rueEntrepot);
                entrepot.getAdressesByIdAdresse().setNumero(numeroEntrepot);
                entrepot.getAdressesByIdAdresse().setBoite(boiteEntrepot);
                entrepot.getAdressesByIdAdresse().setVillesByIdVille(ville);

                if (status == null) {
                    entrepot.setActifEntrepot(false);
                } else {
                    entrepot.setActifEntrepot(true);
                }

                entrepotService.update(entrepot);

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

            response.sendRedirect("gestionEntrepot");
        }
    }
}

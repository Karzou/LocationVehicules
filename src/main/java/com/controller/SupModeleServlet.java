package com.controller;

import com.connection.EMF;
import com.entity.Modele;
import com.exception.ServiceException;
import com.service.ModeleService;
import com.service.VehiculeService;
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

@WebServlet("/supModele")
public class SupModeleServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(SupModeleServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"SupModeleServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"SupModeleServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int idSup = Integer.parseInt(request.getParameter("idSup"));

        ModeleService modeleService = new ModeleService(em);
        VehiculeService vehiculeService = new VehiculeService(em);
        Modele modele = null;

        HttpSession session = request.getSession();

        if (vehiculeService.checkModeleExist(idSup)) {

            logger.info("Le modèle est déjà utilisé sur un ou plusieurs véhicules, impossible de supprimer celui-ci");

            session.setAttribute("errMessage4", "Impossible de supprimer ce modèle car celui-ci est attribué à un ou plusieurs véhicules");

            response.sendRedirect("gestionMarqueModele");
        } else {

            logger.info("Modèle non utilisé, suppression en cours");

            try {

                modele = modeleService.trouver(idSup);
            } catch (ServiceException e) {

                e.printStackTrace();
            }

            try {

                transaction.begin();

                modeleService.suppression(modele);

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

            session.setAttribute("succMessage3", "Le modèle \"" + modele.getNomModele() + "\" a été supprimé avec succès");

            response.sendRedirect("gestionMarqueModele");
        }
    }
}

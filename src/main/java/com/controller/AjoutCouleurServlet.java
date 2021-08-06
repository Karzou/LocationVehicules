package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.service.CouleurService;
import com.service.Validation;
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

@WebServlet("/ajoutCouleur")
public class AjoutCouleurServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(AjoutCouleurServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"AjoutCouleurServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"AjoutCouleurServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        String nomCouleur = request.getParameter("nomCouleur");
        boolean checkValidation = true;

        // Validation des données
        /*if(!Validation.validationCouleur(nomCouleur)) {

            checkValidation = false;
        }*/

        if(!checkValidation) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage", "Le nom de la couleur ne doit pas être vide et doit être compris entre 2 et 50 caractères");

            response.sendRedirect("gestionCouleur");
        } else {

            nomCouleur = Validation.ucFirst(nomCouleur);

            // Instanciation
            CouleurService couleurService = new CouleurService(em);

            Couleur couleur = new Couleur(nomCouleur);

            try {

                transaction.begin();

                couleurService.creer(couleur);

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

            response.sendRedirect("gestionCouleur");
        }
    }
}

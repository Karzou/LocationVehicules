package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.exception.ServiceException;
import com.service.AutoriseService;
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

        AutoriseService autoriseService = new AutoriseService(em);
        HttpSession session = request.getSession();

        if ((autoriseService.hasPermission((int)session.getAttribute("idRole"), "all")) || (autoriseService.hasPermission((int)session.getAttribute("idRole"), "couleurs:write"))) {

            // Récupération des données
            String nomCouleur = request.getParameter("nomCouleur");

            if (Validation.checkValueIsEmpty(nomCouleur)) {

                session.setAttribute("errMessage", "Veuillez insérer un nom de couleur");

                response.sendRedirect("gestionCouleur");
            } else if (!Validation.checkValueLenght(nomCouleur, 2, 50)) {

                session.setAttribute("errMessage", "Le nom de la couleur doit etre composé d'au minimum 2 caractères et de maximum 50 caractères");

                response.sendRedirect("gestionCouleur");
            } else {

                nomCouleur = Validation.ucFirst(nomCouleur);

                // Instanciation
                CouleurService couleurService = new CouleurService(em);

                if (couleurService.checkCouleurExist(nomCouleur)) {

                    session.setAttribute("errMessage", "Ce nom de couleur existe déjà");
                } else {

                    Couleur couleur = new Couleur(nomCouleur);

                    try {

                        transaction.begin();

                        couleurService.creer(couleur);

                        transaction.commit();
                    } catch (ServiceException e) {

                        e.printStackTrace();
                    } finally {

                        if (transaction.isActive()) {

                            transaction.rollback();
                        }

                        if (logger.isInfoEnabled()) {

                            logger.info("Fermeture de l'EntityManager");
                        }

                        em.close();
                    }

                    session.setAttribute("succMessage", "La couleur '" + nomCouleur + "' a été ajouté avec succès");
                }

                response.sendRedirect("gestionCouleur");
            }
        } else {

            logger.info("hasPermission non OK");

            session.setAttribute("erreur", "Vous n'avez pas les droits requis ! ");
            session.setAttribute("retour", "/gestionCouleur");

            response.sendRedirect("erreur");
        }
    }
}

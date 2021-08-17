package com.controller;

import com.connection.EMF;
import com.entity.Marque;
import com.exception.ServiceException;
import com.service.MarqueService;
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

@WebServlet("/ajoutMarque")
public class AjoutMarqueServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(AjoutMarqueServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"AjoutMarqueServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"AjoutMarqueServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        String nomMarque = request.getParameter("nomMarque");

        if (Validation.checkValueIsEmpty(nomMarque)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "Veuillez insérer une marque");

            response.sendRedirect("gestionMarqueModele");

        } else if (!Validation.checkValueLenght(nomMarque, 2, 50)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "La marque doit etre composé d'au minimum 2 caractères et de maximum 50 caractères");

            response.sendRedirect("gestionMarqueModele");
        } else {

            nomMarque = nomMarque.toUpperCase();

            // Instanciation
            MarqueService marqueService = new MarqueService(em);

            if (marqueService.checkMarqueExist(nomMarque)) {

                HttpSession session = request.getSession();

                session.setAttribute("errMessage1", "La marque '" + nomMarque + "' existe déjà");
            } else {

                Marque marque = new Marque(nomMarque);

                try {

                    transaction.begin();

                    marqueService.creer(marque);

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

                HttpSession session = request.getSession();

                session.setAttribute("succMessage1", "La marque '" + nomMarque + "' a été ajouté avec succès");
            }

            response.sendRedirect("gestionMarqueModele");
        }
    }
}

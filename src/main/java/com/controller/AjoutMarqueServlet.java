package com.controller;

import com.connection.EMF;
import com.entity.Marque;
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
        String nomMarque = Validation.ucFirst(request.getParameter("nomMarque"));

        // Instanciation
        MarqueService marqueService = new MarqueService(em);
        Marque marque = new Marque(nomMarque);

        try {

            transaction.begin();

            marqueService.creer(marque);

            transaction.commit();
        } catch ( Exception e ) {

            throw new ServletException( e );
        } finally {

            if (transaction.isActive()) {

                transaction.rollback();
            }

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        response.sendRedirect("gestionMarqueModele");
    }
}

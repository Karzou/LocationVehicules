package com.controller;

import com.connection.EMF;
import com.entity.Entrepot;
import com.exception.ServiceException;
import com.service.EntrepotService;
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

@WebServlet("/supMarqueModele")
public class SupMarqueModeleServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(SupMarqueModeleServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"SupMarqueModeleServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"SupMarqueModeleServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        String id = request.getParameter("idSup");
        int idSup = Integer.parseInt(id);

        EntrepotService entrepotService = new EntrepotService(em);
        Entrepot entrepot = null;
        String message = "";

        try {

            entrepot = entrepotService.trouver(idSup);
            message = "Supression de l id " + idSup + " !!";
        } catch (ServiceException e) {

            e.printStackTrace();
            message = " erreur ";
        }

        try {

            transaction.begin();

            entrepotService.suppressionLogique(entrepot);

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

        response.sendRedirect("gestionEntrepot");
    }
}

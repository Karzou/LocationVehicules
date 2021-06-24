package com.controller;

import com.entity.*;
import com.exception.ServiceException;
import com.connection.EMF;
import com.service.EntrepotService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/accueil")
public class AccueilServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(AccueilServlet.class);

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"AccueilServlet\"");
        }

        EntityManager em = EMF.getEMF().createEntityManager();
        EntrepotService entrepotService = new EntrepotService(em);
        List<Entrepot> entrepotList = null;

        try {

            if(logger.isInfoEnabled()) {

                logger.info("Demande de la liste des entrepots à partir de la base de données");
            }

            entrepotList = entrepotService.lister();
        } catch (ServiceException e) {

            if(logger.isInfoEnabled()) {

                logger.warn("Impossible de demander la liste des entrepots à partir de la base de données");
            }

            e.printStackTrace();
        } finally {

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        if(logger.isInfoEnabled()) {

            logger.info("Envoi de la liste des entrepots à la vue \"accueil.jsp\"");
        }

        request.setAttribute( "entrepotList", entrepotList );

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        if (logger.isInfoEnabled()){

            logger.info("Appel de la méthode \"doPost\" de la servlet \"AccueilServlet\"");
        }
    }
}
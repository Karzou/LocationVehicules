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
        String nomEntrepot = Validation.upperCase(request.getParameter("nomEntrepot"));
        int nombrePlaceEntrepot = Integer.parseInt(request.getParameter("nombrePlace"));
        String rueEntrepot = request.getParameter("rue");
        String numeroEntrepot = request.getParameter("numero");
        String boiteEntrepot = request.getParameter("boite");
        int idVilleEntrepot = Integer.parseInt(request.getParameter("idVille"));

        // Instanciation
        EntrepotService entrepotService = new EntrepotService(em);
        VilleService villeService = new VilleService(em);
        Ville ville = null;

        try {

            ville = villeService.trouver(idVilleEntrepot);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        Adresse adresse = new Adresse(rueEntrepot, numeroEntrepot, boiteEntrepot, ville);
        Entrepot entrepot = new Entrepot(nomEntrepot, nombrePlaceEntrepot, adresse);

        try {

            transaction.begin();

            entrepotService.creer(entrepot);

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

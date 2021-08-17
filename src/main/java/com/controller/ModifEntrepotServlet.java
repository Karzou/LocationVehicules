package com.controller;

import com.connection.EMF;
import com.entity.Entrepot;
import com.entity.Ville;
import com.exception.ServiceException;
import com.service.EntrepotService;
import com.service.VilleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/modifEntrepot")
public class ModifEntrepotServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ModifEntrepotServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"ModifEntrepotServlet\"");
        }

        EntityManager em = EMF.getEM();

        int idModif = (int) request.getSession().getAttribute("idModif");
        int idVille = (int) request.getSession().getAttribute("idVille");

        EntrepotService entrepotService = new EntrepotService(em);
        VilleService villeService = new VilleService(em);
        Entrepot entrepot = null;
        Ville ville = null;
        List<Ville> villeList = null;

        try {

            villeList = villeService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            ville = villeService.trouver(idVille);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            entrepot = entrepotService.trouver(idModif);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("entrepot", entrepot);
        request.setAttribute("ville", ville);
        request.setAttribute("villeList", villeList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifEntrepot.jsp" ).forward( request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"ModifEntrepotServlet\"");
        }

        EntityManager em = EMF.getEM();

        int id = Integer.parseInt(request.getParameter("idModif"));
        int idVille = Integer.parseInt(request.getParameter("idVille"));

        EntrepotService entrepotService = new EntrepotService(em);
        VilleService villeService = new VilleService(em);
        Entrepot entrepot = null;
        Ville ville = null;
        List<Ville> villeList = null;

        try {

            villeList = villeService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            ville = villeService.trouver(idVille);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            entrepot = entrepotService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("entrepot", entrepot);
        request.setAttribute("ville", ville);
        request.setAttribute("villeList", villeList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifEntrepot.jsp" ).forward( request, response);
    }
}

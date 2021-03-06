package com.controller;

import com.connection.EMF;
import com.entity.Marque;
import com.exception.ServiceException;
import com.service.MarqueService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/modifMarque")
public class ModifMarqueServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ModifMarqueServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"ModifMarqueServlet\"");
        }

        EntityManager em = EMF.getEM();

        int id = (int) request.getSession().getAttribute("idMarque");

        MarqueService marqueService = new MarqueService(em);
        Marque marque = null;

        try {

            marque = marqueService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("marque", marque);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifMarque.jsp" ).forward( request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"ModifMarqueServlet\"");
        }

        EntityManager em = EMF.getEM();

        int id = Integer.parseInt(request.getParameter("idModif"));

        MarqueService marqueService = new MarqueService(em);
        Marque marque = null;

        try {

            marque = marqueService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("marque", marque);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifMarque.jsp" ).forward( request, response);
    }
}

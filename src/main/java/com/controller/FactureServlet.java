package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.entity.Facture;
import com.exception.ServiceException;
import com.service.CouleurService;
import com.service.FactureService;
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

@WebServlet("/facture")
public class FactureServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(FactureServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("je passe par la page facture");
        EntityManager em = EMF.getEM();

        FactureService factureService = new FactureService(em);
        List<Facture> factureList = null;
        try {
            factureList = factureService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        } finally {
            if(logger.isInfoEnabled()) {
                logger.info("Fermeture de l'EntityManager");
            }
            em.close();
        }

        request.setAttribute("factureList", factureList);
        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/facture.jsp" ).forward( request, response );
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

package com.controller;

import com.connection.EMF;
import com.entity.Facture;
import com.entity.Marque;
import com.entity.Modele;
import com.exception.ServiceException;
import com.service.FactureService;
import com.service.MarqueService;
import com.service.ModeleService;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

/**
 * @author Deschamps Jérôme
 */

@WebServlet("/modifFacture")

public class ModifFactureServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ModifMarqueServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"ModifFactureServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {
            logger.info("Appel de la methode doPost de ModifFactureServlet.");
        }

        EntityManager em = EMF.getEM();

        int id = Integer.parseInt(request.getParameter("idModif"));


        FactureService factureService = new FactureService(em);
        Facture facture = null;

        try {
            facture = factureService.findById(id);
            if (request.getParameterMap().containsKey("prixFacture")) {
                Float price = Float.parseFloat(request.getParameter("prixFacture"));
                facture.setPrixFacture(price);
            }
            if (request.getParameterMap().containsKey("dateFacture")) {
                String dateFactureString = request.getParameter("dateFacture");
                try {
                    java.util.Date dateFormatedUtil = new SimpleDateFormat("yyyy-MM-dd").parse(dateFactureString);
                    java.sql.Date dateFormatedSql = new java.sql.Date(dateFormatedUtil.getTime()); //your SQL date object
                    facture.setDateFacture(dateFormatedSql);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            if (request.getParameterMap().containsKey("prixFacture")
                    || request.getParameterMap().containsKey("dateFacture")) {
                factureService.update(facture);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        } finally {

            if (logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("facture", facture);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifFacture.jsp").forward(request, response);

    }
}

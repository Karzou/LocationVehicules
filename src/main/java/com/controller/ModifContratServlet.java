package com.controller;

import com.connection.EMF;
import com.entity.Contrat;
import com.entity.Marque;
import com.entity.Modele;
import com.exception.ServiceException;
import com.service.ContratService;
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

@WebServlet("/modifContrat")

public class ModifContratServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ModifMarqueServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        if (logger.isInfoEnabled())
        {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"ModifContratServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {
            logger.info("Appel de la methode doPost de ModifContratServlet.");

    }
    EntityManager em = EMF.getEM();

    int id = Integer.parseInt(request.getParameter("idModif"));


        ContratService contratService = new ContratService(em);
    Contrat contract = null;

        try
        {
            contract = contratService.findById(id);

        if (request.getParameterMap().containsKey("acompte"))
        {
            Float acompte = Float.parseFloat(request.getParameter("acompte"));
            contract.setAcompte(acompte);
        }


        if (request.getParameterMap().containsKey("acompte")) {
            contratService.update(contract);
        }
    } catch (ServiceException e) {
        e.printStackTrace();
    } finally {

        if (logger.isInfoEnabled()) {

            logger.info("Fermeture de l'EntityManager");
        }

        em.close();
    }

        request.setAttribute("contract", contract);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifContrat.jsp").forward(request, response);

}



        }
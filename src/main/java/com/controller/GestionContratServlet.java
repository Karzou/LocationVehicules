package com.controller;

import com.connection.EMF;
import com.entity.Contrat;
import com.entity.Facture;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/gestionContrat")
public class GestionContratServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionMarqueModeleServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"GestionContratServlet\"");
        }

        EntityManager em = EMF.getEM();

        ContratService contratService = new ContratService(em);
        List<Contrat> contratList = new ArrayList<>();
        try
        {
            contratList = contratService.findAll();
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }

        finally {

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }


       request.setAttribute("contratList", contratList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionContrat.jsp").forward( request, response );
    }

}
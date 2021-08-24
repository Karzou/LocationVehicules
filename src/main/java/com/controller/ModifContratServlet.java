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
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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

        EntityManager em = EMF.getEM();
        //faire rollback si transaction se passe pas correctement
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données du champ de la jsp
        HttpSession session = request.getSession();
        int idContrat = (int) session.getAttribute("idModif");



        // Instanciation
        //appel toutes les méthodes dans facture service
        ContratService contratService = new ContratService(em);
        Contrat contrat = null;

        try
        {
            contrat = contratService.findById(idContrat);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (logger.isInfoEnabled())
            {
                logger.info("Fermeture de l'EntityManager");
            }
            em.close();
        }

        request.setAttribute("contrat", contrat);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifContrat.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled())
        {
            logger.info("Appel de la methode doPost de ModifFactureServlet.");
        }

        EntityManager em = EMF.getEM();
        //faire rollback si transaction se passe pas correctement
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données du champ de la jsp
        String strIdModif = request.getParameter("idModif");


        // Instanciation
        //appel toutes les méthodes dans facture service
        ContratService contratService = new ContratService(em);
        Contrat contrat = null;

        try
        {
            int idContrat = Integer.parseInt(strIdModif);
            contrat = contratService.findById(idContrat);
        }
        catch (ServiceException e)
        {
            e.printStackTrace();
        }
        finally
        {

            if (logger.isInfoEnabled())
            {
                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("contrat", contrat);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifContrat.jsp").forward(request, response);

    }
}
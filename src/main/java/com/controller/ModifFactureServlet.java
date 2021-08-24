package com.controller;

import com.connection.EMF;
import com.entity.Facture;
import com.entity.Marque;
import com.entity.Modele;
import com.exception.ServiceException;
import com.service.FactureService;
import com.service.MarqueService;
import com.service.ModeleService;
import com.service.Validation;
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
import javax.transaction.Transaction;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

        if (logger.isInfoEnabled())
        {
            logger.info("Appel de la methode doGet de ModifFactureServlet.");
        }

        EntityManager em = EMF.getEM();
        //faire rollback si transaction se passe pas correctement
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données du champ de la jsp
        HttpSession session = request.getSession();
        int idFacture = (int) session.getAttribute("idModif");

        // Instanciation
        //appel toutes les méthodes dans facture service
        FactureService factureService = new FactureService(em);
        Facture facture = null;

        try
        {

            facture = factureService.findById(idFacture);
            session.removeAttribute("idModif");
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

        request.setAttribute("facture", facture);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifFacture.jsp").forward(request, response);



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
        FactureService factureService = new FactureService(em);
        Facture facture = null;

       try
       {
            int idFacture = Integer.parseInt(strIdModif);
            facture = factureService.findById(idFacture);
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

        request.setAttribute("facture", facture);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifFacture.jsp").forward(request, response);



    }
}


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
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/gestionFacture")
public class GestionFactureServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionFactureServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"GestionFactureServlet\"");
        }

        EntityManager em = EMF.getEM();

        FactureService factureService = new FactureService(em);
        List<Facture> factureList = null;

        try {
            factureList = factureService.findAll();
        } catch (ServiceException e) {
            e.printStackTrace();
        } finally {

            if (logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("factureList", factureList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionFacture.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (logger.isInfoEnabled())
        {
            logger.info("Appel de la methode doPost de ModifFactureServlet.");
        }

        EntityManager em = EMF.getEM();
        //faire rollback si transaction se passe pas correctement
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données du champ de la jsp
        String strDateFacture = request.getParameter("dateFacture");
        String strPrixFacture = request.getParameter("prixFacture");
        String strIdModif = request.getParameter("idModif");


        // Instanciation
        //appel toutes les méthodes dans facture service
        FactureService factureService = new FactureService(em);
        int strIdModif1 = Integer.parseInt(strIdModif);

        Facture facture = null;

        try
        {

            facture = factureService.findById(strIdModif1);

        }catch (Exception e){}

        boolean errFlag = false;

        if (Validation.checkValueIsEmptyorNull(strDateFacture))
        {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage1", "Veuillez insérer une date pour la facture");

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(strPrixFacture))
        {
            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Veuillez insérer un prix");

            errFlag = true;

        }
        else if (!Validation.checkValueIsIFloat(strPrixFacture))
        {
            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Le prix doit être une valeur décimale");

            errFlag = true;
        }

        if (errFlag)
        {
            response.sendRedirect("modifFacture");
        }
        else
        {
            Date dateFacture = Validation.dateFormat(strDateFacture);
            Float price = Float.parseFloat(strPrixFacture);

            try {
                transaction.begin();


                facture.setDateFacture(dateFacture);
                facture.setPrixFacture(price);
                factureService.update(facture);

                transaction.commit();

            } catch (ServiceException e)
            {
                e.printStackTrace();
            }
            finally
            {

                if (logger.isInfoEnabled()) {

                    logger.info("Fermeture de l'EntityManager");
                }
                em.close();

            }
            response.sendRedirect("gestionFacture");
        }
    }
}

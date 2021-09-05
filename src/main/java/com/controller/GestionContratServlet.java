package com.controller;

import com.connection.EMF;
import com.entity.Contrat;
import com.enumeration.Etat;
import com.exception.ServiceException;
import com.service.*;
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
import java.util.List;

/**
 * @author Deschamps Jérôme
 */

@WebServlet("/gestionContrat")
public class GestionContratServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionMarqueModeleServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"GestionContratServlet\"");
        }

        EntityManager em = EMF.getEM();

        ContratService contratService = new ContratService(em);
        List<Contrat> contratList = null;

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        if (logger.isInfoEnabled())
        {
            logger.info("Appel de la methode doPost de ModifContratServlet.");
        }

        EntityManager em = EMF.getEM();
        //faire rollback si transaction se passe pas correctement
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données du champ de la jsp
        String strAcompte = request.getParameter("acompte");
        String strCaution = request.getParameter("caution");
        String strEtat = request.getParameter("etat");
        String strIdModif = request.getParameter("idModif");


        // Instanciation
        //appel toutes les méthodes dans facture service
        ContratService contratService = new ContratService(em);
        int idModif = Integer.parseInt(strIdModif);
        HttpSession session = request.getSession();
        session.setAttribute("idModif", idModif);

        //objet facture de l'entity Facture à null
        Contrat contrat = null;

        try
        {
            contrat = contratService.findById(idModif);

        }catch (Exception e)
            {

            }

        boolean errFlag = false;

        if (Validation.checkValueIsEmpty(strAcompte))
        {
            session.setAttribute("errMessage1", "Veuillez insérer un montant d'acompte");

            errFlag = true;
        }
            else if (!Validation.checkValueIsIFloat(strAcompte))
             {
                session.setAttribute("errMessage1", "Le prix de l'acompte doit être une valeur décimale");

                 errFlag = true;
             }

        if (Validation.checkValueIsEmpty(strCaution))
        {
            session.setAttribute("errMessage2", "Veuillez insérer un montant d'acompte");

            errFlag = true;

        }
            else if (!Validation.checkValueIsIFloat(strCaution))
            {
                session.setAttribute("errMessage2", "Le prix de la caution doit être une valeur décimale");

                errFlag = true;
            }

        if (errFlag)
        {
            response.sendRedirect("modifContrat");
        }

        else
        {
            Float acompte = Float.parseFloat(strAcompte);
            Float caution = Float.parseFloat(strCaution);
            Etat etat = Etat.valueOf(strEtat);


            try {
                transaction.begin();

                contrat.setAcompte(acompte);
                contrat.setCaution(caution);
                contrat.setEtat(etat);
                contratService.update(contrat);

                transaction.commit();

            } catch (ServiceException e)
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
            response.sendRedirect("gestionContrat");
        }
    }
}
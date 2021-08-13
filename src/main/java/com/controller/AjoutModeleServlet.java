package com.controller;

import com.connection.EMF;
import com.entity.Marque;
import com.entity.Modele;
import com.exception.ServiceException;
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

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/ajoutModele")
public class AjoutModeleServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(AjoutModeleServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"AjoutModeleServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"AjoutModeleServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        int idMarque = Integer.parseInt(request.getParameter("idMarque"));
        String nomModele = request.getParameter("nomModele");

        if (Validation.checkModeleIsEmpty(nomModele)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Veuillez insérer un modèle");

            response.sendRedirect("gestionMarqueModele");

        } else if (!Validation.checkModeleLenght(nomModele)) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage2", "Le modèle doit etre composé d'au minimum 2 caractères et de maximum 50 caractères");

            response.sendRedirect("gestionMarqueModele");
        } else {

            // Instanciation
            MarqueService marqueService = new MarqueService(em);
            ModeleService modeleService = new ModeleService(em);

            if (modeleService.checkModeleExist(nomModele)) {

                HttpSession session = request.getSession();

                session.setAttribute("errMessage2", "Le modèle '" + nomModele + "' existe déjà");
            } else {

                Marque marque = null;

                try {

                    marque = marqueService.trouver(idMarque);
                } catch (ServiceException e) {

                    e.printStackTrace();
                }

                Modele modele = new Modele(nomModele, marque);

                try {

                    transaction.begin();

                    modeleService.creer(modele);

                    transaction.commit();
                } catch (ServiceException e) {

                    e.printStackTrace();
                } finally {

                    if (transaction.isActive()) {

                        transaction.rollback();
                    }

                    if (logger.isInfoEnabled()) {

                        logger.info("Fermeture de l'EntityManager");
                    }

                    em.close();
                }

                String strMarque;

                HttpSession session = request.getSession();

                if (marque == null) {
                    strMarque = "inconnue";
                } else {
                    strMarque = marque.getNomMarque();
                }

                session.setAttribute("succMessage2", "Le modèle '" + nomModele + "' a été ajouté à la marque '" + strMarque + "' avec succès");
            }

            response.sendRedirect("gestionMarqueModele");
        }
    }
}

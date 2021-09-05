package com.controller;

import com.connection.EMF;
import com.entity.Marque;
import com.entity.Modele;
import com.exception.ServiceException;
import com.service.AutoriseService;
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

        HttpSession session = request.getSession();
        AutoriseService autoriseService = new AutoriseService(em);

        if ((autoriseService.hasPermission((int)session.getAttribute("idRole"), "all")) || (autoriseService.hasPermission((int)session.getAttribute("idRole"), "modeles:write"))) {

            // Récupération des données
            String stridMarque = request.getParameter("idMarque");
            String nomModele = request.getParameter("nomModele");

            boolean errFlag = false;

            if (Validation.checkValueIsEmptyorNull(stridMarque)) {

                session.setAttribute("errMessage2", "Veuillez selectionner une marque");

                errFlag = true;
            }

            if (Validation.checkValueIsEmpty(nomModele)) {

                session.setAttribute("errMessage3", "Veuillez insérer un modèle");

                errFlag = true;
            } else if (!Validation.checkValueLenght(nomModele, 2, 50)) {

                session.setAttribute("errMessage3", "Le modèle doit être composé d'au minimum 2 caractères et de maximum 50 caractères");

                errFlag = true;
            }

            if (errFlag) {

                response.sendRedirect("gestionMarqueModele");
            } else {

                int idMarque = Integer.parseInt(stridMarque);

                // Instanciation
                MarqueService marqueService = new MarqueService(em);
                ModeleService modeleService = new ModeleService(em);

                if (modeleService.checkModeleExist(nomModele)) {

                    session.setAttribute("errMessage2", "Le modèle '" + nomModele + "' existe déjà pour cette marque");
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

                    if (marque == null) {
                        strMarque = "inconnue";
                    } else {
                        strMarque = marque.getNomMarque();
                    }

                    session.setAttribute("succMessage2", "Le modèle '" + nomModele + "' a été ajouté à la marque '" + strMarque + "' avec succès");
                }

                response.sendRedirect("gestionMarqueModele");
            }
        } else {

            logger.info("hasPermission non OK");

            session.setAttribute("erreur", "Vous n'avez pas les droits requis ! ");
            session.setAttribute("retour", "/gestionMarqueModele");

            response.sendRedirect("erreur");
        }
    }
}

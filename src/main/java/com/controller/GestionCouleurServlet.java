package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.CouleurService;
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
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/gestionCouleur")
public class GestionCouleurServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionCouleurServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"GestionCouleurServlet\"");
        }

        EntityManager em = EMF.getEM();

        CouleurService couleurService = new CouleurService(em);
        List<Couleur> couleurList = null;

        try {

            couleurList = couleurService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("couleurList", couleurList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionCouleur.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"GestionCouleurServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        AutoriseService autoriseService = new AutoriseService(em);
        HttpSession session = request.getSession();

        int idModif = Integer.parseInt(request.getParameter("idModif"));
        String nomCouleur = request.getParameter("nomCouleur");
        String status = request.getParameter("actifCouleur");

        CouleurService couleurService = new CouleurService(em);
        Couleur couleur = null;

        if ((autoriseService.hasPermission((int)session.getAttribute("idRole"), "all")) || (autoriseService.hasPermission((int)session.getAttribute("idRole"), "couleurs:write"))) {

            if (Validation.checkValueIsEmpty(nomCouleur)) {

                session.setAttribute("errMessage", "Veuillez insérer un nom de couleur");
                session.setAttribute("id", idModif);

                response.sendRedirect("modifCouleur");
            } else if (!Validation.checkValueLenght(nomCouleur,2, 50)) {

                session.setAttribute("errMessage", "Le nom de la couleur doit etre composé d'au minimum 2 caractères et de maximum 50 caractères");
                session.setAttribute("id", idModif);

                response.sendRedirect("modifCouleur");
            } else {

                nomCouleur = Validation.ucFirst(nomCouleur);

                if (couleurService.checkCouleurExist(nomCouleur)) {

                    session.setAttribute("errMessage", "Ce nom de couleur existe déjà");
                    session.setAttribute("id", idModif);

                    response.sendRedirect("modifCouleur");
                } else {

                    try {

                        couleur = couleurService.trouver(idModif);
                    } catch (ServiceException e) {

                        e.printStackTrace();
                    }

                    try {

                        transaction.begin();

                        couleur.setNomCouleur(nomCouleur);

                        if (status == null) {
                            couleur.setActifCouleur(false);
                        } else {
                            couleur.setActifCouleur(true);
                        }

                        couleurService.update(couleur);

                        transaction.commit();
                    } catch (Exception e) {

                        throw new ServletException(e);
                    } finally {

                        if (transaction.isActive()) {

                            transaction.rollback();
                        }

                        if (logger.isInfoEnabled()) {

                            logger.info("Fermeture de l'EntityManager");
                        }

                        em.close();
                    }

                    response.sendRedirect("gestionCouleur");
                }
            }
        } else {

            logger.info("hasPermission non OK");

            session.setAttribute("erreur", "Vous n'avez pas les droits requis ! ");
            session.setAttribute("retour", "/modifCouleur");

            response.sendRedirect("erreur");
            //this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
        }
    }
}

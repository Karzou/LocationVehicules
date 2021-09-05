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
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/gestionMarqueModele")
public class GestionMarqueModeleServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionMarqueModeleServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"GestionMarqueModeleServlet\"");
        }

        EntityManager em = EMF.getEM();

        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        List<Marque> marqueList = null;
        List<Modele> modeleList = null;

        try {

            modeleList = modeleService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            marqueList = marqueService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            if(logger.isInfoEnabled()) {

                logger.info("Fermeture de l'EntityManager");
            }

            em.close();
        }

        request.setAttribute("marqueList", marqueList);
        request.setAttribute("modeleList", modeleList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionMarqueModele.jsp").forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"GestionMarqueModeleServlet\"");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        AutoriseService autoriseService = new AutoriseService(em);
        HttpSession session = request.getSession();

        boolean flagModifMarque = Boolean.parseBoolean(request.getParameter("flagModifMarque"));



        String nomMarque = request.getParameter("nomMarque");
        String nomModele = request.getParameter("nomModele");

        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        Marque marque = null;
        Modele modele = null;

        if(flagModifMarque) {

            int idModif = Integer.parseInt(request.getParameter("idModif"));

            try {

                marque = marqueService.trouver(idModif);
            } catch (ServiceException e) {

                e.printStackTrace();
            }

            if ((autoriseService.hasPermission((int)session.getAttribute("idRole"), "all")) || (autoriseService.hasPermission((int)session.getAttribute("idRole"), "marques:write"))) {

                if (Validation.checkValueIsEmpty(nomMarque)) {

                    session.setAttribute("errMessage", "Veuillez insérer une marque");
                    session.setAttribute("idMarque", idModif);

                    response.sendRedirect("modifMarque");
                } else if (!Validation.checkValueLenght(nomMarque, 2, 50)) {

                    session.setAttribute("errMessage", "La marque doit être composé d'au minimum 2 caractères et de maximum 50 caractères");
                    session.setAttribute("idMarque", idModif);

                    response.sendRedirect("modifMarque");
                } else {

                    nomMarque = nomMarque.toUpperCase();

                    if (marqueService.checkMarqueExist(nomMarque)) {

                        session.setAttribute("errMessage", "Cette marque existe déjà");
                        session.setAttribute("idMarque", idModif);

                        response.sendRedirect("modifMarque");
                    } else {

                        try {

                            marque.setNomMarque(nomMarque);

                            transaction.begin();

                            marqueService.update(marque);

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

                        response.sendRedirect("gestionMarqueModele");
                    }
                }
            } else {

                logger.info("hasPermission non OK");

                session.setAttribute("erreur", "Vous n'avez pas les droits requis ! ");
                session.setAttribute("retour", "/modifMarque");

                response.sendRedirect("erreur");
                //this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
            }
        } else {

            int idModif = Integer.parseInt(request.getParameter("idModif"));
            int idMarque = Integer.parseInt(request.getParameter("idMarque"));

            try {

                modele = modeleService.trouver(idModif);
            } catch (ServiceException e) {

                e.printStackTrace();
            }

            if ((autoriseService.hasPermission((int)session.getAttribute("idRole"), "all")) || (autoriseService.hasPermission((int)session.getAttribute("idRole"), "modeles:write"))) {

                if (Validation.checkValueIsEmpty(nomModele)) {

                    session.setAttribute("errMessage", "Veuillez insérer un modèle");
                    session.setAttribute("idMarque", idMarque);

                    response.sendRedirect("modifModele");
                } else if (!Validation.checkValueLenght(nomModele, 2, 50)) {

                    session.setAttribute("errMessage", "Le modèle doit être composé d'au minimum 2 caractères et de maximum 50 caractères");
                    session.setAttribute("idMarque", idMarque);

                    response.sendRedirect("modifModele");
                } else {

                    if (modeleService.checkModeleExist(nomModele)) {

                        session.setAttribute("errMessage", "Ce modèle existe déjà");
                        session.setAttribute("idMarque", idMarque);

                        response.sendRedirect("modifModele");
                    } else {

                        try {

                            modele.setNomModele(nomModele);

                            transaction.begin();

                            modeleService.update(modele);

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

                        response.sendRedirect("gestionMarqueModele");
                    }
                }
            } else {

                logger.info("hasPermission non OK");

                session.setAttribute("erreur", "Vous n'avez pas les droits requis ! ");
                session.setAttribute("retour", "/modifModele");

                response.sendRedirect("erreur");
            }
        }
    }
}

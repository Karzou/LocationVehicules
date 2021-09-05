package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.UtilisateurService;
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
 * @author Vanconingsloo Kevin
 */

@WebServlet("/changerMotDePasse")
public class ChangerMotDePasseServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ChangerMotDePasseServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel de la méthode doPost ChangerMotDePasseServlet");
        }

        HttpSession session = request.getSession();
        String password = request.getParameter("password").trim();
        String confirmPassword = request.getParameter("confirmPassword").trim();
        String idModif = request.getParameter("idModif");
        int idUtilisateur = Integer.parseInt(idModif);
        String message = "";

        if(!Validation.checkValueLenght(password, 3, 255)){
            if(logger.isInfoEnabled()){
                logger.info("Problème lors de la validation de mot de passe");
            }
            message = ("Le mot de passe doit être de minimum 4 caractères ! ");
            session.setAttribute("erreur", "Veuillez remplir tous les champs convenablement! " + message);
            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
        }else{
            if(!confirmPassword.equals(password)){
                if(logger.isInfoEnabled()){
                    logger.info("Problème, les 2 mots de passe ne sont pas identiques. ");
                }
                session.setAttribute("erreur", "Les 2 mots de passes ne sont pas identiques ! ");
                session.setAttribute("retour", "/changerMotDePasse");
                this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
            }else{
                EntityManager em = EMF.getEM();
                if(logger.isInfoEnabled()){
                    logger.info("Ouverture em changer mot de passe do post");
                }
                EntityTransaction transaction = em.getTransaction();

                UtilisateurService utilisateurService = new UtilisateurService(em);
                Utilisateur utilisateur = new Utilisateur();
                AutoriseService autoriseService = new AutoriseService(em);

                try {
                    utilisateur = utilisateurService.trouver(idUtilisateur);

                    if(autoriseService.hasPermission(utilisateur.getRolesByIdRole().getIdRole(), "utilisateurs:write") || (int)session.getAttribute("idUtilisateur") == utilisateur.getIdUtilisateur()){
                        if(logger.isInfoEnabled()){
                            logger.info("hasPermission OK " + utilisateur.getEmail());
                        }
                        transaction.begin();
                        utilisateur.setMotDePasse(password.trim());
                        transaction.commit();

                    }else{
                        if(logger.isInfoEnabled()){
                            logger.info("hasPermission non OK " + utilisateur.getEmail());
                        }
                        session.setAttribute("erreur", "Vous n'avez pas les droits requis ! ");
                        session.setAttribute("retour", "/changerMotDePasse");
                        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
                    }

                } catch (ServiceException e) {
                    e.printStackTrace();
                }finally {
                    if (transaction.isActive()) {
                        logger.warn("Rollback de la modification du mot de passe en db.");
                        transaction.rollback();
                    }
                    em.close();
                    if(logger.isInfoEnabled()){
                        logger.info("Fermeture em changer mot de passe do post");
                    }
                }
                request.setAttribute("utilisateur", utilisateur);
                request.setAttribute("succes", "Votre mot de passe a été changé avec succès ! ");
                if(logger.isInfoEnabled()){
                    logger.info("Mot de passe changé avec succès. " + idUtilisateur);
                }
                this.getServletContext().getRequestDispatcher( "/WEB-INF/view/profil.jsp" ).forward( request, response );
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){

            logger.info("Appelle de la méthode doGet ChangerMotDePasseServlet");
        }

        EntityManager em = EMF.getEM();
        if(logger.isInfoEnabled()){
            logger.info("Ouverture em changer mot de passe do get");
        }

        HttpSession session = request.getSession();
        session.removeAttribute("erreur");
        Utilisateur utilisateur = null;
        UtilisateurService utilisateurService = new UtilisateurService(em);

        try {
            utilisateur = utilisateurService.trouver((Integer)session.getAttribute("idUtilisateur"));
        } catch (ServiceException e) {
            if(logger.isInfoEnabled()){
                logger.info("Problème lors de la recherche de l'utilisateur : " + session.getAttribute("idUtilisateur"));
            }
            session.setAttribute("erreur", "Problème avec le chargement de l'utilisateur. " + e.getMessage());
        }

        em.close();
        if(logger.isInfoEnabled()){
            logger.info("Fermeture em changer mot de passe do get");
        }

        request.setAttribute("utilisateur", utilisateur);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/changerMotDePasse.jsp" ).forward( request, response );
    }
}

package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.UtilisateurService;
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
@WebServlet("/supUtilisateur")
public class SupUtilisateurServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(SupUtilisateurServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel de la methode doPost de la servlet SupUtilisateur");
        }

        EntityManager em = EMF.getEM();
        if(logger.isInfoEnabled()){
            logger.info("Ouverture em supUtilisateur dopost");
        }
        EntityTransaction transaction = em.getTransaction();
        HttpSession session = request.getSession(true);
        AutoriseService autoriseService = new AutoriseService(em);

        if(autoriseService.hasPermission((Integer)session.getAttribute("idRole"), "all")  || autoriseService.hasPermission((Integer)session.getAttribute("idRole"), "utilisateurs:write")) {
            if(logger.isInfoEnabled()){
                logger.info("Permission ok");
            }
            String id = request.getParameter("idSup");
            int idSup = Integer.parseInt(id);
            UtilisateurService utilisateurService = new UtilisateurService(em);
            Utilisateur utilisateur = null;

            try {
                if(logger.isInfoEnabled()){
                    logger.info("Import de l'utilisateur " + idSup);
                }
                utilisateur = utilisateurService.trouver(idSup);
            } catch (ServiceException e) {
                logger.warn("Problème lors de l' 'import de l'utilisateur : " + idSup + ". " + e);
                session.setAttribute("erreur", "erreur lors de l'import de la liste de permission. ");
            }
            if (utilisateur.getEmail().equals("admin@admin.com")){
                String message = "Vous ne pouvez pas supprimer cet utilisateur !";
                session.setAttribute("erreur", message);
                String retour = "/gestionUtilisateur";
                session.setAttribute("retour", retour);
                response.sendRedirect("gestionUtilisateur");
               // this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
            }else{
                try {
                    transaction.begin();
                    if(logger.isInfoEnabled()){
                        logger.info("Début de la transaction de suppression de l'utilisateur : " + utilisateur.getEmail());
                    }
                    if (utilisateur.isActifUtilisateur())
                    {
                        utilisateurService.suppressionLogique(utilisateur);
                    }else{
                        utilisateurService.activationLogique(utilisateur);
                    }

                    transaction.commit();
                } catch ( Exception e ) {
                    logger.warn("Problème lors de la suppression de l'utilisateur : " + utilisateur.getEmail() + ". " + e);
                    throw new ServletException( e );
                } finally {
                    if (transaction.isActive()) {
                        logger.warn("Rollback de la suppression de l'utilisateur : " + utilisateur.getEmail());
                        transaction.rollback();
                    }
                }
                response.sendRedirect("gestionUtilisateur");
            }
        } else {
            if(logger.isInfoEnabled()){
                logger.info("Permission non accordée pour la suppression d'utilisateur");
            }
            String message = "Vous n'avez pas les permissions pour supprimer un utilisateur !";
            request.setAttribute("errorMessage", message);
            String retour = "/gestionUtilisateur";
            request.setAttribute("retour", retour);

            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
        }
        em.close();
        if(logger.isInfoEnabled()){
            logger.info("Fermeture em supUtilisateur dopost");
        }
    }
}

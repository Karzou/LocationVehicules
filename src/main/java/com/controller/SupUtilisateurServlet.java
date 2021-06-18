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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Appel de la methode doGet de SupUtilisateurServlet.");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Appel de la methode doPost de SupUtilisateurServlet.");

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        HttpSession session = request.getSession(true);

        AutoriseService autoriseService = new AutoriseService(em);

        if(autoriseService.hasPermission((Integer)session.getAttribute("idRole"), "all")  || autoriseService.hasPermission((Integer)session.getAttribute("idRole"), "utilisateurs:write")) {
            logger.info("Permission all ou utilisateur:write ok. ");
            String id = request.getParameter("idSup");
            int idSup = Integer.parseInt(id);


            UtilisateurService utilisateurService = new UtilisateurService(em);
            Utilisateur utilisateur = null;

            try {
                logger.info("Import de l utilisateur : " + idSup);
                utilisateur = utilisateurService.trouver(idSup);

            } catch (ServiceException e) {
                logger.warn("Probleme lors de l import de l utilisateur : " + idSup + ". " + e);
                session.setAttribute("erreur", "erreur lors de l'import de la liste de permission. ");
            }

            try {
                transaction.begin();
                logger.info("Debut de la transaction de suppression de l utilisateur : " + utilisateur.getEmail());
                utilisateurService.suppressionLogique(utilisateur);

                transaction.commit();
            } catch ( Exception e ) {
                logger.warn("Probleme lors de la suppression de l utilisateur : " + utilisateur.getEmail() + ". " + e);
                throw new ServletException( e );
            } finally {
                if (transaction.isActive()) {
                    logger.warn("Rollback de la suppression de l utilisateur : " + utilisateur.getEmail());
                    transaction.rollback();
                }
                em.close();
            }
            response.sendRedirect("gestionUtilisateur");
        } else {
            logger.info("Permissions non accordée pour la suppression d utilisateur.");

            String message = "Vous n'avez pas les permissions pour supprimer un utilisateur !";
            request.setAttribute("errorMessage", message);
            String retour = "/gestionUtilisateur";
            request.setAttribute("retour", retour);

            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
        }
    }
}

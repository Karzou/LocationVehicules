package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.entity.Ville;
import com.exception.ServiceException;
import com.service.UtilisateurService;
import com.service.VilleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Vanconingsloo Kevin
 */
@WebServlet("/modifUtilisateur")
public class ModifUtilisateurServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ModifUtilisateurServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(logger.isInfoEnabled()){
            logger.info("Appel de la méthode doget de ModifUtilisateurServlet");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel de la méthode dopost de ModifUtilisateurServlet");
        }

        HttpSession session = request.getSession();
        session.removeAttribute("erreur");

        EntityManager em = EMF.getEM();
        if(logger.isInfoEnabled()){
            logger.info("Ouverture em Modif utilisateur dopost");
        }
        int id = Integer.parseInt(request.getParameter("idModif"));
        String profilFlag = request.getParameter("profilFlag");
        UtilisateurService utilisateurService = new UtilisateurService(em);
        VilleService villeService = new VilleService(em);
        List<Ville> villeList = null;
        Utilisateur utilisateur = null;

        try {
            if(logger.isInfoEnabled()){
                logger.info("Import de la liste ville");
            }
            villeList = villeService.lister();
        } catch (ServiceException e) {
            logger.warn("Problème lors de l' 'import de la liste ville. " + e);
            session.setAttribute("erreur", "Problème avec le chargement de la liste de ville. ");
        }

        try {
            if(logger.isInfoEnabled()){
                logger.info("Imprt de l'utilisateur : " + id);
            }
            utilisateur = utilisateurService.trouver(id);
        } catch (ServiceException e) {
            logger.warn("Problème lors de l' 'import de l' 'utilisateur : " + e);
            session.setAttribute("erreur", "Problème avec le chargement de l'utilisateur'.");
        } finally {
            em.close();
            if(logger.isInfoEnabled()){
                logger.info("Fermeture em gestion utilisateur do post");
            }
        }
        request.setAttribute("villes", villeList);
        request.setAttribute("utilisateur", utilisateur);
        if (profilFlag != null){
            request.setAttribute("profilFlag", profilFlag);
        }

        if(!(session.getAttribute("erreur") == null)){
            session.setAttribute("retour", "/gestionUtilisateur");
            response.sendRedirect("erreur");
        }else{
            if(logger.isInfoEnabled()){
                logger.info("Tout ok redirection vers modifUtilisateur");
            }
            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/modifUtilisateur.jsp" ).forward( request, response );
        }
    }
}

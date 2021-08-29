package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.service.UtilisateurService;
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

@WebServlet("/profil")
public class ProfilServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ProfilServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {
            logger.info("Appel de la methode doGet de ProfilServlet");
        }

        EntityManager em = EMF.getEM();
        if(logger.isInfoEnabled()){
            logger.info("Ouverture em profil do get");
        }

        HttpSession session = request.getSession();
        session.removeAttribute("erreur");
        UtilisateurService utilisateurService = new UtilisateurService(em);
        Utilisateur utilisateur = null;

        try {
            utilisateur = utilisateurService.trouver((int)session.getAttribute("idUtilisateur"));
        } catch (ServiceException e) {
            session.setAttribute("erreur", "Problème avec le chargement de l'utilisateur. " + e.getMessage());
        }
        request.setAttribute("utilisateur", utilisateur);

        if (session.getAttribute("erreur") != null){
            session.setAttribute("retour", "/profil");
            response.sendRedirect("erreur");
        } else {
            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/profil.jsp" ).forward( request, response );

            if (session.getAttribute("succes") != null) {
                session.removeAttribute("succes");
            }
        }
    }

}

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
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appelle de la methode doGet servletLogin");
        }

        EntityManager em = EMF.getEM();

        VilleService villeService = new VilleService(em);
        List<Ville> villeList = null;
        try {
            villeList = villeService.lister();
        } catch (ServiceException e) {
            logger.warn("Probleme avec l import des villes. " + e);
        }

        request.setAttribute("errMessage", null);
        request.setAttribute("villes", villeList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/login.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appelle de la methode doPost servletLogin");
        }

        EntityManager em = EMF.getEM();

        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UtilisateurService utilisateurService = new UtilisateurService(em);
        Utilisateur utilisateur = null;

        VilleService villeService = new VilleService(em);
        List<Ville> villeList = null;
        try {
            villeList = villeService.lister();
        } catch (ServiceException e) {
            logger.warn("Probleme lors de l import des villes. " + e);
        }

        request.setAttribute("villes", villeList);

        if(utilisateurService.checkLogin(userName, password)) {
            if(logger.isInfoEnabled()){
                logger.info("CheckLogin OK " + userName);
            }

            try {
                utilisateur = utilisateurService.trouverParEmail(userName);
            } catch ( ServiceException e) {
                logger.warn("Probleme de trouver le mail " + userName + " en db. " + e);
            } finally {

                em.close();
            }

            HttpSession session = request.getSession();

            session.setAttribute("role", utilisateur.getRolesByIdRole().getRoleDescription());
            session.setAttribute("prenomUtilisateur", utilisateur.getPrenomUtilisateur());
            session.setAttribute("idRole", utilisateur.getRolesByIdRole().getIdRole());
            session.setAttribute("idUtilisateur", utilisateur.getIdUtilisateur());

            response.sendRedirect("accueil");

        } else {
            if(logger.isInfoEnabled()){
                logger.info("Probleme du mail ou password erronne. " + userName);
            }

            request.setAttribute("errMessage", "Votre mail ou mot de passe est erroné.");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }
}

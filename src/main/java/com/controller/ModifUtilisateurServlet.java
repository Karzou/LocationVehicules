package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.entity.Ville;
import com.exception.ServiceException;
import com.service.UtilisateurService;
import com.service.VilleService;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.removeAttribute("erreur");

        EntityManager em = EMF.getEM();


        int id = Integer.parseInt(request.getParameter("idModif"));
        String profilFlag = request.getParameter("profilFlag"); // pour envoyer au profil si on vient du profil

        UtilisateurService utilisateurService = new UtilisateurService(em);
        VilleService villeService = new VilleService(em);
        List<Ville> villeList = null;
        Utilisateur utilisateur = null;

        try {
            villeList = villeService.lister();
        } catch (ServiceException e) {
            session.setAttribute("erreur", "Probleme avec le chargement de la liste de ville. " + e.getMessage());
        }

        try {
            utilisateur = utilisateurService.trouver(id);
        } catch (ServiceException e) {
            session.setAttribute("erreur", "Probleme avec le chargement de l'utilisateur'. " + e.getMessage());
        } finally {
            em.close();
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
            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/modifUtilisateur.jsp" ).forward( request, response );
        }
    }
}

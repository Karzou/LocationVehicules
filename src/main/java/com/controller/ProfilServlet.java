package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.service.UtilisateurService;

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        HttpSession session = request.getSession();
        session.removeAttribute("erreur");
        Utilisateur utilisateur = null;
        UtilisateurService utilisateurService = new UtilisateurService(em);

        try {
            utilisateur = utilisateurService.trouver((Integer)session.getAttribute("idUtilisateur"));
        } catch (ServiceException e) {
            session.setAttribute("erreur", "Problème avec le chargement de l'utilisateur. " + e.getMessage());
        }

        request.setAttribute("utilisateur", utilisateur);

        if(session.getAttribute("erreur") != null){
            session.setAttribute("retour", "/profil");
            response.sendRedirect("erreur");
        }else{
            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/profil.jsp" ).forward( request, response );
        }
    }
}

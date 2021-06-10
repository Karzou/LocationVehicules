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

/**
 * @author Vanconingsloo Kevin
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/login.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        UtilisateurService utilisateurService = new UtilisateurService(em);
        Utilisateur utilisateur = null;

        if(utilisateurService.checkLogin(userName, password)) {

            try {

                utilisateur = utilisateurService.trouverParEmail(userName);
            } catch ( ServiceException e) {

                e.printStackTrace();
            } finally {

                em.close();
            }

            HttpSession session = request.getSession();

            session.setAttribute("role", utilisateur.getRolesByIdRole().getRoleDescription());
            session.setAttribute("idRole", utilisateur.getRolesByIdRole().getIdRole());
            session.setAttribute("idUtilisateur", utilisateur.getIdUtilisateur());

            if(utilisateur.getRolesByIdRole().getRoleDescription().equals("admin")) {

                response.sendRedirect("accueil");
            } else if(utilisateur.getRolesByIdRole().getRoleDescription().equals("employe")) {

                response.sendRedirect("employe");
            } else {

                response.sendRedirect("accueil");
            }
        } else {

            request.setAttribute("errMessage", "Votre mail ou mot de passe est erroné");
            request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
        }
    }
}

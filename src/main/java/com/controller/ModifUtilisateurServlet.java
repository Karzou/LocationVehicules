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

@WebServlet(name = "ModifUtilisateurServlet")
public class ModifUtilisateurServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        HttpSession session = request.getSession(true);

        if (null == session.getAttribute("idUtilisateur")) {

            response.sendRedirect("login");
        } else {

            String idReq = request.getParameter("idModif");
            int id = Integer.parseInt(idReq);

            UtilisateurService utilisateurService = new UtilisateurService(em);
            Utilisateur utilisateur = null;

            try {

                utilisateur = utilisateurService.trouver(id);
            } catch (ServiceException e) {

                e.printStackTrace();
            } finally {

                em.close();
            }

            request.setAttribute("utilisateur", utilisateur);

            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/modifUtilisateur.jsp" ).forward( request, response );
        }
    }
}

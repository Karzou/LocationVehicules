package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.UtilisateurService;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;
/**
 * @author Vanconingsloo Kevin
 */

@WebServlet(name = "SupUtilisateurServlet")
public class SupUtilisateurServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        HttpSession session = request.getSession(true);

        if (null == session.getAttribute("idUtilisateur")) {

            response.sendRedirect("login");
        } else {

            AutoriseService autoriseService = new AutoriseService(em);
            if(autoriseService.hasPermission((Integer)session.getAttribute("idRole"), "all")  || autoriseService.hasPermission((Integer)session.getAttribute("idRole"), "utilisateurs:write")) {

                String id = request.getParameter("idSup");
                int idSup = Integer.parseInt(id);

                UtilisateurService utilisateurService = new UtilisateurService(em);
                Utilisateur utilisateur = null;
                List<Utilisateur> utilisateurList = null;
                String message = "";

                try {

                    utilisateur = utilisateurService.trouver(idSup);
                    message = "Supression de l id " + idSup + " !!";
                } catch (ServiceException e) {

                    e.printStackTrace();
                    message = " erreur ";
                }

                try {

                    utilisateurService.suppressionLogique(utilisateur);
                    utilisateurList = utilisateurService.lister();
                } catch (Exception e) {

                    //message erreur
                } finally {

                    em.close();
                }

                //request.setAttribute("utilisateurList", utilisateurList);

                //this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionUtilisateur.jsp").forward(request, response);

                response.sendRedirect("gestionUtilisateur");
            } else {

                String message = "Vous n'avez pas les permissions pour supprimer un utilisateur !";
                request.setAttribute("errorMessage", message);
                String retour = "/gestionUtilisateur";
                request.setAttribute("retour", retour);

                this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
            }
        }
    }
}

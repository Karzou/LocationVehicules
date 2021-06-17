package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.UtilisateurService;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        HttpSession session = request.getSession(true);

        AutoriseService autoriseService = new AutoriseService(em);

        if(autoriseService.hasPermission((Integer)session.getAttribute("idRole"), "all")  || autoriseService.hasPermission((Integer)session.getAttribute("idRole"), "utilisateurs:write")) {

            String id = request.getParameter("idSup");
            int idSup = Integer.parseInt(id);


            UtilisateurService utilisateurService = new UtilisateurService(em);
            Utilisateur utilisateur = null;

            try {

                utilisateur = utilisateurService.trouver(idSup);

            } catch (ServiceException e) {
                session.setAttribute("erreur", "erreur lors de l'import de la liste de permission. " + e.getMessage());
            }

            try {
                transaction.begin();

                utilisateurService.suppressionLogique(utilisateur);

                transaction.commit();
            } catch ( Exception e ) {
                throw new ServletException( e );
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
                em.close();
            }
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

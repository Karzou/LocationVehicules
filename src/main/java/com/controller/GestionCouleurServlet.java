package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.exception.ServiceException;
import com.service.CouleurService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/gestionCouleur")
public class GestionCouleurServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        CouleurService couleurService = new CouleurService(em);
        List<Couleur> couleurList = null;

        try {

            couleurList = couleurService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("couleurList", couleurList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionCouleur.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int idModif = Integer.parseInt(request.getParameter("idModif"));
        String status = request.getParameter("actifCouleur");

        CouleurService couleurService = new CouleurService(em);
        Couleur couleur = null;

        try {

            couleur = couleurService.trouver(idModif);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            transaction.begin();

            couleur.setNomCouleur(request.getParameter("nomCouleur"));

            if (status == null) {
                couleur.setActifCouleur(false);
            } else {
                couleur.setActifCouleur(true);
            }

            couleurService.update(couleur);

            transaction.commit();
        } catch ( Exception e ) {

            throw new ServletException( e );
        } finally {

            if (transaction.isActive()) {

                transaction.rollback();
            }

            em.close();
        }

        response.sendRedirect("gestionCouleur");
    }
}

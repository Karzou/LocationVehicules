package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.exception.ServiceException;
import com.service.CouleurService;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wets Jeoffroy
 */
@WebServlet("/modifCouleur")
public class ModifCouleurServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        int id = Integer.parseInt(request.getParameter("idModif"));

        CouleurService couleurService = new CouleurService(em);
        Couleur couleur = null;

        try {

            couleur = couleurService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("couleur", couleur);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifCouleur.jsp" ).forward( request, response);
    }
}

package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.entity.Modele;
import com.exception.ServiceException;
import com.service.CouleurService;
import com.service.ModeleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/supModele")
public class SupModeleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int idSup = Integer.parseInt(request.getParameter("idSup"));

        ModeleService modeleService = new ModeleService(em);
        Modele modele = null;

        try {

            modele = modeleService.trouver(idSup);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            transaction.begin();

            modeleService.suppression(modele);

            transaction.commit();
        } catch ( Exception e ) {

            throw new ServletException( e );
        } finally {

            if (transaction.isActive()) {

                transaction.rollback();
            }

            em.close();
        }

        response.sendRedirect("gestionMarqueModele");
    }
}

package com.controller;

import com.connection.EMF;
import com.entity.Entrepot;
import com.exception.ServiceException;
import com.service.EntrepotService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class GestionEntrepotServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        List<Entrepot> entrepotList = null;
        EntrepotService entrepotService = new EntrepotService(em);

        try {

            entrepotList = entrepotService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("entrepotList", entrepotList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionEntrepot.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        String idReq = request.getParameter("idModif");
        int id = Integer.parseInt(idReq);

        EntrepotService entrepotService = new EntrepotService(em);
        Entrepot entrepot = null;

        try {

            entrepot = entrepotService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        }


        try {

            transaction.begin();

            entrepot.setNomEntrepot(request.getParameter("nomEntrepot"));
            entrepot.setNombrePlace(Integer.parseInt(request.getParameter("nombrePlace")));
            entrepot.getAdressesByIdAdresse().setRue(request.getParameter("rue"));
            entrepot.getAdressesByIdAdresse().setNumero(request.getParameter("numero"));
            entrepot.getAdressesByIdAdresse().setBoite(request.getParameter("boite"));
            entrepot.getAdressesByIdAdresse().getVillesByIdVille().setNomVille(request.getParameter("nomVille"));
            entrepot.getAdressesByIdAdresse().getVillesByIdVille().setCodePostal(request.getParameter("codePostal"));
            entrepot.setActifEntrepot(Boolean.parseBoolean(request.getParameter("actifEntrepot")));

            entrepotService.update(entrepot);

            transaction.commit();
        } catch ( Exception e ) {

            throw new ServletException( e );
        } finally {

            if (transaction.isActive()) {

                transaction.rollback();
            }

            em.close();
        }

        response.sendRedirect("gestionEntrepot");
    }
}

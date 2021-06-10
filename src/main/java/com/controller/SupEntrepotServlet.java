package com.controller;

import com.connection.EMF;
import com.entity.Entrepot;
import com.exception.ServiceException;
import com.service.EntrepotService;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SupEntrepotServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        String id = request.getParameter("idSup");
        int idSup = Integer.parseInt(id);

        EntrepotService entrepotService = new EntrepotService(em);
        Entrepot entrepot = null;
        List<Entrepot>entrepotList = null;
        String message = "";

        try {

            entrepot = entrepotService.trouver(idSup);
            message = "Supression de l id " + idSup + " !!";
        } catch (ServiceException e) {

            e.printStackTrace();
            message = " erreur ";
        }

        try {

            entrepotService.suppressionLogique(entrepot);
            entrepotList = entrepotService.lister();
        } catch (Exception e) {

            //message erreur
        } finally {

            em.close();
        }

        //request.setAttribute("entrepotList", entrepotList);

        //this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionEntrepot.jsp").forward(request, response);

        response.sendRedirect("gestionEntrepot");
    }
}

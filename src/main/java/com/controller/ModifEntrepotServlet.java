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

public class ModifEntrepotServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        String idReq = request.getParameter("idModif");
        int id = Integer.parseInt(idReq);

        EntrepotService entrepotService = new EntrepotService(em);
        Entrepot entrepot = null;

        try {

            entrepot = entrepotService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("entrepot", entrepot);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/modifEntrepot.jsp" ).forward( request, response );
    }
}

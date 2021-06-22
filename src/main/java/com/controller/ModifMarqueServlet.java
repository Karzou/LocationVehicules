package com.controller;

import com.connection.EMF;
import com.entity.Marque;
import com.exception.ServiceException;
import com.service.MarqueService;

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

@WebServlet("/modifMarque")
public class ModifMarqueServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        int id = Integer.parseInt(request.getParameter("idModif"));

        MarqueService marqueService = new MarqueService(em);
        Marque marque = null;

        try {

            marque = marqueService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("marque", marque);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifMarque.jsp" ).forward( request, response);
    }
}

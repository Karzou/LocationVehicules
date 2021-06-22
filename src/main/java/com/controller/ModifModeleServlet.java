package com.controller;

import com.connection.EMF;
import com.entity.Couleur;
import com.entity.Marque;
import com.entity.Modele;
import com.exception.ServiceException;
import com.service.CouleurService;
import com.service.MarqueService;
import com.service.ModeleService;

import javax.persistence.EntityManager;
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
@WebServlet("/modifModele")
public class ModifModeleServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        int id = Integer.parseInt(request.getParameter("idModif"));

        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        Marque marque = null;
        List<Modele> modeleList = null;

        try {

            marque = marqueService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            modeleList = modeleService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("marque", marque);
        request.setAttribute("modeleList", modeleList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifModele.jsp" ).forward( request, response);
    }
}

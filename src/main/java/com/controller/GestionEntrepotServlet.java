package com.controller;

import com.connection.EMF;
import com.entity.Entrepot;
import com.entity.Ville;
import com.exception.ServiceException;
import com.service.EntrepotService;
import com.service.VilleService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

@WebServlet("/gestionEntrepot")
public class GestionEntrepotServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionEntrepotServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        EntrepotService entrepotService = new EntrepotService(em);
        VilleService villeService = new VilleService(em);
        List<Entrepot> entrepotList = null;
        List<Ville> villeList = null;

        try {

            villeList = villeService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            entrepotList = entrepotService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("villeList", villeList);
        request.setAttribute("entrepotList", entrepotList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionEntrepot.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int id = Integer.parseInt(request.getParameter("idModif"));
        int idVille = Integer.parseInt(request.getParameter("idVille"));
        String status = request.getParameter("actifEntrepot");

        EntrepotService entrepotService = new EntrepotService(em);
        VilleService villeService = new VilleService(em);
        Entrepot entrepot = null;
        Ville ville = null;

        try {

            ville = villeService.trouver(idVille);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

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
            entrepot.getAdressesByIdAdresse().setVillesByIdVille(ville);

            if (status == null) {
                entrepot.setActifEntrepot(false);
            } else {
                entrepot.setActifEntrepot(true);
            }

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

package com.controller;

/**
 * @author Wets Jeoffroy
 */

import com.entity.Vehicule;
import com.exception.ServiceException;
import com.connection.EMF;
import com.service.VehiculeService;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "VehicleServlet")
public class VehiculeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        if (null == session.getAttribute("idUtilisateur")) {

            response.sendRedirect("login");
        } else {

            EntityManager em = EMF.getEMF().createEntityManager();
            VehiculeService vehiculeService = new VehiculeService(em);

            List<Vehicule> vehiculeList = null;

            try {

                vehiculeList = vehiculeService.lister();
            } catch (ServiceException e) {

                e.printStackTrace();
            } finally {

                em.close();
            }

            request.setAttribute( "vehiculeList", vehiculeList );

            /* Affichage de la page d'inscription */
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/vehicule.jsp").forward( request, response );
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

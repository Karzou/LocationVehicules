package com.controller;

import com.entity.Vehicule;
import com.exception.ServiceException;
import com.connection.EMF;
import com.service.Validation;
import com.service.VehiculeService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/vehicule")
public class VehiculeServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(VehiculeServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"VehiculeServlet\"");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"VehiculeServlet\"");
        }

        EntityManager em = EMF.getEM();

        VehiculeService vehiculeService = new VehiculeService(em);
        List<Vehicule> vehiculeList = null;
        int periodeLocation = 0;

        String strIdLieuDepart = request.getParameter("LieuDepart");
        String strIdLieuRetour = request.getParameter("LieuRetour");
        String strDateDepart = request.getParameter("dateDepart");
        String strHeureDepart = request.getParameter("heureDepart");
        String strDateRetour = request.getParameter("dateRetour");
        String strHeureRetour = request.getParameter("heureRetour");

        if (strIdLieuDepart.trim().isEmpty() || strIdLieuRetour.trim().isEmpty() || strDateDepart.trim().isEmpty() || strHeureDepart.isEmpty() || strDateRetour.isEmpty() || strHeureRetour.isEmpty()) {

            HttpSession session = request.getSession();

            session.setAttribute("errMessage", "Veuillez remplir tous les champs pour effectuer une recheche de véhicule");

            response.sendRedirect("accueil");

        } else {

            int idLieuDepart = Integer.parseInt(strIdLieuDepart);
            int idLieuRetour = Integer.parseInt(strIdLieuDepart);
            Date dateDepart = Validation.dateFormat(strDateDepart);
            Date dateRetour = Validation.dateFormat(strDateRetour);

            try {

                vehiculeList = vehiculeService.rechercher(idLieuDepart, dateDepart, dateRetour);
                periodeLocation = (int) vehiculeService.periodeLocation(dateDepart, dateRetour);
            } catch (ServiceException e) {

                e.printStackTrace();
            } finally {

                if (logger.isInfoEnabled()) {

                    logger.info("Fermeture de l'EntityManager");
                }

                em.close();
            }

            request.setAttribute("periodeLocation", periodeLocation);
            request.setAttribute("vehiculeList", vehiculeList);
            request.setAttribute("idLieuDepart", idLieuDepart);
            request.setAttribute("idLieuRetour", idLieuRetour);
            request.setAttribute("strDateDepart", strDateDepart);
            request.setAttribute("strHeureDepart", strHeureDepart);
            request.setAttribute("strDateRetour", strDateRetour);
            request.setAttribute("strHeureRetour", strHeureRetour);

            this.getServletContext().getRequestDispatcher("/WEB-INF/view/vehicule.jsp").forward(request, response);
        }
    }
}

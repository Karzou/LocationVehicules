package com.controller;

import com.connection.EMF;
import com.entity.Vehicule;
import com.service.VehiculeService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/optionSup")
public class OptionSupServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(OptionSupServlet.class);

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doGet\" de la servlet \"OptionSupServlet\"");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/optionSup.jsp").forward( request, response );
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel de la méthode \"doPost\" de la servlet \"OptionSupServlet\"");
        }

        String strIdLieuDepart = request.getParameter("idLieuDepart");
        String strIdLieuRetour = request.getParameter("idLieuRetour");
        String strDateDepart = request.getParameter("strDateDepart");
        String strHeureDepart = request.getParameter("strHeureDepart");
        String strDateRetour = request.getParameter("strDateRetour");
        String strHeureRetour = request.getParameter("strHeureRetour");
        String idVehicule = request.getParameter("idVehicule");
        String prixTotal = request.getParameter("prixTotal");

        request.setAttribute("idLieuDepart", strIdLieuDepart);
        request.setAttribute("idLieuRetour", strIdLieuRetour);
        request.setAttribute("DateDepart", strDateDepart);
        request.setAttribute("HeureDepart", strHeureDepart);
        request.setAttribute("DateRetour", strDateRetour);
        request.setAttribute("HeureRetour", strHeureRetour);
        request.setAttribute("idVehicule", idVehicule);
        request.setAttribute("prixTotal", prixTotal);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/optionSup.jsp").forward( request, response );
    }
}
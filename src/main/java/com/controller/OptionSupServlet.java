package com.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        boolean erreurConnBdd = false;

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
        request.setAttribute("erreurConnBdd", erreurConnBdd);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/optionSup.jsp").forward( request, response );
    }
}
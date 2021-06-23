package com.controller;

import com.connection.EMF;
import com.entity.*;
import com.exception.ServiceException;
import com.service.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

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

@WebServlet("/modifVehicule")
public class ModifVehiculeServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(ModifVehiculeServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        int id = Integer.parseInt(request.getParameter("idModif"));
        int idMarque = Integer.parseInt(request.getParameter("idMarque"));

        VehiculeService vehiculeService = new VehiculeService(em);
        OptionVehiculeService optionVehiculeService = new OptionVehiculeService(em);
        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        Vehicule vehicule = null;
        List<OptionVehicule> optionVehiculesList = null;
        Marque marque = null;
        List<Marque> marqueList = null;
        List<Modele> modeleList = null;

        try {

            marqueList = marqueService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            marque = marqueService.trouver(idMarque);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            modeleList = modeleService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            optionVehiculesList = optionVehiculeService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            vehicule = vehiculeService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("marque", marque);
        request.setAttribute("marqueList", marqueList);
        request.setAttribute("modeleList", modeleList);
        request.setAttribute("optionVehiculesList", optionVehiculesList);
        request.setAttribute("vehicule", vehicule);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifVehicule.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        int id = Integer.parseInt(request.getParameter("idModif"));
        int idMarque = Integer.parseInt(request.getParameter("idMarque"));
        boolean modifFlag = Boolean.parseBoolean(request.getParameter("modifFlag"));

        VehiculeService vehiculeService = new VehiculeService(em);
        OptionVehiculeService optionVehiculeService = new OptionVehiculeService(em);
        MarqueService marqueService = new MarqueService(em);
        ModeleService modeleService = new ModeleService(em);
        ContientService contientService = new ContientService(em);
        EntrepotService entrepotService = new EntrepotService(em);
        CouleurService couleurService = new CouleurService(em);
        Vehicule vehicule = null;
        List<OptionVehicule> optionVehiculesList = null;
        Marque marque = null;
        List<Marque> marqueList = null;
        List<Modele> modeleList = null;
        List<Contient> contientList = null;
        List<Entrepot> entrepotList = null;
        List<Couleur> couleurList = null;

        try {

            marqueList = marqueService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            marque = marqueService.trouver(idMarque);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            modeleList = modeleService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            optionVehiculesList = optionVehiculeService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            contientList = contientService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            couleurList = couleurService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            entrepotList = entrepotService.lister();
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            vehicule = vehiculeService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("modifFlag", modifFlag);
        request.setAttribute("marque", marque);
        request.setAttribute("marqueList", marqueList);
        request.setAttribute("modeleList", modeleList);
        request.setAttribute("optionVehiculesList", optionVehiculesList);
        request.setAttribute("contientList", contientList);
        request.setAttribute("entrepotList", entrepotList);
        request.setAttribute("couleurList", couleurList);
        request.setAttribute("vehicule", vehicule);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/modifVehicule.jsp").forward(request, response);
    }
}

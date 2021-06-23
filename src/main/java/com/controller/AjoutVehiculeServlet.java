package com.controller;

import com.connection.EMF;
import com.entity.*;
import com.service.*;
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
import java.sql.Date;
import java.util.Set;

/**
 * @author Wets Jeoffroy
 */

@WebServlet("/ajoutVehicule")
public class AjoutVehiculeServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(AjoutVehiculeServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données
        int idModele = Integer.parseInt(request.getParameter("idModele"));
        int idCouleur = Integer.parseInt(request.getParameter("idCouleur"));
        int idEntrepot = Integer.parseInt(request.getParameter("idEntrepot"));
        int cylindree = Integer.parseInt(request.getParameter("cylindree"));
        int puissance = Integer.parseInt(request.getParameter("puissance"));
        String numChassis = request.getParameter("numChassis");
        String immatriculation = request.getParameter("immatriculation");
        Date dateAchat = Date.valueOf(request.getParameter("dateAchat"));
        float prixJournalier = Float.parseFloat(request.getParameter("prixJournalier"));

        // Instanciation
        VehiculeService vehiculeService = new VehiculeService(em);
        EntrepotService entrepotService = new EntrepotService(em);
        CouleurService couleurService = new CouleurService(em);
        ModeleService modeleService = new ModeleService(em);
        OptionVehiculeService optionVehiculeService = new OptionVehiculeService(em);
        ContientService contientService = new ContientService(em);
        Entrepot entrepot = null;
        Couleur couleur = null;
        Modele modele = null;
        OptionVehicule optionVehicule = null;

        try {

            modele = modeleService.trouver(idModele);
        } catch ( Exception e ) {

            throw new ServletException( e );
        }

        try {

            entrepot = entrepotService.trouver(idEntrepot);
        } catch ( Exception e ) {

            throw new ServletException( e );
        }

        try {

            couleur = couleurService.trouver(idCouleur);
        } catch ( Exception e ) {

            throw new ServletException( e );
        }

        Vehicule vehicule = new Vehicule(numChassis, cylindree, puissance, dateAchat,
                immatriculation, prixJournalier, entrepot, couleur, modele);

        try {

            transaction.begin();

            vehiculeService.creer(vehicule);

            transaction.commit();
        } catch ( Exception e ) {

            throw new ServletException( e );
        } finally {

            if (transaction.isActive()) {

                transaction.rollback();
            }
        }

        try {

            // get all parameter names
            Set<String> paramNames = request.getParameterMap().keySet();

            // iterating over parameter names and get its value
            for (String name : paramNames) {

                if (name.contains("option")) {

                    int idOption = Integer.parseInt(request.getParameter(name));

                    try {

                        optionVehicule = optionVehiculeService.trouver(idOption);
                    } catch ( Exception e ) {

                        throw new ServletException( e );
                    }

                    Contient contient = new Contient(optionVehicule, vehicule);

                    transaction.begin();

                    contientService.creer(contient);

                    transaction.commit();
                }
            }
        } catch ( Exception e ) {

            throw new ServletException( e );
        } finally {

            if (transaction.isActive()) {

                transaction.rollback();
            }

            em.close();
        }

        response.sendRedirect("gestionVehicule");
    }
}

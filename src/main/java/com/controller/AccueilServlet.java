package com.controller;

import com.entity.*;
import com.exception.ServiceException;
import com.connection.EMF;
import com.service.EntrepotService;

import java.io.*;
import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 * @author Wets Jeoffroy
 */

public class AccueilServlet extends HttpServlet {

    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        if (null == session.getAttribute("idUtilisateur")) {

            response.sendRedirect("login");
        } else {

            EntityManager em = EMF.getEMF().createEntityManager();
            EntrepotService entrepotService = new EntrepotService(em);

            List<Entrepot> entrepotList = null;

            try {

                entrepotList = entrepotService.lister();
            } catch (ServiceException e) {

                e.printStackTrace();
            } finally {

                em.close();
            }

            request.setAttribute( "entrepotList", entrepotList );

            /* Affichage de la page d'inscription */
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
        }

    }
    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        /*EntityManager em = EMF.getEMF().createEntityManager();
        Entrepot entrepots = em.find(Entrepot.class, 1);
        if (entrepots != null) {
            request.setAttribute( "Tests", entrepots.getNombrePlace());
        } else {
            request.setAttribute( "Tests", "error");
        }*/

        //request.setAttribute( "Tests", paysManager.compterPays());

        /* Traitement des données du formulaire */
        /*String email = request.getParameter( CHAMP_EMAIL );
        String motDePasse = request.getParameter( CHAMP_PASS );
        String confirmation = request.getParameter( CHAMP_CONF );
        String nom = request.getParameter( CHAMP_NOM );

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmail(email);
        utilisateur.setMotDePasse(motDePasse);
        utilisateur.setConfirmation(confirmation);
        utilisateur.setNom(nom);*/

        /*EntityManager em = EMF.getEMF().createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            //Pays pays = new Pays();
            //pays.setNomPays("Belgique");
            Pays pays = em.find(Pays.class, 3);

            Ville villes = new Ville();
            villes.setNomVille("Namur");
            villes.setCodePostal("5000");
            villes.setPaysByIdPays(pays);

            Adresse adresses = new Adresse();
            adresses.setRue("Rue des lilas");
            adresses.setNumero("54");
            adresses.setVillesByIdVille(villes);

            Entrepot entrepots = new Entrepot();
            entrepots.setNomEntrepot("LOCACAR 2");
            entrepots.setNombrePlace(70);
            entrepots.setActifEntrepot(true);
            entrepots.setAdressesByIdAdresse(adresses);

            em.persist(pays);
            em.persist(villes);
            em.persist(adresses);
            em.persist(entrepots);

            transaction.commit();
        } finally {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            em.close();
        }*/

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        try {

            transaction.begin();

            Marque marque = new Marque();
            marque.setNomMarque("Peugeot");

            Modele modele = new Modele();
            modele.setNomModele("308");
            modele.setActifOption(true);
            modele.setMarquesByIdMarque(marque);

            Couleur couleur = new Couleur();
            couleur.setNomCouleur("Bleu");
            couleur.setActifCouleur(true);

            Entrepot entrepot = em.find(Entrepot.class, 1);

            Vehicule vehicule = new Vehicule();
            vehicule.setNumChassis("XXXXXXXXXXXXXXXXX");
            vehicule.setCylindree(1399);
            vehicule.setPuissance(70);
            vehicule.setDateAchat(Date.valueOf("2010/03/02"));
            vehicule.setImmatriculation("1-CCC-567");
            vehicule.setPrixJournalier(55.95f);
            vehicule.setActifVehicule(true);
            vehicule.setModelesByIdModele(modele);
            vehicule.setCouleursByIdCouleur(couleur);
            vehicule.setEntrepotsByIdEntrepot(entrepot);

            em.persist(marque);
            em.persist(modele);
            em.persist(couleur);
            em.persist(vehicule);

            transaction.commit();
        } finally {

            if (transaction.isActive()) {
                transaction.rollback();
            }

            em.close();
        }

        /*EntityManager em = EMF.getEMF().createEntityManager();
        Entrepot entrepots = em.find(Entrepot.class, 1);
        if (entrepots != null) {
            request.setAttribute( "Tests", entrepots.getAdressesByIdAdresse().getVillesByIdVille().getNomVille());
        } else {
            request.setAttribute( "Tests", "error");
        }*/

        /*EntityManager em = EMF.getEMF().createEntityManager();
        EntrepotDAO entrepotDao = new EntrepotDAO();

        List<Entrepot> entrepotList = null;
        try {
            entrepotList = entrepotDao.lister();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        request.setAttribute( "entrepotList", entrepotList );*/

        //request.setAttribute( "utilisateurs", utilisateur );

        /* Transmission de la paire d'objets request/response à notre JSP */
        HttpSession session = request.getSession(true);

        if (null == session.getAttribute("idUtilisateur")) {

            response.sendRedirect("login");
        }else{

            this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
        }
    }
}
package com.controller;

import com.connection.EMF;
import com.entity.Facture;
import com.entity.Reservation;
import com.exception.ServiceException;
import com.service.FactureService;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/facture")
public class FactureServlet extends HttpServlet {
    final static Logger logger = LogManager.getLogger(FactureServlet.class);

    //doget Méthode pour appeller la page
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Ajout d'une ligne de type info dans le log
        logger.info("je passe par la page facture");

        //EntityManger = le gestionnaire du modèle de ta BD
        //em = nom de l'instance
        //EMF.getEM() provient de la page EMF.java (fourni par le prof). Gère la BD
        EntityManager em = EMF.getEM();

        //objet de type FactureService permettant de faire des traitements sur la DB (ajouter factures, m à j factures,...)
        FactureService factureService = new FactureService(em);

        //list<Facture> => provient de l'entity Facture
        //factureListe => nom donné pour la liste
        //null = on crée une liste null
        //lorsuq'il contiendra des factures (donc plus null)=> sera un objet de type Liste
        List<Facture> factureList = null;
        List<Facture> myFactureList = null;

        HttpSession session = request.getSession();
        Integer idUtilisateur = (Integer) session.getAttribute("idUtilisateur");
        try {
            factureList = factureService.findAll();
            Set<Integer> myFactureIds = new HashSet<>();

            for (Facture facture : factureList) {
                Reservation reservation = facture.getContratsByIdContrat().getReservation();
                if (reservation.getUtilisateursByIdUtilisateur().getIdUtilisateur() == idUtilisateur) {
                    myFactureIds.add(facture.getIdFacture());
                }
            }

            myFactureList = factureList.stream().filter(f ->
                    myFactureIds.contains(f.getIdFacture())
            ).collect(Collectors.toList());
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        //finally, il passe d'office par là
        //dans ce cas, il ajoute une information dans le fichier log et il clos la connexion à la DB
        finally {
            if (logger.isInfoEnabled()) {
                logger.info("Fermeture de l'EntityManager");
            }
            em.close();
        }
        //factureList.get(0).getContratsByIdContrat().getReservationsByIdContrat().get(0).getDateDebutLocation();
        //factureList.get(0).getContratsByIdContrat().getReservationsByIdContrat().
        // ajout d'un attribut s:"factureList sur la réponse de la requête précédente'
        request.setAttribute("factureList", myFactureList);

        //Affichage de la page avec deux élements request, reponse
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/facture.jsp").forward(request, response);
    }

    //utiliser pour un formulaires avec le submit
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}

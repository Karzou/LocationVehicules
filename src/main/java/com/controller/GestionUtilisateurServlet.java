package com.controller;

import com.connection.EMF;
import com.entity.Telephone;
import com.entity.Utilisateur;
import com.entity.Ville;
import com.exception.ServiceException;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * @author Vanconingsloo Kevin
 */
@WebServlet("/gestionUtilisateur")
public class GestionUtilisateurServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionUtilisateurServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel du doGet de la servlet GestionUtilisateurServlet.");
        }

        EntityManager em = EMF.getEM();
        HttpSession session = request.getSession();

        List<Utilisateur> utilisateurList = null;
        UtilisateurService utilisateurService = new UtilisateurService(em);

        try {
            if(logger.isInfoEnabled()){
                logger.info("Importation de la liste utilisateur.");
            }

            utilisateurList = utilisateurService.lister();
        } catch (ServiceException e) {
            logger.warn("Problème lors de l' 'import de la lite utilisateur. " + e);
        }

        request.setAttribute("utilisateurList", utilisateurList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionUtilisateur.jsp" ).forward( request, response );

        if(session.getAttribute("succes") != null){
            session.removeAttribute("succes");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel du doPost de la servlet GestionUtilisateurservlet.");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // recuperation des donnees des champs
        int id = Integer.parseInt(request.getParameter("idModif")) ;
        String nom = request.getParameter("nom");
        String password = request.getParameter("password");
        String prenom = request.getParameter("prenom");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String numero = request.getParameter("numero");
        String boite = request.getParameter("boite");
        String profilFlag = request.getParameter("profilFlag");
        int idVille = Integer.parseInt(request.getParameter("ville"));
        Date dateNaissance = Validation.dateFormat(request.getParameter("dateNaissance"));
        Date datePermis = Validation.dateFormat(request.getParameter("datePermis"));
        boolean erreurFlag = false;
        String message = ".";

        // instanciations
        UtilisateurService utilisateurService = new UtilisateurService(em);
        TelephoneService telephoneService = new TelephoneService(em);
        Telephone telephone1 = new Telephone();
        VilleService villeService = new VilleService(em);
        Utilisateur utilisateur = null;
        Ville ville = null;
        HttpSession session = request.getSession();

                if(!Validation.validationPrenom(nom)) {
                    message += "Veuillez entrer un nom d'une longueur entre 2 et 50 caractères ! ";
                    erreurFlag = true;
                }
                if(!Validation.validationPrenom(prenom)) {
                    message += "Veuillez entrer un prénom d'une longueur entre 2 et 50 caractères ! ";
                    erreurFlag = true;
                }
                if(!Validation.validationTelephone(telephone)){
                    message += "Veuillez entrer que des chiffres d'une longueur entre 8 et 50 caractères! ";
                    erreurFlag = true;
                }

                if(!Validation.validationAdresse(rue)){
                    message += "Veuillez entrer une adresse d'au moins 6 caractères et maximum 100 catactères ! ";
                    erreurFlag = true;
                }

                if(!Validation.validationNumAdresse(numero)){
                    message += "Veuillez entrer un numéro d'adresse valide entre 1 et 10 caractères ! ";
                    erreurFlag = true;
                }
                if(!erreurFlag) {
                    nom = Validation.ucFirst(nom);
                    prenom = Validation.ucFirst(prenom);
                    try {
                        if(logger.isInfoEnabled()){
                            logger.info("Import de la ville : " + idVille);
                        }
                        ville = villeService.trouver(idVille);
                    } catch (ServiceException e) {
                        logger.warn("Problème avec l import de la ville: " + e);
                    }
                    try {
                        utilisateur = utilisateurService.trouver(id);
                    } catch (ServiceException e) {
                        logger.warn("Problème avec l' 'import de l' 'utilisateur : " + id + ". " + e);
                    }
                   try {
                        transaction.begin();
                        if(logger.isInfoEnabled()){
                            logger.info("Début de la transaction de l'update utilisateur. " + utilisateur.getEmail());
                        }

                        telephone1 = telephoneService.trouver(utilisateur.getTelephonesByIdUtilisateur().get(0).getIdTelephone());
                        telephone1.setNumero(telephone);
                        utilisateur.setNomUtilisateur(nom);
                        utilisateur.setPrenomUtilisateur(prenom);
                        utilisateur.setDateNaissance(dateNaissance);
                        utilisateur.setMotDePasse(password);
                        utilisateur.setDatePermis(datePermis);
                        utilisateur.getAdressesByIdAdresse().setBoite(boite);
                        utilisateur.getAdressesByIdAdresse().setNumero(numero);
                        utilisateur.getAdressesByIdAdresse().setRue(rue);
                        utilisateur.getAdressesByIdAdresse().setVillesByIdVille(ville);
                        telephoneService.update(telephone1);
                        utilisateurService.update(utilisateur);

                        transaction.commit();
                    } catch (Exception e) {
                       logger.warn("Problème lors de la mise à jour de l' 'utilisateur. " + e);
                        session.setAttribute("erreur", "Une erreur est survenue lors de l'insertion en db !" );
                    } finally {
                        if (transaction.isActive()) {
                            logger.warn("Rollback de la mise à jour de l'utilisateur. " + utilisateur.getEmail());
                            transaction.rollback();
                        }
                        em.close();
                    }
                }else {
                    if(logger.isInfoEnabled()){
                        logger.info("Les champs de la mise à jour utilisateur ne sont pas remplis. " + message);
                    }

                    session.setAttribute("erreur", "Veuillez remplir tous les champs convenablement! " + message);
                }

                if(session.getAttribute("erreur") != null){
                    if(profilFlag != null){
                        session.setAttribute("retour", "/profil");
                    }else{
                        session.setAttribute("retour", "/gestionUtilisateur");
                    }

                    response.sendRedirect("erreur");
                }else{
                    session.setAttribute("succes", "Vos données ont été changé avec succes ! ");
                    if(! (profilFlag == null)){

                        response.sendRedirect("profil");
                    }else{
                        response.sendRedirect("gestionUtilisateur");
                    }
                }
        }
    }





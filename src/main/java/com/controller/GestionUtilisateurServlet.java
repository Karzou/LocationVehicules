package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.entity.Ville;
import com.exception.ServiceException;
import com.service.*;

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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        List<Utilisateur> utilisateurList = null;
        UtilisateurService utilisateurService = new UtilisateurService(em);

        try {
            utilisateurList = utilisateurService.lister();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        // Plus besoin du coup

      //  Role role = null;
     //   RoleService roleService = null;
      //  Autorise autorise = new Autorise();
      //  AutoriseService autoriseService = new AutoriseService(em);

      //  List<Autorise> autoriseList = null;

      /*  try {
            autoriseList = autoriseService.listerParIdRole(2);
        } catch (ServiceException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }*/

        request.setAttribute("utilisateurList", utilisateurList);

       // request.setAttribute("autoriseList", autoriseList);

        this.getServletContext().getRequestDispatcher( "/WEB-INF/view/gestionUtilisateur.jsp" ).forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // recuperation des donnees des champs
        int id = Integer.parseInt(request.getParameter("idModif")) ;
        String nom = request.getParameter("nom");
        String password = request.getParameter("password");
        String prenom = request.getParameter("prenom");
      //  String mail = request.getParameter("mail");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String numero = request.getParameter("numero");
        String boite = request.getParameter("boite");
        String profilFlag = request.getParameter("profilFlag");
        int idVille = Integer.parseInt(request.getParameter("ville"));
        Date dateNaissance = Validation.dateFormat(request.getParameter("dateNaissance"));
        Date datePermis = Validation.dateFormat(request.getParameter("datePermis"));
      //  int role = Integer.parseInt(request.getParameter("role"));
        boolean erreurFlag = false;
        String message = ".";

        // instanciations
        UtilisateurService utilisateurService = new UtilisateurService(em);
        VilleService villeService = new VilleService(em);
    //    RoleService roleService = new RoleService(em);
        Utilisateur utilisateur = null;
        Ville ville = null;
        HttpSession session = request.getSession();

                if(!Validation.validationPrenom(nom)) {
                    message += "Veuillez entrer un nom avec au moins 2 caracteres ! ";
                    erreurFlag = true;
                }
                if(!Validation.validationPrenom(prenom)) {
                    message += "Veuillez entrer un prenom avec au moins 2 carcateres ! ";
                    erreurFlag = true;
                }
           /*     if(!Validation.validationTelephone(telephone)){
                    message += "Veuillez entrer que des chiffres ! ";
                    erreurFlag = true;
                }*/

                if(!Validation.validationAdresse(rue)){
                    message += "Veuillez entrer une adresse d'au moins 6 caracteres ! ";
                    erreurFlag = true;
                }
                if(!erreurFlag) {
                    nom = Validation.ucFirst(nom);
                    prenom = Validation.ucFirst(prenom);
                    try {
                        ville = villeService.trouver(idVille);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                    try {
                        utilisateur = utilisateurService.trouver(id);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
         /*           Role roleDb = null;
                    try {
                        roleDb = roleService.trouver(role);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
         */          try {
                        transaction.begin();

                        utilisateur.setNomUtilisateur(nom);
                        utilisateur.setPrenomUtilisateur(prenom);
                        utilisateur.setDateNaissance(dateNaissance);
                        utilisateur.setMotDePasse(password);
                        utilisateur.setDatePermis(datePermis);
                        utilisateur.getAdressesByIdAdresse().setBoite(boite);
                        utilisateur.getAdressesByIdAdresse().setNumero(numero);
                        utilisateur.getAdressesByIdAdresse().setRue(rue);
                        utilisateur.getAdressesByIdAdresse().setVillesByIdVille(ville);
        //                utilisateur.setRolesByIdRole(roleDb);

                        utilisateurService.update(utilisateur);

                        transaction.commit();
                    } catch (Exception e) {
                        session.setAttribute("erreur", "Une erreur est survenue lors de l'insertion en db !" );
                    } finally {
                        if (transaction.isActive()) {
                            transaction.rollback();
                        }
                        em.close();
                    }
                }else {
                    session.setAttribute("erreur", "Veuillez remplir tous les champs ! " + message);
                }

                if(session.getAttribute("erreur") != null){
                    session.setAttribute("retour", "/gestionUtilisateur");
                    response.sendRedirect("erreur");
                }else{
                    if(! (profilFlag == null)){
                        response.sendRedirect("profil");
                    }else{
                        response.sendRedirect("gestionUtilisateur");
                    }
                }
        }
    }





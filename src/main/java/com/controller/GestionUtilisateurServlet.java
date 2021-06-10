package com.controller;

import com.connection.EMF;
import com.entity.Autorise;
import com.entity.Role;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.RoleService;
import com.service.UtilisateurService;
import com.service.VilleService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

/**
 * @author Vanconingsloo Kevin
 */

@WebServlet(name = "GestionUtilisateurServlet")
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

        Role role = null;
        RoleService roleService = null;
        Autorise autorise = new Autorise();
        AutoriseService autoriseService = new AutoriseService(em);

        /*try {
            role = roleService.trouver(1);
        } catch (ServiceException e) {
            e.printStackTrace();
        }*/

        List<Autorise> autoriseList = null;

        try {

            autoriseList = autoriseService.lister(2);
        } catch (ServiceException e) {

            e.printStackTrace();
        } finally {

            em.close();
        }

        request.setAttribute("utilisateurList", utilisateurList);
        request.setAttribute("autoriseList", autoriseList);

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
        String mail = request.getParameter("mail");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String numero = request.getParameter("numero");
        String boite = request.getParameter("boite");
        String villeInput = request.getParameter("ville");
        String codePostal = request.getParameter("codepostal");
        String dateNaissance = request.getParameter("dateNaissance");
        String datePermis = request.getParameter("datePermis");
        int role = Integer.parseInt(request.getParameter("role"));

        // instanciations
        UtilisateurService utilisateurService = new UtilisateurService(em);
        VilleService villeService = new VilleService(em);
        RoleService roleService = new RoleService(em);
        Utilisateur utilisateur = null;

        try {

            utilisateur = utilisateurService.trouver(id);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        Role roleDb = null;

        try {

            roleDb = roleService.trouver(role);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        Date dateDeNaissance = Date.valueOf(dateNaissance);
        Date dateDePermis = Date.valueOf(datePermis);

        utilisateur.setNomUtilisateur(nom);
        utilisateur.setPrenomUtilisateur(prenom);
        utilisateur.setDateNaissance(dateDeNaissance);
        utilisateur.setDatePermis(dateDePermis);
        utilisateur.getAdressesByIdAdresse().setBoite(boite);
        utilisateur.getAdressesByIdAdresse().setNumero(numero);
        utilisateur.getAdressesByIdAdresse().setRue(rue);
        utilisateur.getAdressesByIdAdresse().getVillesByIdVille().setCodePostal(codePostal);
        utilisateur.getAdressesByIdAdresse().getVillesByIdVille().setNomVille(villeInput);
        utilisateur.setRolesByIdRole(roleDb);

        utilisateurService.update(utilisateur);

        response.sendRedirect("gestionUtilisateur");
    }
}

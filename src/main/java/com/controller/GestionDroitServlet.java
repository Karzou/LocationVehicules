package com.controller;

import com.connection.EMF;
import com.entity.Autorise;
import com.entity.Role;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.RoleService;
import com.service.UtilisateurService;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GestionDroitServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        UtilisateurService utilisateurService = new UtilisateurService(em);
        Utilisateur utilisateur = null;
        List<Autorise> autorise = null;
        AutoriseService autoriseService = new AutoriseService(em);

        int id = Integer.parseInt(request.getParameter("idSup"));
        int idRole = Integer.parseInt(request.getParameter("idRole"));

        try {

            utilisateur = utilisateurService.trouver(id);
            autorise = autoriseService.lister(idRole);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        request.setAttribute("utilisateur", utilisateur);
        request.setAttribute("autorise", autorise);


        this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionDroit.jsp").forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int idUtilisateur = Integer.parseInt(request.getParameter("idSup"));
        int idRole = Integer.parseInt(request.getParameter("idRole"));

        Utilisateur utilisateur = null;
        UtilisateurService  utilisateurService = new UtilisateurService(em);
        RoleService roleService = new RoleService(em);
        Role role = null;

        try {

            utilisateur = utilisateurService.trouver(idUtilisateur);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        try {

            role = roleService.trouver(idRole);
        } catch (ServiceException e) {

            e.printStackTrace();
        }

        utilisateur.setRolesByIdRole(role);

        try {

            transaction.begin();
            utilisateurService.update(utilisateur);
            transaction.commit();
        }finally {

            if (transaction.isActive()) {
                transaction.rollback();
            }
            em.close();
        }

        response.sendRedirect("gestionUtilisateur");
    }
}

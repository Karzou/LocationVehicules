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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Vanconingsloo Kevin
 */

@WebServlet("/gestionDroit")
public class GestionDroitServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();

        UtilisateurService utilisateurService = new UtilisateurService(em);
        Utilisateur utilisateur = null;
        List<Autorise> autorise = null;
        AutoriseService autoriseService = new AutoriseService(em);
        RoleService roleService = new RoleService(em);
        List<Role> roleList = null;

        try {
            roleList = roleService.lister();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        int id = Integer.parseInt(request.getParameter("idSup"));
        int idRole = Integer.parseInt(request.getParameter("idRole"));

        try {
            utilisateur = utilisateurService.trouver(id);
            autorise = autoriseService.listerParIdRole(idRole);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        em.close();

        request.setAttribute("utilisateur", utilisateur);
        request.setAttribute("autorise", autorise);
        request.setAttribute("roleList", roleList);

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
        try {
            utilisateur.setRolesByIdRole(role);
        } catch (Exception e){
            // A faire
        }
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

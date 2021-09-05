package com.controller;

import com.connection.EMF;
import com.entity.Autorise;
import com.entity.Role;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.RoleService;
import com.service.UtilisateurService;
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
import java.util.List;

/**
 * @author Vanconingsloo Kevin
 */

@WebServlet("/gestionDroit")
public class GestionDroitServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionDroitServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel du doGet de la servlet GestionDroit.");
        }

        EntityManager em = EMF.getEM();

        UtilisateurService utilisateurService = new UtilisateurService(em);
        Utilisateur utilisateur = null;
        List<Autorise> autorise = null;
        AutoriseService autoriseService = new AutoriseService(em);
        RoleService roleService = new RoleService(em);
        List<Role> roleList = null;

        try {
            if(logger.isInfoEnabled()){
                logger.info("Import de la liste des services.");
            }

            roleList = roleService.lister();
        } catch (ServiceException e) {
            logger.warn("Problème avec l'import de la liste de rôle. " + e);
        }

        int id = Integer.parseInt(request.getParameter("idSup"));
        int idRole = Integer.parseInt(request.getParameter("idRole"));

        try {
            utilisateur = utilisateurService.trouver(id);
            autorise = autoriseService.listerParIdRole(idRole);
        } catch (ServiceException e) {
            logger.warn("Problème avec l'import de la liste des autorisations par rôle. " + e);
        }
        em.close();

        request.setAttribute("utilisateur", utilisateur);
        request.setAttribute("autorise", autorise);
        request.setAttribute("roleList", roleList);

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionDroit.jsp").forward( request, response );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel du doPost de la servlet GestionDroit.");
        }
        HttpSession session = request.getSession();
        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        int idUtilisateur = Integer.parseInt(request.getParameter("idSup"));
        int idRole = Integer.parseInt(request.getParameter("idRole"));

        Utilisateur utilisateur = null;
        UtilisateurService  utilisateurService = new UtilisateurService(em);
        RoleService roleService = new RoleService(em);
        Role role = null;
        AutoriseService autoriseService = new AutoriseService(em);

        if( !autoriseService.hasPermission((int)session.getAttribute("idRole"), "all" )){
            logger.info("hasPermission non OK " + utilisateur.getEmail());
            session.setAttribute("erreur", "Vous n'avez pas les droits requis ! ");
            session.setAttribute("retour", "/gestionDroit");
            this.getServletContext().getRequestDispatcher( "/WEB-INF/view/erreur.jsp" ).forward( request, response );
        }else{
            try {
                utilisateur = utilisateurService.trouver(idUtilisateur);
            } catch (ServiceException e) {
                logger.warn("Problème lors de la recherche de l'idUtilisateur : " + idUtilisateur + ". " + e);
            }

            try {
                role = roleService.trouver(idRole);
            } catch (ServiceException e) {
                logger.warn("Problème lors de la recherche de rôle par idRole : " + idRole + ". " + e);
            }
            try {
                utilisateur.setRolesByIdRole(role);
            } catch (Exception e){
                logger.warn("Problème lors de la mise à jour du rôle de l'utilisateur : " + utilisateur.getEmail() + ". " + e);
            }
            try {
                if(logger.isInfoEnabled()){
                    logger.info("Début de la transaction de la mise à jour des droits de l'utilisateur : " + utilisateur.getEmail());
                }

                transaction.begin();

                utilisateurService.update(utilisateur);
                session.setAttribute("success", "Les droits de l utilisateur ont ete mis à jour.");

                transaction.commit();
            }finally {
                if (transaction.isActive()) {
                    logger.warn("Rollback de la mise à jour des droits de " + utilisateur.getEmail());
                    transaction.rollback();
                }
            }
            response.sendRedirect("gestionUtilisateur");
        }
        em.close();
    }
}

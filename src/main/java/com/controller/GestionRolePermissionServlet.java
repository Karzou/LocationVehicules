package com.controller;

import com.connection.EMF;
import com.entity.Autorise;
import com.entity.Permission;
import com.entity.Role;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.PermissionService;
import com.service.RoleService;
import com.service.Validation;
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

@WebServlet("/gestionRolePermission")
public class GestionRolePermissionServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(GestionRolePermissionServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel du doGet de la servlet GestionRolePermission.");
        }

        EntityManager em = EMF.getEM();

        if(logger.isInfoEnabled()){
            logger.info("Ouverture de l em gestion role permission do get");
        }

        HttpSession session = request.getSession();

        List<Role> roleList = null;
        RoleService roleService = new RoleService(em);
        List<Permission> permissionList = null;
        PermissionService permissionService = new PermissionService(em);
        List<Autorise> autoriseList = null;
        AutoriseService autoriseService = new AutoriseService(em);

        try{
            if(logger.isInfoEnabled()){
                logger.info("Import de la liste autorise.");
            }
            autoriseList = autoriseService.lister();
        } catch (ServiceException e) {
            logger.warn("Problème avec l'import de la liste autorisation. " + e);
            session.setAttribute("erreur", "Erreur lors de l'import de la liste des autorises. ");
        }

        try {
            if(logger.isInfoEnabled()){
                logger.info("Improt de la liste des permissions.");
            }
            permissionList = permissionService.lister();
        } catch (ServiceException e) {
            logger.warn("Erreur lors de l'import de la liste des permissions. " + e);
            session.setAttribute("erreur", "Erreur lors de l'import de la liste des permissions. ");
        }

        try {
            if(logger.isInfoEnabled()){
                logger.info("Import de la liste des roles.");
            }

            roleList = roleService.lister();
        } catch (ServiceException e) {
            logger.warn("Probleme lors de l'import de la liste des roles. " + e);
            session.setAttribute("erreur","Probleme lors de l'import' de la liste des roles.");
        }

        em.close();
        if(logger.isInfoEnabled()){
            logger.info("Fermeture de l'em gestion role permission do get");
        }

        request.setAttribute("autoriseList", autoriseList);
        request.setAttribute("roleList", roleList);
        request.setAttribute("permissionList", permissionList);

        if(session.getAttribute("erreur") != null ){
            if(logger.isInfoEnabled()){
                logger.info("Des erreurs ont ete comise. " + session.getAttribute("erreur"));
            }

            request.setAttribute("retour", "/gestionRolePermission");
            response.sendRedirect("erreur");

        }else {
            if(logger.isInfoEnabled()){
                logger.info("Tout ok.");
            }

            this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionRolePermission.jsp").forward( request, response );
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel du doPost de la servlet GestionRolePermission.");
        }

        EntityManager em = EMF.getEM();
        if(logger.isInfoEnabled()){
            logger.info("Ouverture de l em gestion role permission do post");
        }
        EntityTransaction transaction = em.getTransaction();
        HttpSession session = request.getSession();

        String supRoleFlag = request.getParameter("supRoleFlag");
        String ajoutPermissionFlag = request.getParameter("ajoutPermissionFlag");
        String ajoutRoleFlag = request.getParameter("ajoutRoleFlag");

        List<Role> roleList = null;
        RoleService roleService = new RoleService(em);
        List<Permission> permissionList = null;
        PermissionService permissionService = new PermissionService(em);
        AutoriseService autoriseService = new AutoriseService(em);

        session.setAttribute("erreur", null);
        session.setAttribute("retour", null);

        if(ajoutRoleFlag != null){
            String nomRole = request.getParameter("nomRole").trim();

            if( !Validation.checkValueLenght(nomRole, 1, 50) || Validation.checkValueIsEmpty(nomRole)){

                if(logger.isInfoEnabled()){
                    logger.info("Nom du role pas valide: " + nomRole);
                }
                session.setAttribute("erreur", "problème lors de la création du rôle. Le nom du rôle n'est pas valide.");
            }
            else{
                if ( roleService.trouverParNom(nomRole) != null){
                    session.setAttribute("erreur", "Ce role existe deja.");
                }else{
                    Role role = new Role(nomRole);
                    try {
                        if(logger.isInfoEnabled()){
                            logger.info("Début methode ajout de role. : " + nomRole);
                        }

                        transaction.begin();
                        roleService.creer(role);
                        transaction.commit();
                        session.setAttribute("success", "Le role a bien été créé.");
                    } catch (ServiceException e) {
                        logger.warn("Problème lors de la création du rôle : " + e);
                        session.setAttribute("erreur", "problème lors de la création du rôle. ");
                    }finally {
                        if (transaction.isActive()) {
                            logger.warn("Rollback de la création du rôle.");
                            transaction.rollback();
                        }
                    }
                }
            }
        }

        if(supRoleFlag != null ){
            Role role  = null;
            try {
                role = roleService.trouver(Integer.parseInt(request.getParameter( "idRole")));
                if(logger.isInfoEnabled()){
                    logger.info("Recherche du rôle a supprimer : " + role.getRoleDescription());
                }
            } catch (ServiceException e) {
                logger.warn("Problème lors de la suppression du rôle : " + role.getRoleDescription() + ". " + e);
                session.setAttribute("erreur", "Problème lors de la suppression du rôle.");
            }
            try {
                if(logger.isInfoEnabled()){
                    logger.info("Début de la transaction de suppression de rôle.");
                }

                if(!role.getRoleDescription().equals("admin")){
                    if(!role.getRoleDescription().equals("client")){
                        if(!role.getRoleDescription().equals("employe")){
                            transaction.begin();
                            autoriseService.supprimerParRole(role.getIdRole());
                            roleService.supprimer(role);
                            transaction.commit();
                            session.setAttribute("success", "Le role est bien supprimé.");
                        }else{
                            logger.warn("tentative d'effacer le rôle 'employe'");
                            session.setAttribute("adminSafe", "Vous ne pouvez pas effacer le rôle 'employe'");
                        }
                    }else{
                        logger.warn("tentative d'effacement du rôle 'client'");
                        session.setAttribute("adminSafe", "Vous ne pouvez pas effacer le rôle 'client'");
                    }

                }else{
                    logger.warn("tentative d'effacement du rôle 'admin'");
                    session.setAttribute("adminSafe", "Vous ne pouvez pas effacer le rôle 'admin'");
                }

            }catch (Exception e){
                logger.warn("Problème lors de la suppression du rôle : " + role.getRoleDescription() + ". " + e);
                session.setAttribute("erreur", "Impossible de supprimer ce role car il est relié à au moins un utilisateur.");
                session.setAttribute("retour", "/gestionRolePermission");
            }finally {
                if (transaction.isActive()) {
                    logger.warn("Rollback de la suppression du rôle : " + role.getRoleDescription());
                    transaction.rollback();
                }
            }
        }

        if(ajoutPermissionFlag != null){
            int idPermission = Integer.parseInt(request.getParameter( "idPermission"));
            int ajoutIdRole = Integer.parseInt( request.getParameter( "ajoutIdRole"));
            Permission permission = null;
            Role role = null;

            if(!autoriseService.checkAutorise(ajoutIdRole, idPermission)){
                try {
                    if(logger.isInfoEnabled()){
                        logger.info("Début de la recherche de permission. " + idPermission);
                    }
                    permission = permissionService.trouver(idPermission);
                } catch (ServiceException e) {
                    logger.warn("Problème lors de la recherche de permission. " + e);
                    session.setAttribute("erreur","Problème lors de la recherche d'une permission. ");
                }
                try {
                    if(logger.isInfoEnabled()){
                        logger.info("Début de la recherche de rôle. " + ajoutIdRole);
                    }
                    role = roleService.trouver(ajoutIdRole);
                } catch (ServiceException e) {
                    logger.warn("Problème lors de la recherche de rôle : " + ajoutIdRole + ". " + e);
                    session.setAttribute("erreur","Problème lors de la recherche d'un rôle. ");
                }
                if (!role.getRoleDescription().equals("admin") ){
                    if (!role.getRoleDescription().equals("client")){
                       if (!role.getRoleDescription().equals("employe")){
                           Autorise autorise = new Autorise(permission,role);
                           try {
                               if(logger.isInfoEnabled()){
                                   logger.info("Début de la transaction persist autorise.");
                               }
                               transaction.begin();
                               autoriseService.creer(autorise);
                               transaction.commit();
                               session.setAttribute("success", "Le role a bien été mis à jour.");
                           } catch (Exception e) {
                               logger.warn("Problème lors de l'insertion en db de l'autorisation. " + e);
                               session.setAttribute("erreur", "Erreur lors de la persistance de 'autorise'. ");
                           }finally {
                               if (transaction.isActive()) {
                                   logger.warn("Rollback de l' 'insertion de autorise.");
                                   transaction.rollback();
                               }
                           }
                       } else{
                           session.setAttribute("adminSafe", "Vous ne pouvez pas modifier les permissions sur le rôle 'employe'");
                       }
                    }else{
                        session.setAttribute("adminSafe", "Vous ne pouvez pas modifier les permissions sur le rôle 'client'");
                    }


                }else{
                    session.setAttribute("adminSafe", "Vous ne pouvez pas modifier les permissions sur le rôle 'admin'");
                }
            }else{
                if(logger.isInfoEnabled()){
                    logger.info("La permission existe déjà sur ce rôle.");
                }
                session.setAttribute("retour", "/gestionRolePermission");
                session.setAttribute("erreur", "Cette permission existe déjà sur ce role ! ");
            }
            em.close();
            if(logger.isInfoEnabled()){
                logger.info("Fermeture em Gestion role permission do post");
            }
        }
        //Pour recharger les dernieres modifications
        EntityManager em1 = EMF.getEM();
        if(logger.isInfoEnabled()){
            logger.info("Ouverture em Gestion role permission do post");
        }
        PermissionService permissionService1 = new PermissionService(em1);
        RoleService roleService1 = new RoleService(em1);
        List<Autorise> autoriseList = null;
        AutoriseService autoriseService1 = new AutoriseService(em1);

        try{
            if(logger.isInfoEnabled()){
                logger.info("Import de la liste 'autorise'.");
            }
            autoriseList = autoriseService1.lister();
        } catch (ServiceException e) {
            logger.warn("Problème lors de l' 'import de la liste 'autorisation. " + e);
            session.setAttribute("erreur", "erreur lors de l'import de la liste d'autorisations'. ");
        }

        try {
            if(logger.isInfoEnabled()){
                logger.info("Import de la liste de permission.");
            }
            permissionList = permissionService1.lister();
        } catch (ServiceException e) {
            logger.warn("Problème lors de l' 'import de la liste de permission. " + e);
            session.setAttribute("erreur", "erreur lors de l'import de la liste de permission. ");
        }

        try {
            if(logger.isInfoEnabled()){
                logger.info("Import de la liste de rôle.");
            }
            roleList = roleService1.lister();
        } catch (ServiceException e) {
            logger.warn("Probleme lors de l' 'import de la liste des rôles. " + e);
            session.setAttribute("erreur", "Erreur lors de l'import de la liste des rôles.");
        }

        em1.close();
        if(logger.isInfoEnabled()){
            logger.info("Fermeture de l em gestion role permission dopost");
        }

        request.setAttribute("autoriseList", autoriseList);
        request.setAttribute("roleList", roleList);
        request.setAttribute("permissionList", permissionList);

        if(session.getAttribute("erreur") != null ){
            if(logger.isInfoEnabled()){
                logger.info("Redirection vers erreur.");
            }
            session.setAttribute("retour", "/gestionRolePermission");
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionRolePermission.jsp").forward( request, response );
        }else {
            if(logger.isInfoEnabled()){
                logger.info("OK");
            }
           this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionRolePermission.jsp").forward( request, response );
            if(session.getAttribute("adminSafe") != null){
                session.removeAttribute("adminSafe");
            }
        }
    }
}
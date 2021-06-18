package com.controller;

import com.connection.EMF;
import com.entity.Autorise;
import com.entity.Permission;
import com.entity.Role;
import com.exception.ServiceException;
import com.service.AutoriseService;
import com.service.PermissionService;
import com.service.RoleService;
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

        logger.info("Appel du doGet de la servlet GestionRolePermission.");

        EntityManager em = EMF.getEM();

        HttpSession session = request.getSession();

        List<Role> roleList = null;
        RoleService roleService = new RoleService(em);
        List<Permission> permissionList = null;
        PermissionService permissionService = new PermissionService(em);
        List<Autorise> autoriseList = null;
        AutoriseService autoriseService = new AutoriseService(em);

        try{
            logger.info("Import de la liste autorise.");
            autoriseList = autoriseService.lister();
        } catch (ServiceException e) {
            logger.warn("Probleme avec l import de la liste autorisation. " + e);
            session.setAttribute("erreur", "Erreur lors de l'import de la liste des autorises. ");
        }
        try {
            logger.info("Improt de la liste des permissions.");
            permissionList = permissionService.lister();
        } catch (ServiceException e) {
            logger.warn("Erreur lors de l'import de la liste des permissions. " + e);
            session.setAttribute("erreur", "Erreur lors de l'import de la liste des permissions. ");
        }

        try {
            logger.info("Import de la liste des roles.");
            roleList = roleService.lister();
        } catch (ServiceException e) {
            logger.warn("Probleme lors de l'import' de la liste des roles. " + e);
            session.setAttribute("erreur","Probleme lors de l'import' de la liste des roles.");
        }

        em.close();

        request.setAttribute("autoriseList", autoriseList);
        request.setAttribute("roleList", roleList);
        request.setAttribute("permissionList", permissionList);

        if(session.getAttribute("erreur") != null ){
            logger.info("Des erreurs ont ete comise. " + session.getAttribute("erreur"));
            request.setAttribute("retour", "/gestionRolePermission");
            response.sendRedirect("erreur");

        }else {
            logger.info("Tout ok.");
            this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionRolePermission.jsp").forward( request, response );
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Appel du doPost de la servlet GestionRolePermission.");

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();
        HttpSession session = request.getSession();

        session.setAttribute("erreur", null);
        session.setAttribute("retour", null);
        List<Role> roleList = null;
        RoleService roleService = new RoleService(em);
        List<Permission> permissionList = null;
        PermissionService permissionService = new PermissionService(em);
        AutoriseService autoriseService = new AutoriseService(em);

        String supRoleFlag = request.getParameter("supRoleFlag");

        String ajoutPermissionFlag = request.getParameter("ajoutPermissionFlag");

        String ajoutRoleFlag = request.getParameter("ajoutRoleFlag");

        if(ajoutRoleFlag != null){

            String nomRole = request.getParameter("nomRole");

            Role role = new Role(nomRole);
            try {
                logger.info("Debut methode ajout de role. : " + nomRole);
                transaction.begin();
                roleService.creer(role);
                transaction.commit();
            } catch (ServiceException e) {
                session.setAttribute("erreur", "probleme lors de la creation du role. " + e.getMessage());
            }finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        }

        if(supRoleFlag != null ){
            Role role  = null;
            try {
                role = roleService.trouver(Integer.parseInt(request.getParameter( "idRole")));
            } catch (ServiceException e) {
                session.setAttribute("erreur", "Porbleme lors de la suppression du role. " + e.getMessage());
            }
            try {
                transaction.begin();
                roleService.supprimer(role);
                transaction.commit();
            }catch (Exception e){
                session.setAttribute("erreur", "Impossible de supprimer ce role car il est relié à au moins un utilisateur." + e.getMessage());
                session.setAttribute("retour", "/gestionRolePermission");
            }finally {
                if (transaction.isActive()) {
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
                    permission = permissionService.trouver(idPermission);
                } catch (ServiceException e) {
                    session.setAttribute("erreur","Probleme lors de la recherche d'une permission. " + e.getMessage());
                }
                try {
                    role = roleService.trouver(ajoutIdRole);
                } catch (ServiceException e) {
                    session.setAttribute("erreur","Probleme lors de la recherche d'un role. " + e.getMessage());
                }
                Autorise autorise = new Autorise(permission,role);
                try {
                    transaction.begin();
                    em.persist(autorise);
                    transaction.commit();
                } catch (Exception e) {
                    session.setAttribute("erreur", "Erreur lors de la persistance de 'autorise'. " + e.getMessage());
                }finally {
                    if (transaction.isActive()) {
                        transaction.rollback();
                    }

                }
            }else{
                session.setAttribute("retour", "/gestionRolePermission");
                session.setAttribute("erreur", "Cette permission existe déjà sur ce role ! ");
            }
            em.close();
        }

        EntityManager em1 = EMF.getEM();

        PermissionService permissionService1 = new PermissionService(em1);
        RoleService roleService1 = new RoleService(em1);
        List<Autorise> autoriseList = null;
        AutoriseService autoriseService1 = new AutoriseService(em1);

        try{
            autoriseList = autoriseService1.lister();
        } catch (ServiceException e) {
            session.setAttribute("erreur", "erreur lors de l'import de la liste d'autorisations'. " + e.getMessage());
        }

        try {
            permissionList = permissionService1.lister();
        } catch (ServiceException e) {
            session.setAttribute("erreur", "erreur lors de l'import de la liste de permission. " + e.getMessage());
        }

        try {
            roleList = roleService1.lister();
        } catch (ServiceException e) {
            session.setAttribute("erreur", "Erreur lors de l'import de la liste des roles. " +e.getMessage());
        }

        em1.close();

        request.setAttribute("autoriseList", autoriseList);
        request.setAttribute("roleList", roleList);
        request.setAttribute("permissionList", permissionList);

        if(session.getAttribute("erreur") != null ){
            response.sendRedirect("erreur");

        }else {
           this.getServletContext().getRequestDispatcher("/WEB-INF/view/gestionRolePermission.jsp").forward( request, response );
            //this.getServletContext().getRequestDispatcher("/login").forward( request, response );
        }
    }
}
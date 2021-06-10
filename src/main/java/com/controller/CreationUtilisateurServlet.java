package com.controller;

import com.connection.EMF;
import com.entity.*;
import com.exception.ServiceException;
import com.service.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

/**
 * @author Vanconingsloo Kevin
 */

public class CreationUtilisateurServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if(session != null) {

            response.sendRedirect("login");
        } else {

            this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // recuperation des donnees des champs
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
        String confirmPassword = request.getParameter("confirmPassword");

        nom = nom.substring(0,1).toUpperCase() + nom.substring(1).toLowerCase();
        prenom = prenom.substring(0,1).toUpperCase() + prenom.substring(1).toLowerCase();
        villeInput = villeInput.substring(0,1).toUpperCase() + villeInput.substring(1).toLowerCase();

        // instanciations
        UtilisateurService utilisateurService = new UtilisateurService(em);
        VilleService villeService = new VilleService(em);
        RoleService roleService = new RoleService(em);
        Role role = null;

        if ( nom.trim().isEmpty() || prenom.trim().isEmpty() || telephone.trim().isEmpty() || password.trim().isEmpty()
                || mail.trim().isEmpty() || rue.trim().isEmpty() || numero.trim().isEmpty()
                || villeInput.trim().isEmpty()) {

            request.setAttribute("errMessage", "Veuillez remplir tous les champs");

            this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward( request, response );
        } else {

            if (utilisateurService.mailExist(mail)) {

                request.setAttribute("errMessage", "Ce mail existe deja !!!");
            } else {

                // conversion date
                Date dateNaissanceSql = Date.valueOf(dateNaissance);
                Date datePermisSql = Date.valueOf(datePermis);
                Ville ville = null;

                try {

                    transaction.begin();
                    // traitements
                    try {

                        role = roleService.trouver(2);
                    } catch (ServiceException e) {

                        e.printStackTrace();
                    }

                    try {

                        ville = villeService.trouverParVille(villeInput);
                    } catch (Exception e) {

                        e.printStackTrace();
                    } finally {

                        if (ville == null) {

                            ville = new Ville();
                            PaysService paysService = new PaysService(em);
                            ville.setNomVille(villeInput);
                            ville.setCodePostal(codePostal);

                            try {

                                ville.setPaysByIdPays(paysService.trouver(1));
                            } catch (ServiceException e) {

                                e.printStackTrace();
                            }

                            try {

                                villeService.creer(ville);
                            } catch (ServiceException e) {

                                e.printStackTrace();
                            }

                            ville = villeService.trouverParVille(villeInput);
                        }
                    }

                    Adresse adresse = new Adresse(rue, numero, boite, ville);
                    Utilisateur utilisateur = new Utilisateur(
                                nom,
                                prenom,
                                mail,
                                password,
                                dateNaissanceSql,
                                datePermisSql,
                                adresse,
                                role);

                    //insertion db
                    if (confirmPassword.equals(password)) {

                        try {

                            utilisateurService.creer(utilisateur);
                            Utilisateur utilisateur1 = utilisateurService.trouverParNom(utilisateur.getNomUtilisateur());
                            TelephoneService telephoneService = new TelephoneService(em);
                            Telephone telephoneDb = new Telephone(telephone, utilisateur1);
                            telephoneService.creer(telephoneDb);
                            request.setAttribute("succes", "L'utilisateur a été créé avec succes.");
                        } catch (ServiceException e) {

                            e.printStackTrace();
                        }
                    } else {

                        request.setAttribute("errMessagePass", "Vos mots de passe ne sont pas identiques");
                    }

                    transaction.commit();
                }catch ( Exception e ) {

                    throw new ServletException( e );
                } finally {

                    if (transaction.isActive()) {

                        transaction.rollback();
                    }

                    em.close();
                }
            }
        }

        // redirection
        this.getServletContext().getRequestDispatcher("/WEB-INF/view/login.jsp").forward( request, response );
        //response.sendRedirect("login");
    }
}
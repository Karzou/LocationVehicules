package com.controller;

import com.connection.EMF;
import com.entity.*;
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

/**
 * @author Vanconingsloo Kevin
 */
@WebServlet("/creationUtilisateur")
public class CreationUtilisateurServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(CreationUtilisateurServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()){
            logger.info("Appel du doGet de la servlet CreationUtilisateur");
        }

        HttpSession session = request.getSession(false);

        if(session != null) {
            if (logger.isInfoEnabled()){
                logger.info("Pas de session ==> redirection login.");
            }
            response.sendRedirect("login");
        } else {

            this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel du doPost la servlet CreationUtilisateur");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // recuperation des donnees des champs
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String password = request.getParameter("password");
        String mail = request.getParameter("mail");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String numero = request.getParameter("numero");
        String boite = request.getParameter("boite");
        String idVilleInput = request.getParameter("ville");
        String dateNaissanceInput = request.getParameter("dateNaissance");
        String datePermisInput = request.getParameter("datePermis");
        String confirmPassword = request.getParameter("confirmPassword");

        request.setAttribute("nom", nom);
        request.setAttribute("prenom", prenom);
        request.setAttribute("password", password);
        request.setAttribute("confirmPassword", confirmPassword);
        request.setAttribute("mail", mail);
        request.setAttribute("telephone", telephone);
        request.setAttribute("rue", rue);
        request.setAttribute("numero", numero);
        request.setAttribute("boite", boite);


        // instanciations
        UtilisateurService utilisateurService = new UtilisateurService(em);
        VilleService villeService = new VilleService(em);
        RoleService roleService = new RoleService(em);
        Role role = null;
        Ville ville = null;
        boolean erreurFlag = false;
        HttpSession session = request.getSession();

        session.setAttribute("creation", " ok ");

        //Traitement
        if ( nom.trim().isEmpty() || prenom.trim().isEmpty() || telephone.trim().isEmpty() || password.trim().isEmpty()
                || mail.trim().isEmpty() || rue.trim().isEmpty() || numero.trim().isEmpty() || dateNaissanceInput.trim().isEmpty()
                || datePermisInput.trim().isEmpty()
                ) {
            if(logger.isInfoEnabled()){
                logger.info("Tous les champs du formulaire de création d'utilisateur ne sont pas remplis.");
            }

            request.setAttribute("errMessage", "Veuillez remplir tous les champs");
            request.setAttribute("erreurFlag", true);

            this.getServletContext().getRequestDispatcher("/login").forward( request, response );
        } else {
            if(logger.isInfoEnabled()){
                logger.info("Les champs du formulaire de création d'utilisateur sont bien remplis");
            }

            int idVille = Integer.parseInt(idVilleInput);
            nom = Validation.ucFirst(nom.trim());
            prenom = Validation.ucFirst(prenom.trim());
            Date dateNaissance = Validation.dateFormat(dateNaissanceInput);
            Date datePermis = Validation.dateFormat(dateNaissanceInput);

            if (utilisateurService.mailExist(mail)) {
                if(logger.isInfoEnabled()){
                    logger.info("Le mail existe deja : " + mail);
                }

                request.setAttribute("errMessage", "Ce mail existe deja !!!");
            } else {
                if(!Validation.validationPassword(password.trim())){

                    if(logger.isInfoEnabled()){
                        logger.info("Problème avec la validation du password : " + password);
                    }

                    request.setAttribute("erreurPassword", "Veuillez entrer un mot de passe d'au moins 4 caractères !");
                    erreurFlag = true;
                }
                if(!Validation.validationEmail(mail.trim())){

                    if(logger.isInfoEnabled()){
                        logger.info("Problème avec la validation du mail : " + mail);
                    }

                    request.setAttribute("erreurMail", "Veuillez entrer un mail valide !");
                    erreurFlag = true;
                }
                if(!Validation.validationPrenom(nom)) {

                    if(logger.isInfoEnabled()){
                        logger.info("Problème avec la validation du nom : " + nom);
                    }

                    request.setAttribute("erreurNom", "Veuillez entrer un nom avec au moins 2 caractères !");
                    erreurFlag = true;
                }
                if(!Validation.validationPrenom(prenom)) {
                    if(logger.isInfoEnabled()){
                        logger.info("Problème avec la validation du prénom : " + prenom);
                    }

                    request.setAttribute("erreurPrenom", "Veuillez entrer un prénom avec au moins 2 carcatères");
                    erreurFlag = true;
                }
                if(!Validation.validationTelephone(telephone.trim())){

                    if(logger.isInfoEnabled()){
                        logger.info("Problème avec la validation du téléphone : " + telephone);
                    }

                    request.setAttribute("erreurTelephone", "Veuillez entrer que des chiffres ! ");
                    erreurFlag = true;
                }

                if(!Validation.validationAdresse(rue.trim())){

                    if(logger.isInfoEnabled()){
                        logger.info("Problème avec la validation de l'adresse : " + rue);
                    }

                    request.setAttribute("erreurAdresse", "Veuillez entrer une adresse d'au moins 6 caractères ! ");
                    erreurFlag = true;
                }
                if( ! erreurFlag ) {
                    try {
                        if(logger.isInfoEnabled()){
                            logger.info("Début de la transaction CreationUtilisateur");
                        }

                        transaction.begin();
                        try {
                            ville = villeService.trouver(idVille);
                            role = roleService.trouverParNom("client");
                        } catch (ServiceException e) {
                            logger.warn("Problème de recherche de ville et de role client :" + idVille + " " + e);
                        }
                        Adresse adresse = new Adresse(rue.trim(), numero.trim(), boite.trim(), ville);
                        Utilisateur utilisateur = new Utilisateur(
                                nom,
                                prenom,
                                mail.trim(),
                                password.trim(),
                                dateNaissance,
                                datePermis,
                                adresse,
                                role);
                        //insertion db
                        if (confirmPassword.equals(password)) {
                            if(logger.isInfoEnabled()){
                                logger.info("Les mots de passes sont identiques : " + password + "/" + confirmPassword);
                            }

                            try {
                                if(logger.isInfoEnabled()){
                                    logger.info("Début de la création de l'utilisateur " + utilisateur.getEmail());
                                }

                                utilisateurService.creer(utilisateur);
                                Utilisateur utilisateur1 = utilisateurService.trouverParNom(utilisateur.getNomUtilisateur());
                                TelephoneService telephoneService = new TelephoneService(em);
                                Telephone telephoneDb = new Telephone(telephone, utilisateur1);
                                telephoneService.creer(telephoneDb);
                                request.setAttribute("succes", "L'utilisateur a été créé avec succès.");
                            } catch (ServiceException e) {
                                logger.warn("Problème de création de l'utilisateur " + utilisateur.getEmail() + " " + e);
                            }
                        } else {
                            request.setAttribute("erreurFlag", true);
                            request.setAttribute("errMessagePass", "Vos mots de passe ne sont pas identiques");
                        }
                        transaction.commit();
                    } catch ( Exception e ) {
                        logger.warn("Problème lors de la transaction de création utilisateur. " + e);
                    } finally {
                        if (transaction.isActive()) {
                            logger.warn("Rollback de la création d'utilisateur.");
                            transaction.rollback();
                        }
                        em.close();
                    }
                }else {
                    logger.info("Erreur lors de la création utilisateur.");
                    request.setAttribute("erreurFlag", true);
                }
            }
        }
        // redirection
        this.getServletContext().getRequestDispatcher("/login").forward( request, response );
    }
}
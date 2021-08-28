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

        if (logger.isInfoEnabled()) {

            logger.info("Appel du doGet de la servlet CreationUtilisateur");
        }

        HttpSession session = request.getSession(false);

        if (session != null) {

            if (logger.isInfoEnabled()) {

                logger.info("Pas de session ==> redirection login.");
            }

            response.sendRedirect("login");
        } else {

            this.getServletContext().getRequestDispatcher("/WEB-INF/view/accueil.jsp").forward( request, response );
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (logger.isInfoEnabled()) {

            logger.info("Appel du doPost la servlet CreationUtilisateur");
        }

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        // Récupération des données via les champs
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String mail = request.getParameter("mail");
        String telephone = request.getParameter("telephone");
        String rue = request.getParameter("rue");
        String numero = request.getParameter("numero");
        String boite = request.getParameter("boite");
        String idVilleInput = request.getParameter("ville");
        String dateNaissanceInput = request.getParameter("dateNaissance");
        String datePermisInput = request.getParameter("datePermis");

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

        HttpSession session = request.getSession();

        boolean errFlag = false;

        if (Validation.checkValueIsEmpty(nom)) {

            session.setAttribute("errRegister1", "Veuillez insérer votre nom");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (!Validation.checkValueLenght(nom, 2, 50)) {

            session.setAttribute("errRegister1", "Le nom de l'entrepôt doit être composé d'au minimum 2 caractères et au maximum 50 caractères");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(prenom)) {

            session.setAttribute("errRegister2", "Veuillez insérer votre prénom");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (!Validation.checkValueLenght(prenom, 2, 50)) {

            session.setAttribute("errRegister2", "Le nombre de place de l'entrepôt doit contenir au maximum 10 chiffres");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(password)) {

            session.setAttribute("errRegister4", "Veuillez insérer un mot de passe");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (!Validation.checkValueLenght(password, 4, 255)) {

            session.setAttribute("errRegister4", "Le nombre de place de l'entrepôt doit contenir au maximum 10 chiffres");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(confirmPassword)) {

            session.setAttribute("errRegister5", "Veuillez confirmer votre mot de passe");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (!Validation.checkValueLenght(confirmPassword, 4, 255)) {

            session.setAttribute("errRegister5", "Le nombre de place de l'entrepôt doit contenir au maximum 10 chiffres");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(mail)) {

            session.setAttribute("errRegister3", "Veuillez insérer votre email");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (!Validation.checkEmailFormat(mail)) {

            if(logger.isInfoEnabled()) {

                logger.info("Problème avec la validation du mail : " + mail);
            }

            session.setAttribute("errRegister3", "Veuillez insérer un mail valide");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (utilisateurService.mailExist(mail)) {

            session.setAttribute("errRegister3", "Ce mail existe déjà !!!");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(telephone)) {

            session.setAttribute("errRegister6", "Veuillez insérer un numéro de téléphone");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (!Validation.checkTelephoneFormat(telephone)) {

            session.setAttribute("errRegister6", "Veuillez insérer un num. de téléphone valide");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (!Validation.checkValueLenght(telephone, 7, 100)) {

            session.setAttribute("errRegister6", "Le nom de la rue de l'entrepôt doit être composé d'au minimum 6 caractères et au maximum 100 caractères");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(rue)) {

            session.setAttribute("errRegister7", "Veuillez insérer un nom de rue");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (!Validation.checkValueLenght(rue, 6, 100)) {

            session.setAttribute("errRegister7", "Le nom de la rue de l'entrepôt doit être composé d'au minimum 6 caractères et au maximum 100 caractères");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmpty(numero)) {

            session.setAttribute("errRegister8", "Veuillez insérer un numéro de rue");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        } else if (!Validation.checkValueLenghtMax(numero, 10)) {

            session.setAttribute("errRegister8", "Le numéro de la rue de l'entrepôt doit être composé d'au maximum 10 caractères");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmptyorNull(idVilleInput)) {

            session.setAttribute("errRegister9", "Veuillez selectionner une ville");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmptyorNull(dateNaissanceInput)) {

            session.setAttribute("errRegister10", "Veuillez insérer votre date de naissance");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (Validation.checkValueIsEmptyorNull(datePermisInput)) {

            session.setAttribute("errRegister11", "Veuillez insérer la date de votre permis de conduire");
            session.setAttribute("erreurFlag", true);

            errFlag = true;
        }

        if (errFlag) {

            logger.info("Erreur lors de la création de l'utilisateur.");

            session.setAttribute("erreurFlag", true);

            response.sendRedirect("login");
        } else {

            if(logger.isInfoEnabled()) {

                logger.info("Tous les champs du formulaire de création utilisateur sont remplis");
            }

            int idVille = Integer.parseInt(idVilleInput);
            nom = Validation.ucFirst(nom.trim());
            prenom = Validation.ucFirst(prenom.trim());
            Date dateNaissance = Validation.dateFormat(dateNaissanceInput);
            Date datePermis = Validation.dateFormat(datePermisInput);

            try {

                if (logger.isInfoEnabled()) {

                    logger.info("Début de la transaction CreationUtilisateur");
                }

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

                    if (logger.isInfoEnabled()) {

                        logger.info("Les mots de passes sont identiques : " + password + "/" + confirmPassword);
                    }

                    try {

                        if (logger.isInfoEnabled()) {

                            logger.info("Début de la création de l'utilisateur " + utilisateur.getEmail());
                        }

                        transaction.begin();

                        utilisateurService.creer(utilisateur);

                        Utilisateur utilisateur1 = utilisateurService.trouverParEmail(utilisateur.getEmail());
                        TelephoneService telephoneService = new TelephoneService(em);
                        Telephone telephoneDb = new Telephone(telephone, utilisateur1);

                        telephoneService.creer(telephoneDb);

                        transaction.commit();
                    } catch (ServiceException e) {

                        logger.warn("Problème de création de l'utilisateur " + utilisateur.getEmail() + " " + e);
                    }
                } else {

                    session.setAttribute("errRegister4", "les mots de passe ne sont pas identiques");
                    session.setAttribute("erreurFlag", true);
                }
            } catch ( Exception e ) {

                logger.warn("Problème lors de la transaction de création utilisateur. " + e);
            } finally {

                if (transaction.isActive()) {

                    logger.warn("Rollback de la création d'utilisateur.");

                    transaction.rollback();
                }

                em.close();
            }

            session.setAttribute("erreurFlag", false);
            session.setAttribute("success", "L'utilisateur a été créé avec succès");

            response.sendRedirect("login");
        }
    }
}
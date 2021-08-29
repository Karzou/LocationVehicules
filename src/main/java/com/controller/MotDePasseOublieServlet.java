package com.controller;

import com.connection.EMF;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import com.mail.Mail;
import com.mail.MailSender;
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

/**
 * @author Vanconingsloo Kevin
 */

@WebServlet(name = "/motDePasseOublie")
public class MotDePasseOublieServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(MotDePasseOublieServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel de la methode dopost de la servlet MotDePasseOublie.");
        }

        String message = "";
        String mail = request.getParameter("mail").trim();
        EntityManager em = EMF.getEM();
        if(logger.isInfoEnabled()){
            logger.info("Ouverture em MotDePasseOublie dopost");
        }
        EntityTransaction transaction = em.getTransaction();
        Utilisateur utilisateur = new Utilisateur();
        UtilisateurService utilisateurService = new UtilisateurService(em);
        Mail email = new Mail();
        HttpSession session = request.getSession();
        int randomInt = (int)(Math.random() * (9999 - 1000) + 1000);
        String randomPassword = String.valueOf(randomInt);

        if(!utilisateurService.mailExist(mail)){
            message = "Votre mail n'existe pas. ";
            session.setAttribute("errMessage", message);
        }else {
            try {
                utilisateur = utilisateurService.trouverParEmail(mail);
            } catch (ServiceException e) {
                e.getMessage();
                session.setAttribute("errMessage", message);
            }
            try {
                transaction.begin();

                utilisateur.setMotDePasse(randomPassword);
                utilisateurService.update(utilisateur);

                transaction.commit();

            } catch ( Exception e ) {
            logger.warn("Problème lors de la transaction de l'update mot de passe utilisateur. " + e);
        } finally {
            if (transaction.isActive()) {
                logger.warn("Rollback de l'update mot de passe utilisateur.");
                transaction.rollback();
            }
            em.close();
            if(logger.isInfoEnabled()){
                logger.info("Fermeture em gestion utilisateur do get");
            }

            //Préparation et envoi de mail.
            email.setMsgBody("Votre mot de passe a été réinitialisé à " + randomPassword + ". Veuillez changer votre mot de passe lors de votre prochaine connection. ");
            email.setFrom("locacarprojetsgbd@gmail.com");
            email.setSubject("Réinitialisation mot de passe.");
            email.setNick("Locacar");
            email.setReplyTo(utilisateur.getEmail());
            email.setEncodeUTF8(true);
            email.getListTo().add(utilisateur.getEmail());

            MailSender.sendMail(email);

            session.setAttribute("success", "Un mail a été envoyé avec votre nouveau mot de passe à l'email suivant : " + utilisateur.getEmail() + " .");
            request.setAttribute("forgotFlag", "OK");
            this.getServletContext().getRequestDispatcher("/login").forward( request, response );
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(logger.isInfoEnabled()){
            logger.info("Appel de la méthode doget de la servlet MotDePasseOublie");
        }

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/motDePasseOublie.jsp").forward( request, response );
    }
}

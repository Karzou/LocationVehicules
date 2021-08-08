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

        logger.info("Appel de la methode doPost de MotDePasseOublieServlet.");

        String message = "";
        String mail = request.getParameter("mail");

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();
        Utilisateur utilisateur = new Utilisateur();
        UtilisateurService utilisateurService = new UtilisateurService(em);
        Mail email = new Mail();
        HttpSession session = request.getSession();

        if(!utilisateurService.mailExist(mail)){
            message = "Votre mail n'existe pas. ";
        }else {
            try {
                utilisateur = utilisateurService.trouverParEmail(mail);
            } catch (ServiceException e) {
                e.getMessage();
                session.setAttribute("errMessage", message);
            }
            try {


            transaction.begin();

            // on peut fairer un random aussi.
            utilisateur.setMotDePasse("0000");

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

            email.setMsgBody("Votre mot de passe a été réinitialisé à 0000. Veuillez changer votre mot de passe lors de votre prochaine connection. ");
            email.setFrom("locacarprojetsgbd@gmail.com");
            email.setSubject("Réinitialisation mot de passe.");
            email.setNick("Locacar");
            email.setReplyTo(utilisateur.getEmail());
            email.setEncodeUTF8(true);
            email.getListTo().add(utilisateur.getEmail());

            MailSender.sendMail(email);

            response.sendRedirect("login");

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        logger.info("Appel de la methode doGet de MotDePasseOublieServlet.");

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/motDePasseOublie.jsp").forward( request, response );
    }
}

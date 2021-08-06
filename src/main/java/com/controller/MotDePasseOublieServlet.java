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
import java.io.IOException;

@WebServlet(name = "/motDePasseOublie")
public class MotDePasseOublieServlet extends HttpServlet {

    final static Logger logger = LogManager.getLogger(MotDePasseOublieServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String message = "";
        String mail = request.getParameter("mail");

        EntityManager em = EMF.getEM();
        EntityTransaction transaction = em.getTransaction();

        Utilisateur utilisateur = new Utilisateur();
        UtilisateurService utilisateurService = new UtilisateurService(em);

        if(!utilisateurService.mailExist(mail)){
            message = "Votre mail n'existe pas. ";
        }else {
            try {
                utilisateur = utilisateurService.trouverParEmail(mail);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
            try {


            transaction.begin();

            utilisateur.setMotDePasse("0000");

            utilisateurService.update(utilisateur);

            transaction.commit();

            } catch ( Exception e ) {
            logger.warn("Probleme lors de la transaction de creation utilisateur. " + e);
        } finally {
            if (transaction.isActive()) {
                logger.warn("Rollback de la creation d utilisateur.");
                transaction.rollback();
            }
            em.close();

            /* A vérifier
                Mail email = new Mail();
                email.setMsgBody("Votre mot de passe a été réinitialisé à 0000. Veuillez changer votre mot de passe lors de votre prochaine connection. ");
                email.setFrom("locationVehicule@test.com");
                email.setSubject("Réinitialisation mot de passe.");
                email.setNick(utilisateur.getEmail());
                email.setReplyTo(utilisateur.getEmail());

                MailSender.sendMail(email);
            */

            response.sendRedirect("login");

            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.getServletContext().getRequestDispatcher("/WEB-INF/view/motDePasseOublie.jsp").forward( request, response );
    }
}

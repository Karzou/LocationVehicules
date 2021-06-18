package com.service;

import com.entity.Utilisateur;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Vanconingsloo Kevin
 */

public class UtilisateurService {

    private static final Logger LOGGER = LogManager.getLogger(UtilisateurService.class);

    EntityManager em;

    public UtilisateurService(EntityManager em) {
        this.em = em;
    }

    public void update(Utilisateur utilisateur) {
        em.merge(utilisateur);
    }

    public Utilisateur trouver(int id) throws ServiceException {
        try {
            LOGGER.info("recherche utilisateur " + id);
            return em.find(Utilisateur.class, id);
        } catch (Exception e) {
            LOGGER.error("erreur : " + e);
            throw new ServiceException(e);
        }
    }
    public boolean mailExist (String email){
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.mailExist", Utilisateur.class);
        query.setParameter("email", email);

        try {
            Utilisateur u = query.getSingleResult();
            LOGGER.info("recherche mail " + email);
            return true;
        } catch(javax.persistence.NoResultException e) {
            LOGGER.info("mail " + email + " pas trouvé.");
            return false;
        }
    }

    public boolean checkLogin (String email, String password){
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.checkLogin", Utilisateur.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        try {
            LOGGER.info("recherche login + password " + email + " / " + password );
            Utilisateur u = query.getSingleResult();
            return true;
        } catch(javax.persistence.NoResultException e) {
            LOGGER.info("login pas trouvé.");
            return false;
        }
    }

    public Utilisateur trouverParEmail(String email) throws ServiceException {
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.trouverParEmail", Utilisateur.class);
        query.setParameter("email", email);
        LOGGER.info("recherche en db le mail : " + email );
        return  query.getSingleResult();

    }

    public Utilisateur trouverParNom(String nom) throws ServiceException {
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.trouverParNom", Utilisateur.class);
        query.setParameter("nom", nom);

        LOGGER.info("recherche en db le nom : " + nom );

        return query.getSingleResult();
    }

    public void creer(Utilisateur utilisateur) throws ServiceException {
        LOGGER.info("Creation de l utilisateur " + utilisateur.getEmail());

        em.persist(utilisateur);
    }

    public List<Utilisateur> lister() throws ServiceException {
        try {
            LOGGER.info("Import de la liste utilisateur.");

            TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.lister", Utilisateur.class);
            return query.getResultList();
        } catch (Exception e) {
            LOGGER.warn("Probleme lors de l import liste utilisateur. " + e.getMessage());

            throw new ServiceException(e);
        }
    }

    public void suppressionLogique (Utilisateur utilisateur){
        LOGGER.info("Suppression logique de l utilisateur : " + utilisateur.getEmail());

        utilisateur.setActifUtilisateur(false);
        em.persist(utilisateur);
    }
}

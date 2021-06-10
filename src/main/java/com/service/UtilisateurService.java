package com.service;

import com.entity.Utilisateur;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Vanconingsloo Kevin
 */

public class UtilisateurService {

    EntityManager em;

    public UtilisateurService(EntityManager em) {
        this.em = em;
    }

    public void update(Utilisateur utilisateur) {
        em.merge(utilisateur);
    }

    public Utilisateur trouver(int id) throws ServiceException {
        try {
            return em.find(Utilisateur.class, id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public boolean mailExist (String email){
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.mailExist", Utilisateur.class);
        query.setParameter("email", email);

        try {
            Utilisateur u = query.getSingleResult();
            return true;
        } catch(javax.persistence.NoResultException e) {
            return false;
        }
    }

    public boolean checkLogin (String email, String password){
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.checkLogin", Utilisateur.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        try {
            Utilisateur u = query.getSingleResult();
            return true;
        } catch(javax.persistence.NoResultException e) {
            return false;
        }
    }

    public Utilisateur trouverParEmail(String email) throws ServiceException {
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.trouverParEmail", Utilisateur.class);
        query.setParameter("email", email);
        return  query.getSingleResult();
    }

    public Utilisateur trouverParNom(String nom) throws ServiceException {
        TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.trouverParNom", Utilisateur.class);
        query.setParameter("nom", nom);
        return query.getSingleResult();
    }

    public void creer(Utilisateur utilisateur) throws ServiceException {
        em.persist(utilisateur);
    }

    public List<Utilisateur> lister() throws ServiceException {
        try {
            TypedQuery<Utilisateur> query = em.createNamedQuery("Utilisateur.lister", Utilisateur.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void suppressionLogique (Utilisateur utilisateur){
        utilisateur.setActifUtilisateur(false);
        em.persist(utilisateur);
    }
}

package com.service;

import com.entity.Autorise;
import com.exception.ServiceException;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
/**
 * @author Vanconingsloo Kevin
 */
public class AutoriseService {

    EntityManager em;

    public AutoriseService(EntityManager em) {
        this.em = em;
    }

    public Autorise trouver(int id) throws ServiceException {
        try {

            return em.find(Autorise.class, id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void creer(Autorise autorise) throws ServiceException {
        try {
            em.persist(autorise);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public Boolean hasPermission (int idRole, String permission){
        TypedQuery<Autorise> query = em.createNamedQuery("Autorise.hasPermission", Autorise.class);
        query.setParameter("idRole", idRole);
        query.setParameter("permission", permission);

        try {
            query.getSingleResult();
            return true;
        } catch(javax.persistence.NoResultException e) {
            return false;
        }
    }

    public List<Autorise> lister() throws ServiceException {
        try {
            TypedQuery<Autorise> query = em.createNamedQuery("Autorise.lister", Autorise.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Autorise> listerParIdRole(int idRole) throws ServiceException {
        try {
            TypedQuery<Autorise> query = em.createNamedQuery("Autorise.listeParRole", Autorise.class);
             query.setParameter("idRole", idRole);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public boolean checkAutorise (int idRole, int idPermission){
        TypedQuery<Autorise> query = em.createNamedQuery("Autorise.trouverParRoleEtPermission", Autorise.class);
        query.setParameter("idRole", idRole);
        query.setParameter("idPermission", idPermission);

        try {
            query.getSingleResult();
            return true;
        } catch(javax.persistence.NoResultException e) {
            return false;
        }
    }

    public void supprimerParRole (int idRole) {
        Query query = em.createNamedQuery("Autorise.effacerParRole", Autorise.class);
        query.setParameter("idRole", idRole);

        query.executeUpdate();
    }

    public void supprimer (Autorise autorise){
        em.remove(autorise);
    }
}

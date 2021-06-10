package com.service;

import com.entity.Entrepot;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @authors Wets Jeoffroy // Vanconingsloo Kevin
 */

public class EntrepotService {

    EntityManager em;

    public EntrepotService(EntityManager em) {
        this.em = em;
    }

    public void update(Entrepot entrepot) {
        em.merge(entrepot);
    }

    public Entrepot trouver(int id) throws ServiceException {
        try {
            return em.find(Entrepot.class, id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void creer(Entrepot entrepot) throws ServiceException {
        try {
            em.persist(entrepot);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Entrepot> lister() throws ServiceException {
        try {
            TypedQuery<Entrepot> query = em.createNamedQuery("Entrepot.lister", Entrepot.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void suppressionLogique (Entrepot entrepot) throws ServiceException {
        try {
            entrepot.setActifEntrepot(false);
            em.persist(entrepot);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}
package com.service;

import com.entity.Marque;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class MarqueService {

    EntityManager em;

    public MarqueService(EntityManager em) {

        this.em = em;
    }

    public Marque trouver(int id) throws ServiceException {

        try {

            return em.find(Marque.class, id);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public Marque trouverParNom(String nomMarque) {

        TypedQuery<Marque> query = em.createNamedQuery("Marque.trouverParNom", Marque.class);
        query.setParameter("nomMarque", nomMarque);

        if(query.getSingleResult() != null) {

            return query.getSingleResult();
        } else {

            return null;
        }
    }

    public void creer(Marque marque) throws ServiceException {

        try {

            em.persist(marque);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public List<Marque> lister() throws ServiceException {

        try {

            TypedQuery<Marque> query = em.createNamedQuery("Marque.lister", Marque.class);
            return query.getResultList();
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }
}


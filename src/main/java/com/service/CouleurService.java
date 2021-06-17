package com.service;

import com.entity.Couleur;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class CouleurService {

    EntityManager em;

    public CouleurService(EntityManager em) {

        this.em = em;
    }

    public Couleur trouver(int id) throws ServiceException {

        try {

            return em.find(Couleur.class, id);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public Couleur trouverParNom(String nomCouleur) {

        TypedQuery<Couleur> query = em.createNamedQuery("Couleur.trouverParNom", Couleur.class);
        query.setParameter("nomCouleur", nomCouleur);

        if(query.getSingleResult() != null) {

            return query.getSingleResult();
        } else {

            return null;
        }
    }

    public void creer(Couleur couleur) throws ServiceException {

        try {

            em.persist(couleur);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public List<Couleur> lister() throws ServiceException {

        try {

            TypedQuery<Couleur> query = em.createNamedQuery("Couleur.lister", Couleur.class);
            return query.getResultList();
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }
}


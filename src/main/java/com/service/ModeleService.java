package com.service;

import com.entity.Couleur;
import com.entity.Modele;
import com.entity.Utilisateur;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class ModeleService {

    EntityManager em;

    public ModeleService(EntityManager em) {

        this.em = em;
    }

    public void update(Modele modele) {

        em.merge(modele);
    }

    public Modele trouver(int id) throws ServiceException {

        try {

            return em.find(Modele.class, id);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public Modele trouverParNom(String nomModele) {

        TypedQuery<Modele> query = em.createNamedQuery("Modele.trouverParNom", Modele.class);
        query.setParameter("nomModele", nomModele);

        if(query.getSingleResult() != null) {

            return query.getSingleResult();
        } else {

            return null;
        }
    }

    public void creer(Modele modele) throws ServiceException {

        try {

            em.persist(modele);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public List<Modele> lister() throws ServiceException {

        try {

            TypedQuery<Modele> query = em.createNamedQuery("Modele.lister", Modele.class);
            return query.getResultList();
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public void suppression (Modele modele) {

        em.remove(modele);
    }
}


package com.service;

import com.entity.Couleur;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class CouleurService {

    private static final Logger LOGGER = LogManager.getLogger(CouleurService.class);

    EntityManager em;

    public CouleurService(EntityManager em) {

        this.em = em;
    }

    public void update(Couleur couleur) throws ServiceException {

        try {

            LOGGER.info("Mise à jour des infos de la couleur ayant l'id: " + couleur.getIdCouleur());

            em.merge(couleur);
        } catch (Exception e) {

            LOGGER.warn("Impossible de mettre à jour les infos de la couleur ayant l'id: " + couleur.getIdCouleur());

            throw new ServiceException(e);
        }
    }

    public Couleur trouver(int id) throws ServiceException {

        try {

            LOGGER.info("Recherche de la couleur ayant l'id: " + id);

            return em.find(Couleur.class, id);
        } catch (Exception e) {

            LOGGER.warn("Impossible de rechercher la couleur ayant l'id: " + id);

            throw new ServiceException(e);
        }
    }

    public Couleur trouverParNom(String nomCouleur) throws ServiceException {

        try {

            LOGGER.info("Recherche de la couleur avec le nom: " + nomCouleur);

            TypedQuery<Couleur> query = em.createNamedQuery("Couleur.trouverParNom", Couleur.class);
            query.setParameter("nomCouleur", nomCouleur);
            return query.getSingleResult();
        } catch (Exception e) {

            LOGGER.warn("Impossible de rechercher la couleur avec le nom: " + nomCouleur);

            throw new ServiceException(e);
        }
    }

    public void creer(Couleur couleur) throws ServiceException {

        try {

            LOGGER.info("Insertion de la couleur \"" + couleur.getNomCouleur() + "\" dans la base de données");

            em.persist(couleur);
        } catch (Exception e) {

            LOGGER.warn("Impossible d'insérer la couleur \"" + couleur.getNomCouleur() + "\" dans la base de données");

            throw new ServiceException(e);
        }
    }

    public List<Couleur> lister() throws ServiceException {

        try {

            LOGGER.info("Récupération de la liste des couleurs à partir de la base de données");

            TypedQuery<Couleur> query = em.createNamedQuery("Couleur.lister", Couleur.class);
            return query.getResultList();
        } catch (Exception e) {

            LOGGER.warn("Impossible de récupérer la liste des couleurs à partir de la base de données");

            throw new ServiceException(e);
        }
    }

    public boolean checkCouleurExist(String nomCouleur) {

        Query query = em.createNamedQuery("Couleur.checkCouleurExist", Couleur.class);
        query.setParameter("nomCouleur", nomCouleur);

        int count = ((Number)query.getSingleResult()).intValue();

        return count > 0;
    }

    public void suppressionLogique(Couleur couleur) throws ServiceException {

        try {

            LOGGER.info("Désactivation de la couleur \"" + couleur.getNomCouleur() + "\" dans la base de données");

            couleur.setActifCouleur(false);
            em.persist(couleur);
        } catch (Exception e) {

            LOGGER.warn("Impossible de désactiver la couleur \"" + couleur.getNomCouleur() + "\" dans la base de données");

            throw new ServiceException(e);
        }
    }
}


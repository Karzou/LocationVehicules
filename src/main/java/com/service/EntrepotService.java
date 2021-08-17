package com.service;

import com.entity.Couleur;
import com.entity.Entrepot;
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

public class EntrepotService {

    private static final Logger LOGGER = LogManager.getLogger(EntrepotService.class);

    EntityManager em;

    public EntrepotService(EntityManager em) {

        this.em = em;
    }

    public void update(Entrepot entrepot) throws ServiceException {

        try {

            LOGGER.info("Mise à jour des infos de l'entrepot ayant l'id: " + entrepot.getIdEntrepot());

            em.merge(entrepot);
        } catch (Exception e) {

            LOGGER.warn("Impossible de mettre à jour les infos de l'entrepot ayant l'id: " + entrepot.getIdEntrepot());

            throw new ServiceException(e);
        }
    }

    public Entrepot trouver(int id) throws ServiceException {

        try {

            LOGGER.info("Recherche de l'entrepot ayant l'id: " + id);

            return em.find(Entrepot.class, id);
        } catch (Exception e) {

            LOGGER.warn("Impossible de rechercher l'entrepot ayant l'id: " + id);

            throw new ServiceException(e);
        }
    }

    public void creer(Entrepot entrepot) throws ServiceException {

        try {

            LOGGER.info("Insertion d'un nouvel entrepot dans la base de données");

            em.persist(entrepot);
        } catch (Exception e) {

            LOGGER.warn("Impossible d'insérer un nouvel entrepot dans la base de données");

            throw new ServiceException(e);
        }
    }

    public List<Entrepot> lister() throws ServiceException {

        try {

            LOGGER.info("Récupération de la liste des entrepot à partir de la base de données");

            TypedQuery<Entrepot> query = em.createNamedQuery("Entrepot.lister", Entrepot.class);
            return query.getResultList();
        } catch (Exception e) {

            LOGGER.warn("Impossible de récupérer la liste des entrepot à partir de la base de données");

            throw new ServiceException(e);
        }
    }

    public boolean checkEntrepotExist(String nomEntrepot) {

        Query query = em.createNamedQuery("Entrepot.checkEntrepotExist", Entrepot.class);
        query.setParameter("nomEntrepot", nomEntrepot);

        int count = ((Number)query.getSingleResult()).intValue();

        return count > 0;
    }

    public boolean checkOtherEntrepotExist(String nomEntrepot, int idEntrepot) {

        Query query = em.createNamedQuery("Entrepot.checkOtherEntrepotExist", Entrepot.class);
        query.setParameter("nomEntrepot", nomEntrepot);
        query.setParameter("idEntrepot", idEntrepot);

        int count = ((Number)query.getSingleResult()).intValue();

        return count > 0;
    }

    public void suppressionLogique(Entrepot entrepot) throws ServiceException {

        try {

            LOGGER.info("Désactivation de l'entrepot \"" + entrepot.getNomEntrepot() + "\" dans la base de données");

            entrepot.setActifEntrepot(false);
            em.persist(entrepot);
        } catch (Exception e) {

            LOGGER.warn("Impossible de désactiver l'entrepot \"" + entrepot.getNomEntrepot() + "\" dans la base de données");

            throw new ServiceException(e);
        }
    }
}
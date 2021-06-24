package com.service;

import com.entity.Marque;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class MarqueService {

    private static final Logger LOGGER = LogManager.getLogger(MarqueService.class);

    EntityManager em;

    public MarqueService(EntityManager em) {

        this.em = em;
    }

    public void update(Marque marque) throws ServiceException {

        try {

            LOGGER.info("Mise à jour des infos de la marque ayant l'id: " + marque.getIdMarque());

            em.merge(marque);
        } catch (Exception e) {

            LOGGER.warn("Impossible de mettre à jour les infos de la marque ayant l'id: " + marque.getIdMarque());

            throw new ServiceException(e);
        }
    }

    public Marque trouver(int id) throws ServiceException {

        try {

            LOGGER.info("Recherche de la marque ayant l'id: " + id);

            return em.find(Marque.class, id);
        } catch (Exception e) {

            LOGGER.warn("Impossible de rechercher la marque ayant l'id: " + id);

            throw new ServiceException(e);
        }
    }

    public Marque trouverParNom(String nomMarque) throws ServiceException {

        try {

            LOGGER.info("Recherche de la marque avec le nom: " + nomMarque);

            TypedQuery<Marque> query = em.createNamedQuery("Marque.trouverParNom", Marque.class);
            query.setParameter("nomMarque", nomMarque);
            return query.getSingleResult();
        } catch (Exception e) {

            LOGGER.warn("Impossible de rechercher la marque avec le nom: " + nomMarque);

            throw new ServiceException(e);
        }
    }

    public void creer(Marque marque) throws ServiceException {

        try {

            LOGGER.info("Insertion de la marque \"" + marque.getNomMarque() + "\" dans la base de données");

            em.persist(marque);
        } catch (Exception e) {

            LOGGER.warn("Impossible d'insérer la marque \"" + marque.getNomMarque() + "\" dans la base de données");

            throw new ServiceException(e);
        }
    }

    public List<Marque> lister() throws ServiceException {

        try {

            LOGGER.info("Récupération de la liste des marques à partir de la base de données");

            TypedQuery<Marque> query = em.createNamedQuery("Marque.lister", Marque.class);
            return query.getResultList();
        } catch (Exception e) {

            LOGGER.warn("Impossible de récupérer la liste des marques à partir de la base de données");

            throw new ServiceException(e);
        }
    }
}


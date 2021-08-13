package com.service;

import com.entity.Modele;
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

public class ModeleService {

    private static final Logger LOGGER = LogManager.getLogger(ModeleService.class);

    EntityManager em;

    public ModeleService(EntityManager em) {

        this.em = em;
    }

    public void update(Modele modele) throws ServiceException {

        try {

            LOGGER.info("Mise à jour des infos du modèle ayant l'id: " + modele.getIdModele());

            em.merge(modele);
        } catch (Exception e) {

            LOGGER.warn("Impossible de mettre à jour les infos du modèle ayant l'id: " + modele.getIdModele());

            throw new ServiceException(e);
        }
    }

    public Modele trouver(int id) throws ServiceException {

        try {

            LOGGER.info("Recherche du modèle ayant l'id: " + id);

            return em.find(Modele.class, id);
        } catch (Exception e) {

            LOGGER.warn("Impossible de rechercher le modèle ayant l'id: " + id);

            throw new ServiceException(e);
        }
    }

    public Modele trouverParNom(String nomModele) throws ServiceException {

        try {

            LOGGER.info("Recherche du modèle ayant le nom: " + nomModele);

            TypedQuery<Modele> query = em.createNamedQuery("Modele.trouverParNom", Modele.class);
            query.setParameter("nomModele", nomModele);
            return query.getSingleResult();
        } catch (Exception e) {

            LOGGER.warn("Impossible de rechercher le modèle ayant le nom: " + nomModele);

            throw new ServiceException(e);
        }
    }

    public void creer(Modele modele) throws ServiceException {

        try {

            LOGGER.info("Insertion du modèle \"" + modele.getNomModele() + "\" dans la base de données");

            em.persist(modele);
        } catch (Exception e) {

            LOGGER.warn("Impossible d'insérer le modèle \"" + modele.getNomModele() + "\" dans la base de données");

            throw new ServiceException(e);
        }
    }

    public List<Modele> lister() throws ServiceException {

        try {

            LOGGER.info("Récupération de la liste des modèles à partir de la base de données");

            TypedQuery<Modele> query = em.createNamedQuery("Modele.lister", Modele.class);
            return query.getResultList();
        } catch (Exception e) {

            LOGGER.warn("Impossible de récupérer la liste des modèles à partir de la base de données");

            throw new ServiceException(e);
        }
    }

    public boolean checkModeleExist(String nomModele) {

        Query query = em.createNamedQuery("Modele.checkModeleExist", Modele.class);
        query.setParameter("nomModele", nomModele);

        int count = ((Number)query.getSingleResult()).intValue();

        return count > 0;
    }

    public void suppression(Modele modele) throws ServiceException {

        try {

            LOGGER.info("Suppression du modèle \"" + modele.getNomModele() + "\" dans la base de données");

            em.remove(modele);
        } catch (Exception e) {

            LOGGER.warn("Impossible de supprimer le modèle \"" + modele.getNomModele() + "\" dans la base de données");

            throw new ServiceException(e);
        }
    }
}


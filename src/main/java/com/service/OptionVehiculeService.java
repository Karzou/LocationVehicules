package com.service;

import com.entity.OptionVehicule;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class OptionVehiculeService {

    private static final Logger LOGGER = LogManager.getLogger(OptionVehiculeService.class);

    EntityManager em;

    public OptionVehiculeService(EntityManager em) {

        this.em = em;
    }

    public void update(OptionVehicule optionVehicule) throws ServiceException {

        try {

            LOGGER.info("Mise à jour des infos de l'option véhicule avec l'id: " + optionVehicule.getIdOption());

            em.merge(optionVehicule);
        } catch (Exception e) {

            LOGGER.warn("Impossible de mettre à jour les infos de l'option véhicule avec l'id: " + optionVehicule.getIdOption());

            throw new ServiceException(e);
        }
    }

    public OptionVehicule trouver(int id) throws ServiceException {

        try {

            LOGGER.info("Recherche de l'option véhicule avec l'id: " + id);

            return em.find(OptionVehicule.class, id);
        } catch (Exception e) {

            LOGGER.warn("Impossible de rechercher l'option du véhicule avec l'id: " + id);

            throw new ServiceException(e);
        }
    }

    public void creer(OptionVehicule optionVehicule) throws ServiceException {

        try {

            LOGGER.info("Insertion de l'option véhicule \"" + optionVehicule.getNomOption() + "\" dans la base de données");

            em.persist(optionVehicule);
        } catch (Exception e) {

            LOGGER.warn("Impossible d'insérer l'option véhicule \"" + optionVehicule.getNomOption() + "\" dans la base de données");

            throw new ServiceException(e);
        }
    }

    public List<OptionVehicule> lister() throws ServiceException {

        try {

            LOGGER.info("Récupération de la liste des options véhicules à partir de la base de données");

            TypedQuery<OptionVehicule> query = em.createNamedQuery("OptionVehicule.lister", OptionVehicule.class);
            return query.getResultList();
        } catch (Exception e) {

            LOGGER.warn("Impossible de récupérer la liste des options véhicules à partir de la base de données");

            throw new ServiceException(e);
        }
    }
}

package com.service;

import com.entity.Vehicule;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

/**
 * @authors Wets Jeoffroy
 */

public class VehiculeService {

    private static final Logger LOGGER = LogManager.getLogger(VehiculeService.class);

    EntityManager em;

    public VehiculeService(EntityManager em) {

        this.em = em;
    }

    public void update(Vehicule vehicule) throws ServiceException {

        try {

            LOGGER.info("Mise à jour des infos du véhicule ayant l'id: " + vehicule.getIdVehicule());

            em.merge(vehicule);
        } catch (Exception e) {

            LOGGER.warn("Impossible de mettre à jour les infos du véhicule ayant l'id: " + vehicule.getIdVehicule());

            throw new ServiceException(e);
        }
    }

    public Vehicule trouver(int id) throws ServiceException {

        try {

            LOGGER.info("Recherche du véhicule ayant l'id: " + id);

            return em.find(Vehicule.class, id);
        } catch (Exception e) {

            LOGGER.warn("Impossible de trouver le véhicule ayant l'id: " + id);

            throw new ServiceException(e);
        }
    }

    public void creer(Vehicule vehicule) throws ServiceException {

        try {

            LOGGER.info("Insertion d'un nouveau véhicule dans la base de données");

            em.persist(vehicule);
        } catch (Exception e) {

            LOGGER.warn("Impossible d'insérer un nouveau véhicule dans la base de données");

            throw new ServiceException(e);
        }
    }

    public List<Vehicule> lister() throws ServiceException {

        try {

            LOGGER.info("Récupération de la liste des véhicules à partir de la base de données");

            TypedQuery<Vehicule> query = em.createNamedQuery("Vehicule.lister", Vehicule.class);
            return query.getResultList();
        } catch (Exception e) {

            LOGGER.warn("Impossible de récupérer la liste des véhicules à partir de la base de données");

            throw new ServiceException(e);
        }
    }

    public List<Vehicule> rechercher(int idEntrepot, Date dateDebut, Date dateFin) throws ServiceException {

        try {

            LOGGER.info("Recherche des véhicules disponibles suivant les paramètres de recherche dans la base de données");

            TypedQuery<Vehicule> query = em.createNamedQuery("Vehicule.rechercher", Vehicule.class);
            query.setParameter("idEntrepot", idEntrepot);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.getResultList();
        } catch (Exception e) {

            LOGGER.warn("Impossible de recherche les véhicules disponibles suivant les paramètres de recherche dans la base de données");

            throw new ServiceException(e);
        }
    }

    public void suppressionLogique(Vehicule vehicule) throws ServiceException {

        try {

            LOGGER.info("Désactivation du véhicule avec l'id \"" + vehicule.getIdVehicule() + "\" dans la base de données");

            vehicule.setActifVehicule(false);
            em.persist(vehicule);
        } catch (Exception e) {

            LOGGER.warn("Impossible de désactiver le véhicule avec l'id \"" + vehicule.getIdVehicule() + "\" dans la base de données");

            throw new ServiceException(e);
        }
    }

    private final static double AVERAGE_MILLIS_PER_DAYS = 86400000;

    public double periodeLocation(Date d1, Date d2) {

        return (d2.getTime() - d1.getTime()) / AVERAGE_MILLIS_PER_DAYS;
    }
}

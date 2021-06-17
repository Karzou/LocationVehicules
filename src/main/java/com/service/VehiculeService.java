package com.service;

import com.entity.Vehicule;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.util.List;

/**
 * @authors Wets Jeoffroy // Vanconingsloo Kevin
 */

public class VehiculeService {

    EntityManager em;

    public VehiculeService(EntityManager em) {

        this.em = em;
    }

    public void update(Vehicule vehicule) throws ServiceException {

        try {

            em.merge(vehicule);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public Vehicule trouver(int id) throws ServiceException {

        try {

            return em.find(Vehicule.class, id);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public void creer(Vehicule vehicule) throws ServiceException {

        try {

            em.persist(vehicule);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public List<Vehicule> lister() throws ServiceException {

        try {

            TypedQuery<Vehicule> query = em.createNamedQuery("Vehicule.lister", Vehicule.class);
            return query.getResultList();
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public List<Vehicule> rechercher(int idEntrepot, Date dateDebut, Date dateFin) throws ServiceException {

        try {

            TypedQuery<Vehicule> query = em.createNamedQuery("Vehicule.rechercher", Vehicule.class);
            query.setParameter("idEntrepot", idEntrepot);
            query.setParameter("dateDebut", dateDebut);
            query.setParameter("dateFin", dateFin);
            return query.getResultList();
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public void suppressionLogique (Vehicule vehicule) throws ServiceException {

        try {

            vehicule.setActifVehicule(false);
            em.persist(vehicule);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    private final static double AVERAGE_MILLIS_PER_DAYS = 86400000;

    public double periodeLocation(Date d1, Date d2) {

        return (d2.getTime() - d1.getTime()) / AVERAGE_MILLIS_PER_DAYS;
    }
}

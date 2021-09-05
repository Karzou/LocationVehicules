package com.service;

import com.entity.Reservation;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Partie de Shahin
 */

public class ReservationService {

    private static final Logger LOGGER = LogManager.getLogger(ReservationService.class);

    EntityManager em;

    public ReservationService(EntityManager em) {

        this.em = em;
    }

    public List<Reservation> lister() throws ServiceException {

        try {

            LOGGER.info("Récupération de la liste des réservations à partir de la base de données");

            TypedQuery<Reservation> query = em.createNamedQuery("Reservation.lister", Reservation.class);
            //query.setParameter("dateFin", date);
            return query.getResultList();
        } catch (Exception e) {

            LOGGER.warn("Impossible de récupérer la liste des réservations à partir de la base de données");

            throw new ServiceException(e);
        }
    }
}

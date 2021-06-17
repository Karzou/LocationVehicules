package com.service;

import com.entity.Reservation;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */
public class ReservationService {

    EntityManager em;

    public ReservationService(EntityManager em) {
        this.em = em;
    }

    public List<Reservation> lister() throws ServiceException {
        try {
            TypedQuery<Reservation> query = em.createNamedQuery("Reservation.lister", Reservation.class);
            //query.setParameter("dateFin", date);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}

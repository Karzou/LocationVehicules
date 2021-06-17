package com.service;

import com.entity.Contient;
import com.exception.ServiceException;

import javax.persistence.EntityManager;

/**
 * @author Wets Jeoffroy
 */

public class ContientService {

    EntityManager em;

    public ContientService(EntityManager em) {
        this.em = em;
    }

    public void creer(Contient contient) throws ServiceException {
        try {
            em.persist(contient);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}

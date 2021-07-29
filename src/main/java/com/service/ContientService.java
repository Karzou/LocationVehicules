package com.service;

import com.entity.Contient;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class ContientService {

    private static final Logger LOGGER = LogManager.getLogger(ContientService.class);

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

    public List<Contient> lister() throws ServiceException {

        try {

            TypedQuery<Contient> query = em.createNamedQuery("Contient.lister", Contient.class);
            return query.getResultList();
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public void supprimer(Contient contient) throws ServiceException {

        try {
            em.remove(contient);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}

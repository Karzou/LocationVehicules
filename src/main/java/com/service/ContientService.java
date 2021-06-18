package com.service;

import com.entity.Autorise;
import com.entity.Contient;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

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

    public List<Contient> lister() throws ServiceException {

        try {

            TypedQuery<Contient> query = em.createNamedQuery("Contient.lister", Contient.class);
            return query.getResultList();
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public void supprimer (Contient contient) {

        em.remove(contient);
    }
}

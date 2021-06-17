package com.service;

import com.entity.OptionVehicule;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Wets Jeoffroy
 */

public class OptionVehiculeService {

    EntityManager em;

    public OptionVehiculeService(EntityManager em) {

        this.em = em;
    }

    // si pas utilisé, a enlever
    public void update(OptionVehicule optionVehicule) throws ServiceException {

        try {

            em.merge(optionVehicule);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public OptionVehicule trouver(int id) throws ServiceException {

        try {

            return em.find(OptionVehicule.class, id);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public void creer(OptionVehicule optionVehicule) throws ServiceException {

        try {

            em.persist(optionVehicule);
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }

    public List<OptionVehicule> lister() throws ServiceException {

        try {

            TypedQuery<OptionVehicule> query = em.createNamedQuery("OptionVehicule.lister", OptionVehicule.class);
            return query.getResultList();
        } catch (Exception e) {

            throw new ServiceException(e);
        }
    }
}

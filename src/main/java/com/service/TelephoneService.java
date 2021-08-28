package com.service;

import com.entity.Telephone;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Vanconingsloo Kevin
 */

public class TelephoneService {

    private static final Logger LOGGER = LogManager.getLogger(AdresseService.class);

    EntityManager em;

    public TelephoneService(EntityManager em) {
        this.em = em;
    }

    public Telephone trouver(int id ) throws ServiceException {
        try {
            return em.find(Telephone.class, id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void creer(Telephone telephone) throws ServiceException {
        try {

            LOGGER.info("Création du numéro de téléphone");

            em.persist(telephone);
        } catch (Exception e) {

            LOGGER.warn("Problème lors de la création du numéro de téléphone : " + e);

            throw new ServiceException(e);
        }
    }

    public List<Telephone> lister(Utilisateur utilisateur) throws ServiceException {
        try {
            TypedQuery<Telephone> query = em.createNamedQuery("Telephone.lister", Telephone.class);
            query.setParameter("utilisateur", utilisateur);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void update (Telephone telephone){
        em.merge(telephone);
    }
}


package com.service;

import com.entity.Adresse;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

// Ce service n'est actuellement pas nécessaire mais existe pour une éventuelle mise à jour future.

/**
 * @author Vanconingsloo Kevin
 */
public class AdresseService {

    private static final Logger LOGGER = LogManager.getLogger(AdresseService.class);

    EntityManager em;

    public AdresseService(EntityManager em) {
        this.em = em;
    }

    public Adresse trouver(int id) throws ServiceException {
        try {
            LOGGER.info("Recherche de l id adresse " + id);
            return em.find(Adresse.class, id);
        } catch (Exception e) {
            LOGGER.warn("Probleme lors de la recherche id adresse : " + id);
            throw new ServiceException(e);
        }
    }

    public void creer(Adresse adresse) throws ServiceException {
        try {
            LOGGER.info("Creation d une adresse.");
            em.persist(adresse);
        } catch (Exception e) {
            LOGGER.warn("Probleme lors de la creation d une adresse : " + e);

            throw new ServiceException(e);
        }
    }

    public List<Adresse> lister() throws ServiceException {
        try {
            TypedQuery<Adresse> query = em.createNamedQuery( "Adresse.lister", Adresse.class );
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}

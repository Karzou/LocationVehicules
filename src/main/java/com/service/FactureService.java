package com.service;

import com.entity.Facture;
import com.entity.Marque;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class FactureService {
    private static final Logger LOGGER = LogManager.getLogger(FactureService.class);

    EntityManager em;

    public FactureService(EntityManager em) {

        this.em = em;
    }

    public List<Facture> findAll() throws ServiceException {
        TypedQuery<Facture> factureTypedQuery = em.createNamedQuery("Facture.getAll", Facture.class);
        return factureTypedQuery.getResultList();
    }

    public Facture findById(int id) throws ServiceException {
        try {
            LOGGER.info("recherche facture " + id);
            return em.find(Facture.class, id);
        } catch (Exception e) {
            LOGGER.error("erreur : " + e);
            throw new ServiceException(e);
        }
    }

    public void update(Facture facture) throws ServiceException {
        try {
            LOGGER.info("Mise à jour des infos de la facture ayant l'id: " + facture.getIdFacture());

            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.merge(facture);
            transaction.commit();
        } catch (Exception e) {
            LOGGER.warn("Impossible de mettre à jour les infos de la marque ayant l'id: " + facture.getIdFacture());
            throw new ServiceException(e);
        }
    }

}

package com.service;

import com.entity.Contrat;
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

public class ContratService {
    private static final Logger LOGGER = LogManager.getLogger(ContratService.class);

    EntityManager em;

    public ContratService(EntityManager em) {

        this.em = em;
    }

    public List<Contrat> findAll() throws ServiceException
    {
        TypedQuery<Contrat> contratTypedQuery = em.createNamedQuery("Contrat.getAll", Contrat.class);
        return contratTypedQuery.getResultList();
    }

    public Contrat findById(int id) throws ServiceException {
        try {
            LOGGER.info("recherche contrat " + id);
            return em.find(Contrat.class, id);
        } catch (Exception e) {
            LOGGER.error("erreur : " + e);
            throw new ServiceException(e);
        }
    }

    public void update(Contrat contrat) throws ServiceException {
        try {
            LOGGER.info("Mise à jour des infos du contrat ayant l'id: " + contrat.getIdContrat());

            em.merge(contrat);

        } catch (Exception e) {
            LOGGER.warn("Impossible de mettre à jour les infos du contrat ayant l'id: " + contrat.getIdContrat());
            throw new ServiceException(e);
        }
    }
}


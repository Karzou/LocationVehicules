package com.service;

import com.entity.Contrat;
import com.entity.Facture;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ContratService {
    private static final Logger LOGGER = LogManager.getLogger(ContratService.class);

    EntityManager em;

    public ContratService(EntityManager em) {

        this.em = em;
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
}


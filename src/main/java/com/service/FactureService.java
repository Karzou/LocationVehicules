package com.service;

import com.entity.Facture;
import com.entity.Utilisateur;
import com.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
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
}

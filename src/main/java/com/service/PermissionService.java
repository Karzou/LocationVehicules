package com.service;

import com.entity.Permission;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Vanconingsloo Kevin
 */

public class PermissionService {

    EntityManager em;

    public PermissionService(EntityManager em) {
        this.em = em;
    }

    public Permission trouver(int id) throws ServiceException {
        try {
            return em.find(Permission.class, id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void creer(Permission permission) throws ServiceException {
        try {
            em.persist(permission);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Permission> lister() throws ServiceException {
        try {
            TypedQuery<Permission> query = em.createQuery("SELECT c FROM Permission c ORDER BY c.idPermission", Permission.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }
}


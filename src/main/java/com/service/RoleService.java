package com.service;

import com.entity.Role;
import com.exception.ServiceException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Vanconingsloo Kevin
 */

public class RoleService {

    EntityManager em;

    public RoleService(EntityManager em) {
        this.em = em;
    }

    public Role trouver(int id ) throws ServiceException {
        try {
            return em.find(Role.class, id);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void creer(Role role) throws ServiceException {
        try {
            em.persist(role);
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public List<Role> lister() throws ServiceException {
        try {
            TypedQuery<Role> query = em.createNamedQuery("Role.lister", Role.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    public void supprimer (Role role){
        em.remove(role);
    }
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.automovil.persistencia;

import com.mycompany.automovil.logica.AutoMovil;
import com.mycompany.automovil.persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author yonat
 */
public class AutoMovilJpaController implements Serializable {

    public AutoMovilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AutoMovilJpaController(){
        emf = Persistence.createEntityManagerFactory("autoPU");
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(AutoMovil autoMovil) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(autoMovil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(AutoMovil autoMovil) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            autoMovil = em.merge(autoMovil);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = autoMovil.getId();
                if (findAutoMovil(id) == null) {
                    throw new NonexistentEntityException("The autoMovil with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            AutoMovil autoMovil;
            try {
                autoMovil = em.getReference(AutoMovil.class, id);
                autoMovil.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The autoMovil with id " + id + " no longer exists.", enfe);
            }
            em.remove(autoMovil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<AutoMovil> findAutoMovilEntities() {
        return findAutoMovilEntities(true, -1, -1);
    }

    public List<AutoMovil> findAutoMovilEntities(int maxResults, int firstResult) {
        return findAutoMovilEntities(false, maxResults, firstResult);
    }

    private List<AutoMovil> findAutoMovilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(AutoMovil.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public AutoMovil findAutoMovil(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(AutoMovil.class, id);
        } finally {
            em.close();
        }
    }

    public int getAutoMovilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<AutoMovil> rt = cq.from(AutoMovil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.repositorio.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unach.repositorio.dao.exceptions.NonexistentEntityException;
import mx.unach.repositorio.jpa.Finanza;
import mx.unach.repositorio.jpa.Servicio;

/**
 *
 * @author javier
 */
public class FinanzaJpaController implements Serializable {

    public FinanzaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Finanza finanza) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicioid = finanza.getServicioid();
            if (servicioid != null) {
                servicioid = em.getReference(servicioid.getClass(), servicioid.getId());
                finanza.setServicioid(servicioid);
            }
            em.persist(finanza);
            if (servicioid != null) {
                servicioid.getFinanzasList().add(finanza);
                servicioid = em.merge(servicioid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Finanza finanza) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Finanza persistentFinanza = em.find(Finanza.class, finanza.getId());
            Servicio servicioidOld = persistentFinanza.getServicioid();
            Servicio servicioidNew = finanza.getServicioid();
            if (servicioidNew != null) {
                servicioidNew = em.getReference(servicioidNew.getClass(), servicioidNew.getId());
                finanza.setServicioid(servicioidNew);
            }
            finanza = em.merge(finanza);
            if (servicioidOld != null && !servicioidOld.equals(servicioidNew)) {
                servicioidOld.getFinanzasList().remove(finanza);
                servicioidOld = em.merge(servicioidOld);
            }
            if (servicioidNew != null && !servicioidNew.equals(servicioidOld)) {
                servicioidNew.getFinanzasList().add(finanza);
                servicioidNew = em.merge(servicioidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = finanza.getId();
                if (findFinanza(id) == null) {
                    throw new NonexistentEntityException("The finanza with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Finanza finanza;
            try {
                finanza = em.getReference(Finanza.class, id);
                finanza.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The finanza with id " + id + " no longer exists.", enfe);
            }
            Servicio servicioid = finanza.getServicioid();
            if (servicioid != null) {
                servicioid.getFinanzasList().remove(finanza);
                servicioid = em.merge(servicioid);
            }
            em.remove(finanza);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Finanza> findFinanzaEntities() {
        return findFinanzaEntities(true, -1, -1);
    }

    public List<Finanza> findFinanzaEntities(int maxResults, int firstResult) {
        return findFinanzaEntities(false, maxResults, firstResult);
    }

    private List<Finanza> findFinanzaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Finanza.class));
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

    public Finanza findFinanza(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Finanza.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinanzaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Finanza> rt = cq.from(Finanza.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

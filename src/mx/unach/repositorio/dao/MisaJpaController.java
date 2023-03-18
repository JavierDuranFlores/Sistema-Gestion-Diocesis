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
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Sacerdote;

/**
 *
 * @author javier
 */
public class MisaJpaController implements Serializable {

    public MisaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Misa misa) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coro coroid = misa.getCoroid();
            if (coroid != null) {
                coroid = em.getReference(coroid.getClass(), coroid.getId());
                misa.setCoroid(coroid);
            }
            Sacerdote sacerdoteid = misa.getSacerdoteid();
            if (sacerdoteid != null) {
                sacerdoteid = em.getReference(sacerdoteid.getClass(), sacerdoteid.getId());
                misa.setSacerdoteid(sacerdoteid);
            }
            em.persist(misa);
            if (coroid != null) {
                coroid.getMisaList().add(misa);
                coroid = em.merge(coroid);
            }
            if (sacerdoteid != null) {
                sacerdoteid.getMisaList().add(misa);
                sacerdoteid = em.merge(sacerdoteid);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Misa misa) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Misa persistentMisa = em.find(Misa.class, misa.getId());
            Coro coroidOld = persistentMisa.getCoroid();
            Coro coroidNew = misa.getCoroid();
            Sacerdote sacerdoteidOld = persistentMisa.getSacerdoteid();
            Sacerdote sacerdoteidNew = misa.getSacerdoteid();
            if (coroidNew != null) {
                coroidNew = em.getReference(coroidNew.getClass(), coroidNew.getId());
                misa.setCoroid(coroidNew);
            }
            if (sacerdoteidNew != null) {
                sacerdoteidNew = em.getReference(sacerdoteidNew.getClass(), sacerdoteidNew.getId());
                misa.setSacerdoteid(sacerdoteidNew);
            }
            misa = em.merge(misa);
            if (coroidOld != null && !coroidOld.equals(coroidNew)) {
                coroidOld.getMisaList().remove(misa);
                coroidOld = em.merge(coroidOld);
            }
            if (coroidNew != null && !coroidNew.equals(coroidOld)) {
                coroidNew.getMisaList().add(misa);
                coroidNew = em.merge(coroidNew);
            }
            if (sacerdoteidOld != null && !sacerdoteidOld.equals(sacerdoteidNew)) {
                sacerdoteidOld.getMisaList().remove(misa);
                sacerdoteidOld = em.merge(sacerdoteidOld);
            }
            if (sacerdoteidNew != null && !sacerdoteidNew.equals(sacerdoteidOld)) {
                sacerdoteidNew.getMisaList().add(misa);
                sacerdoteidNew = em.merge(sacerdoteidNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = misa.getId();
                if (findMisa(id) == null) {
                    throw new NonexistentEntityException("The misa with id " + id + " no longer exists.");
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
            Misa misa;
            try {
                misa = em.getReference(Misa.class, id);
                misa.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The misa with id " + id + " no longer exists.", enfe);
            }
            Coro coroid = misa.getCoroid();
            if (coroid != null) {
                coroid.getMisaList().remove(misa);
                coroid = em.merge(coroid);
            }
            Sacerdote sacerdoteid = misa.getSacerdoteid();
            if (sacerdoteid != null) {
                sacerdoteid.getMisaList().remove(misa);
                sacerdoteid = em.merge(sacerdoteid);
            }
            em.remove(misa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Misa> findMisaEntities() {
        return findMisaEntities(true, -1, -1);
    }

    public List<Misa> findMisaEntities(int maxResults, int firstResult) {
        return findMisaEntities(false, maxResults, firstResult);
    }

    private List<Misa> findMisaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Misa.class));
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

    public Misa findMisa(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Misa.class, id);
        } finally {
            em.close();
        }
    }

    public int getMisaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Misa> rt = cq.from(Misa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

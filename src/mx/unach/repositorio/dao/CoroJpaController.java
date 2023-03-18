/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.unach.repositorio.dao;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mx.unach.repositorio.jpa.Servicio;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.unach.repositorio.dao.exceptions.IllegalOrphanException;
import mx.unach.repositorio.dao.exceptions.NonexistentEntityException;
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Misa;

/**
 *
 * @author javier
 */
public class CoroJpaController implements Serializable {

    public CoroJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Coro coro) {
        if (coro.getServicioList() == null) {
            coro.setServicioList(new ArrayList<Servicio>());
        }
        if (coro.getMisaList() == null) {
            coro.setMisaList(new ArrayList<Misa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedServicioList = new ArrayList<Servicio>();
            for (Servicio servicioListServicioToAttach : coro.getServicioList()) {
                servicioListServicioToAttach = em.getReference(servicioListServicioToAttach.getClass(), servicioListServicioToAttach.getId());
                attachedServicioList.add(servicioListServicioToAttach);
            }
            coro.setServicioList(attachedServicioList);
            List<Misa> attachedMisaList = new ArrayList<Misa>();
            for (Misa misaListMisaToAttach : coro.getMisaList()) {
                misaListMisaToAttach = em.getReference(misaListMisaToAttach.getClass(), misaListMisaToAttach.getId());
                attachedMisaList.add(misaListMisaToAttach);
            }
            coro.setMisaList(attachedMisaList);
            em.persist(coro);
            for (Servicio servicioListServicio : coro.getServicioList()) {
                Coro oldCoroidOfServicioListServicio = servicioListServicio.getCoroid();
                servicioListServicio.setCoroid(coro);
                servicioListServicio = em.merge(servicioListServicio);
                if (oldCoroidOfServicioListServicio != null) {
                    oldCoroidOfServicioListServicio.getServicioList().remove(servicioListServicio);
                    oldCoroidOfServicioListServicio = em.merge(oldCoroidOfServicioListServicio);
                }
            }
            for (Misa misaListMisa : coro.getMisaList()) {
                Coro oldCoroidOfMisaListMisa = misaListMisa.getCoroid();
                misaListMisa.setCoroid(coro);
                misaListMisa = em.merge(misaListMisa);
                if (oldCoroidOfMisaListMisa != null) {
                    oldCoroidOfMisaListMisa.getMisaList().remove(misaListMisa);
                    oldCoroidOfMisaListMisa = em.merge(oldCoroidOfMisaListMisa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Coro coro) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coro persistentCoro = em.find(Coro.class, coro.getId());
            List<Servicio> servicioListOld = persistentCoro.getServicioList();
            List<Servicio> servicioListNew = coro.getServicioList();
            List<Misa> misaListOld = persistentCoro.getMisaList();
            List<Misa> misaListNew = coro.getMisaList();
            List<String> illegalOrphanMessages = null;
            if (servicioListNew != null && misaListNew != null) {
                for (Servicio servicioListOldServicio : servicioListOld) {
                    if (!servicioListNew.contains(servicioListOldServicio)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<String>();
                        }
                        illegalOrphanMessages.add("You must retain Servicio " + servicioListOldServicio + " since its coroid field is not nullable.");
                    }
                }
                for (Misa misaListOldMisa : misaListOld) {
                    if (!misaListNew.contains(misaListOldMisa)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<String>();
                        }
                        illegalOrphanMessages.add("You must retain Misa " + misaListOldMisa + " since its coroid field is not nullable.");
                    }
                }
                if (illegalOrphanMessages != null) {
                    throw new IllegalOrphanException(illegalOrphanMessages);
                }
                List<Servicio> attachedServicioListNew = new ArrayList<Servicio>();
                for (Servicio servicioListNewServicioToAttach : servicioListNew) {
                    servicioListNewServicioToAttach = em.getReference(servicioListNewServicioToAttach.getClass(), servicioListNewServicioToAttach.getId());
                    attachedServicioListNew.add(servicioListNewServicioToAttach);
                }
                servicioListNew = attachedServicioListNew;
                coro.setServicioList(servicioListNew);
                List<Misa> attachedMisaListNew = new ArrayList<Misa>();
                for (Misa misaListNewMisaToAttach : misaListNew) {
                    misaListNewMisaToAttach = em.getReference(misaListNewMisaToAttach.getClass(), misaListNewMisaToAttach.getId());
                    attachedMisaListNew.add(misaListNewMisaToAttach);
                }
                misaListNew = attachedMisaListNew;
                coro.setMisaList(misaListNew);
                coro = em.merge(coro);
                for (Servicio servicioListNewServicio : servicioListNew) {
                    if (!servicioListOld.contains(servicioListNewServicio)) {
                        Coro oldCoroidOfServicioListNewServicio = servicioListNewServicio.getCoroid();
                        servicioListNewServicio.setCoroid(coro);
                        servicioListNewServicio = em.merge(servicioListNewServicio);
                        if (oldCoroidOfServicioListNewServicio != null && !oldCoroidOfServicioListNewServicio.equals(coro)) {
                            oldCoroidOfServicioListNewServicio.getServicioList().remove(servicioListNewServicio);
                            oldCoroidOfServicioListNewServicio = em.merge(oldCoroidOfServicioListNewServicio);
                        }
                    }
                }
                for (Misa misaListNewMisa : misaListNew) {
                    if (!misaListOld.contains(misaListNewMisa)) {
                        Coro oldCoroidOfMisaListNewMisa = misaListNewMisa.getCoroid();
                        misaListNewMisa.setCoroid(coro);
                        misaListNewMisa = em.merge(misaListNewMisa);
                        if (oldCoroidOfMisaListNewMisa != null && !oldCoroidOfMisaListNewMisa.equals(coro)) {
                            oldCoroidOfMisaListNewMisa.getMisaList().remove(misaListNewMisa);
                            oldCoroidOfMisaListNewMisa = em.merge(oldCoroidOfMisaListNewMisa);
                        }
                    }
                }
            }
            coro = em.merge(coro);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = coro.getId();
                if (findCoro(id) == null) {
                    throw new NonexistentEntityException("The coro with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Short id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coro coro;
            try {
                coro = em.getReference(Coro.class, id);
                coro.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The coro with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Servicio> servicioListOrphanCheck = coro.getServicioList();
            for (Servicio servicioListOrphanCheckServicio : servicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Coro (" + coro + ") cannot be destroyed since the Servicio " + servicioListOrphanCheckServicio + " in its servicioList field has a non-nullable coroid field.");
            }
            List<Misa> misaListOrphanCheck = coro.getMisaList();
            for (Misa misaListOrphanCheckMisa : misaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Coro (" + coro + ") cannot be destroyed since the Misa " + misaListOrphanCheckMisa + " in its misaList field has a non-nullable coroid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(coro);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Coro> findCoroEntities() {
        return findCoroEntities(true, -1, -1);
    }

    public List<Coro> findCoroEntities(int maxResults, int firstResult) {
        return findCoroEntities(false, maxResults, firstResult);
    }

    private List<Coro> findCoroEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Coro.class));
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

    public Coro findCoro(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Coro.class, id);
        } finally {
            em.close();
        }
    }

    public int getCoroCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Coro> rt = cq.from(Coro.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

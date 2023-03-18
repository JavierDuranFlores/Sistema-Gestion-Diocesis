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
import mx.unach.repositorio.jpa.Coro;
import mx.unach.repositorio.jpa.Sacerdote;
import mx.unach.repositorio.jpa.Finanza;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import mx.unach.repositorio.dao.exceptions.IllegalOrphanException;
import mx.unach.repositorio.dao.exceptions.NonexistentEntityException;
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Servicio;

/**
 *
 * @author javier
 */
public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicios) {
        if (servicios.getFinanzasList() == null) {
            servicios.setFinanzasList(new ArrayList<Finanza>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Coro coroid = servicios.getCoroid();
            if (coroid != null) {
                coroid = em.getReference(coroid.getClass(), coroid.getId());
                servicios.setCoroid(coroid);
            }
            Misa misaid = servicios.getMisaid();
            if (misaid != null) {
                misaid = em.getReference(misaid.getClass(), misaid.getId());
                servicios.setMisaid(misaid);
            }
            Sacerdote sacerdoteid = servicios.getSacerdoteid();
            if (sacerdoteid != null) {
                sacerdoteid = em.getReference(sacerdoteid.getClass(), sacerdoteid.getId());
                servicios.setSacerdoteid(sacerdoteid);
            }
            List<Finanza> attachedFinanzasList = new ArrayList<Finanza>();
            for (Finanza finanzasListFinanzasToAttach : servicios.getFinanzasList()) {
                finanzasListFinanzasToAttach = em.getReference(finanzasListFinanzasToAttach.getClass(), finanzasListFinanzasToAttach.getId());
                attachedFinanzasList.add(finanzasListFinanzasToAttach);
            }
            servicios.setFinanzasList(attachedFinanzasList);
            em.persist(servicios);
            if (coroid != null) {
                coroid.getServicioList().add(servicios);
                coroid = em.merge(coroid);
            }
            if (misaid != null) {
                misaid.getServicioList().add(servicios);
                misaid = em.merge(misaid);
            }
            if (sacerdoteid != null) {
                sacerdoteid.getServicioList().add(servicios);
                sacerdoteid = em.merge(sacerdoteid);
            }
            for (Finanza finanzasListFinanzas : servicios.getFinanzasList()) {
                Servicio oldServicioidOfFinanzasListFinanzas = finanzasListFinanzas.getServicioid();
                finanzasListFinanzas.setServicioid(servicios);
                finanzasListFinanzas = em.merge(finanzasListFinanzas);
                if (oldServicioidOfFinanzasListFinanzas != null) {
                    oldServicioidOfFinanzasListFinanzas.getFinanzasList().remove(finanzasListFinanzas);
                    oldServicioidOfFinanzasListFinanzas = em.merge(oldServicioidOfFinanzasListFinanzas);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicios = em.find(Servicio.class, servicios.getId());
            Coro coroidOld = persistentServicios.getCoroid();
            Coro coroidNew = servicios.getCoroid();
            Misa misaidOld = persistentServicios.getMisaid();
            Misa misaidNew = servicios.getMisaid();
            Sacerdote sacerdoteidOld = persistentServicios.getSacerdoteid();
            Sacerdote sacerdoteidNew = servicios.getSacerdoteid();
            List<Finanza> finanzasListOld = persistentServicios.getFinanzasList();
            List<Finanza> finanzasListNew = servicios.getFinanzasList();
            List<String> illegalOrphanMessages = null;
            if (finanzasListNew != null) {
                for (Finanza finanzasListOldFinanzas : finanzasListOld) {
                    if (!finanzasListNew.contains(finanzasListOldFinanzas)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<String>();
                        }
                        illegalOrphanMessages.add("You must retain Finanzas " + finanzasListOldFinanzas + " since its servicioid field is not nullable.");
                    }
                }
            
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (coroidNew != null) {
                coroidNew = em.getReference(coroidNew.getClass(), coroidNew.getId());
                servicios.setCoroid(coroidNew);
            }
            if (misaidNew != null) {
                misaidNew = em.getReference(misaidNew.getClass(), misaidNew.getId());
                servicios.setMisaid(misaidNew);
            }
            if (sacerdoteidNew != null) {
                sacerdoteidNew = em.getReference(sacerdoteidNew.getClass(), sacerdoteidNew.getId());
                servicios.setSacerdoteid(sacerdoteidNew);
            }
            List<Finanza> attachedFinanzasListNew = new ArrayList<Finanza>();
            if (finanzasListNew != null) {
            for (Finanza finanzasListNewFinanzasToAttach : finanzasListNew) {
                finanzasListNewFinanzasToAttach = em.getReference(finanzasListNewFinanzasToAttach.getClass(), finanzasListNewFinanzasToAttach.getId());
                attachedFinanzasListNew.add(finanzasListNewFinanzasToAttach);
            }
            }
            finanzasListNew = attachedFinanzasListNew;
            servicios.setFinanzasList(finanzasListNew);
            servicios = em.merge(servicios);
            if (coroidOld != null && !coroidOld.equals(coroidNew)) {
                coroidOld.getServicioList().remove(servicios);
                coroidOld = em.merge(coroidOld);
            }
            if (coroidNew != null && !coroidNew.equals(coroidOld)) {
                coroidNew.getServicioList().add(servicios);
                coroidNew = em.merge(coroidNew);
            }
            if (misaidOld != null && !misaidOld.equals(misaidNew)) {
                misaidOld.getServicioList().remove(servicios);
                misaidOld = em.merge(misaidOld);
            }
            if (misaidNew != null && !misaidNew.equals(misaidOld)) {
                misaidNew.getServicioList().add(servicios);
                misaidNew = em.merge(misaidNew);
            }
            if (sacerdoteidOld != null && !sacerdoteidOld.equals(sacerdoteidNew)) {
                sacerdoteidOld.getServicioList().remove(servicios);
                sacerdoteidOld = em.merge(sacerdoteidOld);
            }
            if (sacerdoteidNew != null && !sacerdoteidNew.equals(sacerdoteidOld)) {
                sacerdoteidNew.getServicioList().add(servicios);
                sacerdoteidNew = em.merge(sacerdoteidNew);
            }
            for (Finanza finanzasListNewFinanzas : finanzasListNew) {
                if (!finanzasListOld.contains(finanzasListNewFinanzas)) {
                    Servicio oldServicioidOfFinanzasListNewFinanzas = finanzasListNewFinanzas.getServicioid();
                    finanzasListNewFinanzas.setServicioid(servicios);
                    finanzasListNewFinanzas = em.merge(finanzasListNewFinanzas);
                    if (oldServicioidOfFinanzasListNewFinanzas != null && !oldServicioidOfFinanzasListNewFinanzas.equals(servicios)) {
                        oldServicioidOfFinanzasListNewFinanzas.getFinanzasList().remove(finanzasListNewFinanzas);
                        oldServicioidOfFinanzasListNewFinanzas = em.merge(oldServicioidOfFinanzasListNewFinanzas);
                    }
                }
            }
            }
            servicios = em.merge(servicios);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = servicios.getId();
                if (findServicios(id) == null) {
                    throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.");
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
            Servicio servicios;
            try {
                servicios = em.getReference(Servicio.class, id);
                servicios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Finanza> finanzasListOrphanCheck = servicios.getFinanzasList();
            for (Finanza finanzasListOrphanCheckFinanzas : finanzasListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Servicios (" + servicios + ") cannot be destroyed since the Finanzas " + finanzasListOrphanCheckFinanzas + " in its finanzasList field has a non-nullable servicioid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Coro coroid = servicios.getCoroid();
            if (coroid != null) {
                coroid.getServicioList().remove(servicios);
                coroid = em.merge(coroid);
            }
            Misa misaid = servicios.getMisaid();
            if (misaid != null) {
                misaid.getServicioList().remove(servicios);
                misaid = em.merge(misaid);
            }
            Sacerdote sacerdoteid = servicios.getSacerdoteid();
            if (sacerdoteid != null) {
                sacerdoteid.getServicioList().remove(servicios);
                sacerdoteid = em.merge(sacerdoteid);
            }
            em.remove(servicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServiciosEntities() {
        return findServiciosEntities(true, -1, -1);
    }

    public List<Servicio> findServiciosEntities(int maxResults, int firstResult) {
        return findServiciosEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServiciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
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

    public Servicio findServicios(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

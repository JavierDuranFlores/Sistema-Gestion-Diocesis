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
import mx.unach.repositorio.jpa.Misa;
import mx.unach.repositorio.jpa.Sacerdote;

/**
 *
 * @author javier
 */
public class SacerdoteJpaController implements Serializable {

    public SacerdoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sacerdote sacerdote) {
        if (sacerdote.getServicioList() == null) {
            sacerdote.setServicioList(new ArrayList<Servicio>());
        }
        if (sacerdote.getMisaList() == null) {
            sacerdote.setMisaList(new ArrayList<Misa>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Servicio> attachedServicioList = new ArrayList<Servicio>();
            for (Servicio servicioListServicioToAttach : sacerdote.getServicioList()) {
                servicioListServicioToAttach = em.getReference(servicioListServicioToAttach.getClass(), servicioListServicioToAttach.getId());
                attachedServicioList.add(servicioListServicioToAttach);
            }
            sacerdote.setServicioList(attachedServicioList);
            List<Misa> attachedMisaList = new ArrayList<Misa>();
            for (Misa misaListMisaToAttach : sacerdote.getMisaList()) {
                misaListMisaToAttach = em.getReference(misaListMisaToAttach.getClass(), misaListMisaToAttach.getId());
                attachedMisaList.add(misaListMisaToAttach);
            }
            sacerdote.setMisaList(attachedMisaList);
            em.persist(sacerdote);
            for (Servicio servicioListServicio : sacerdote.getServicioList()) {
                Sacerdote oldSacerdoteidOfServicioListServicio = servicioListServicio.getSacerdoteid();
                servicioListServicio.setSacerdoteid(sacerdote);
                servicioListServicio = em.merge(servicioListServicio);
                if (oldSacerdoteidOfServicioListServicio != null) {
                    oldSacerdoteidOfServicioListServicio.getServicioList().remove(servicioListServicio);
                    oldSacerdoteidOfServicioListServicio = em.merge(oldSacerdoteidOfServicioListServicio);
                }
            }
            for (Misa misaListMisa : sacerdote.getMisaList()) {
                Sacerdote oldSacerdoteidOfMisaListMisa = misaListMisa.getSacerdoteid();
                misaListMisa.setSacerdoteid(sacerdote);
                misaListMisa = em.merge(misaListMisa);
                if (oldSacerdoteidOfMisaListMisa != null) {
                    oldSacerdoteidOfMisaListMisa.getMisaList().remove(misaListMisa);
                    oldSacerdoteidOfMisaListMisa = em.merge(oldSacerdoteidOfMisaListMisa);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sacerdote sacerdote) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sacerdote persistentSacerdote = em.find(Sacerdote.class, sacerdote.getId());
            List<Servicio> servicioListOld = persistentSacerdote.getServicioList();
            List<Servicio> servicioListNew = sacerdote.getServicioList();
            List<Misa> misaListOld = persistentSacerdote.getMisaList();
            List<Misa> misaListNew = sacerdote.getMisaList();
            List<String> illegalOrphanMessages = null;
            System.out.println("ae");
            if (servicioListNew != null) {
                for (Servicio servicioListOldServicio : servicioListOld) {
                    System.out.println("for");
                    if (!servicioListNew.contains(servicioListOldServicio)) {
                        System.out.println("ifa");
                        if (illegalOrphanMessages == null) {
                            System.out.println("if");
                            illegalOrphanMessages = new ArrayList<String>();
                        }
                        illegalOrphanMessages.add("You must retain Servicio " + servicioListOldServicio + " since its sacerdoteid field is not nullable.");
                    }
                }
                System.out.println("ad");
                for (Misa misaListOldMisa : misaListOld) {
                    if (!misaListNew.contains(misaListOldMisa)) {
                        if (illegalOrphanMessages == null) {
                            illegalOrphanMessages = new ArrayList<String>();
                        }
                        illegalOrphanMessages.add("You must retain Misa " + misaListOldMisa + " since its sacerdoteid field is not nullable.");
                    }
                }
                if (illegalOrphanMessages != null) {
                    throw new IllegalOrphanException(illegalOrphanMessages);
                }
                List<Servicio> attachedServicioListNew = new ArrayList<Servicio>();
                for (Servicio servicioListNewServicioToAttach : servicioListNew) {
                    System.out.println("0");
                    servicioListNewServicioToAttach = em.getReference(servicioListNewServicioToAttach.getClass(), servicioListNewServicioToAttach.getId());
                    attachedServicioListNew.add(servicioListNewServicioToAttach);
                }
                System.out.println("ac");
                servicioListNew = attachedServicioListNew;
                sacerdote.setServicioList(servicioListNew);
                List<Misa> attachedMisaListNew = new ArrayList<Misa>();
                for (Misa misaListNewMisaToAttach : misaListNew) {
                    System.out.println("1");
                    misaListNewMisaToAttach = em.getReference(misaListNewMisaToAttach.getClass(), misaListNewMisaToAttach.getId());
                    attachedMisaListNew.add(misaListNewMisaToAttach);
                }

                misaListNew = attachedMisaListNew;
                sacerdote.setMisaList(misaListNew);
                sacerdote = em.merge(sacerdote);
                for (Servicio servicioListNewServicio : servicioListNew) {
                    System.out.println("2");
                    if (!servicioListOld.contains(servicioListNewServicio)) {
                        Sacerdote oldSacerdoteidOfServicioListNewServicio = servicioListNewServicio.getSacerdoteid();
                        servicioListNewServicio.setSacerdoteid(sacerdote);
                        servicioListNewServicio = em.merge(servicioListNewServicio);
                        if (oldSacerdoteidOfServicioListNewServicio != null && !oldSacerdoteidOfServicioListNewServicio.equals(sacerdote)) {
                            oldSacerdoteidOfServicioListNewServicio.getServicioList().remove(servicioListNewServicio);
                            oldSacerdoteidOfServicioListNewServicio = em.merge(oldSacerdoteidOfServicioListNewServicio);
                        }
                    }
                }
                System.out.println("ab");
                for (Misa misaListNewMisa : misaListNew) {
                    System.out.println("3");
                    if (!misaListOld.contains(misaListNewMisa)) {
                        Sacerdote oldSacerdoteidOfMisaListNewMisa = misaListNewMisa.getSacerdoteid();
                        misaListNewMisa.setSacerdoteid(sacerdote);
                        misaListNewMisa = em.merge(misaListNewMisa);
                        if (oldSacerdoteidOfMisaListNewMisa != null && !oldSacerdoteidOfMisaListNewMisa.equals(sacerdote)) {
                            oldSacerdoteidOfMisaListNewMisa.getMisaList().remove(misaListNewMisa);
                            oldSacerdoteidOfMisaListNewMisa = em.merge(oldSacerdoteidOfMisaListNewMisa);
                        }
                    }
                }
            }
            System.out.println("aa");
            sacerdote = em.merge(sacerdote);
            em.getTransaction().commit();
            System.out.println("ap");
        } catch (Exception ex) {
            ex.getMessage();
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Short id = sacerdote.getId();
                if (findSacerdote(id) == null) {
                    throw new NonexistentEntityException("The sacerdote with id " + id + " no longer exists.");
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
            Sacerdote sacerdote;
            try {
                sacerdote = em.getReference(Sacerdote.class, id);
                sacerdote.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sacerdote with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Servicio> servicioListOrphanCheck = sacerdote.getServicioList();
            for (Servicio servicioListOrphanCheckServicio : servicioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sacerdote (" + sacerdote + ") cannot be destroyed since the Servicio " + servicioListOrphanCheckServicio + " in its servicioList field has a non-nullable sacerdoteid field.");
            }
            List<Misa> misaListOrphanCheck = sacerdote.getMisaList();
            for (Misa misaListOrphanCheckMisa : misaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sacerdote (" + sacerdote + ") cannot be destroyed since the Misa " + misaListOrphanCheckMisa + " in its misaList field has a non-nullable sacerdoteid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sacerdote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sacerdote> findSacerdoteEntities() {
        return findSacerdoteEntities(true, -1, -1);
    }

    public List<Sacerdote> findSacerdoteEntities(int maxResults, int firstResult) {
        return findSacerdoteEntities(false, maxResults, firstResult);
    }

    private List<Sacerdote> findSacerdoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sacerdote.class));
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

    public Sacerdote findSacerdote(Short id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sacerdote.class, id);
        } finally {
            em.close();
        }
    }

    public int getSacerdoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sacerdote> rt = cq.from(Sacerdote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}

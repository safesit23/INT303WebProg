/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import model.Account;
import model.History;
import model.controller.exceptions.NonexistentEntityException;
import model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author jatawatsafe
 */
public class HistoryJpaController implements Serializable {

    public HistoryJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(History history) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Account accountid = history.getAccountid();
            if (accountid != null) {
                accountid = em.getReference(accountid.getClass(), accountid.getAccountid());
                history.setAccountid(accountid);
            }
            em.persist(history);
            if (accountid != null) {
                accountid.getHistoryList().add(history);
                accountid = em.merge(accountid);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(History history) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            History persistentHistory = em.find(History.class, history.getHistoryid());
            Account accountidOld = persistentHistory.getAccountid();
            Account accountidNew = history.getAccountid();
            if (accountidNew != null) {
                accountidNew = em.getReference(accountidNew.getClass(), accountidNew.getAccountid());
                history.setAccountid(accountidNew);
            }
            history = em.merge(history);
            if (accountidOld != null && !accountidOld.equals(accountidNew)) {
                accountidOld.getHistoryList().remove(history);
                accountidOld = em.merge(accountidOld);
            }
            if (accountidNew != null && !accountidNew.equals(accountidOld)) {
                accountidNew.getHistoryList().add(history);
                accountidNew = em.merge(accountidNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = history.getHistoryid();
                if (findHistory(id) == null) {
                    throw new NonexistentEntityException("The history with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            History history;
            try {
                history = em.getReference(History.class, id);
                history.getHistoryid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The history with id " + id + " no longer exists.", enfe);
            }
            Account accountid = history.getAccountid();
            if (accountid != null) {
                accountid.getHistoryList().remove(history);
                accountid = em.merge(accountid);
            }
            em.remove(history);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<History> findHistoryEntities() {
        return findHistoryEntities(true, -1, -1);
    }

    public List<History> findHistoryEntities(int maxResults, int firstResult) {
        return findHistoryEntities(false, maxResults, firstResult);
    }

    private List<History> findHistoryEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(History.class));
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

    public History findHistory(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(History.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoryCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<History> rt = cq.from(History.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

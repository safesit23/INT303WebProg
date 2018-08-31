/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.jpa.model.controller;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import sit.int303.first.jpa.model.OrderDetail;
import sit.int303.first.jpa.model.OrderDetailPK;
import sit.int303.first.jpa.model.Orders;
import sit.int303.first.jpa.model.Product;
import sit.int303.first.jpa.model.controller.exceptions.NonexistentEntityException;
import sit.int303.first.jpa.model.controller.exceptions.PreexistingEntityException;
import sit.int303.first.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author INT303
 */
public class OrderDetailJpaController implements Serializable {

    public OrderDetailJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrderDetail orderDetail) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (orderDetail.getOrderDetailPK() == null) {
            orderDetail.setOrderDetailPK(new OrderDetailPK());
        }
        orderDetail.getOrderDetailPK().setOrdernumber(orderDetail.getOrders().getOrdernumber());
        orderDetail.getOrderDetailPK().setProductcode(orderDetail.getProduct().getProductcode());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Orders orders = orderDetail.getOrders();
            if (orders != null) {
                orders = em.getReference(orders.getClass(), orders.getOrdernumber());
                orderDetail.setOrders(orders);
            }
            Product product = orderDetail.getProduct();
            if (product != null) {
                product = em.getReference(product.getClass(), product.getProductcode());
                orderDetail.setProduct(product);
            }
            em.persist(orderDetail);
            if (orders != null) {
                orders.getOrderDetailList().add(orderDetail);
                orders = em.merge(orders);
            }
            if (product != null) {
                product.getOrderDetailList().add(orderDetail);
                product = em.merge(product);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findOrderDetail(orderDetail.getOrderDetailPK()) != null) {
                throw new PreexistingEntityException("OrderDetail " + orderDetail + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrderDetail orderDetail) throws NonexistentEntityException, RollbackFailureException, Exception {
        orderDetail.getOrderDetailPK().setOrdernumber(orderDetail.getOrders().getOrdernumber());
        orderDetail.getOrderDetailPK().setProductcode(orderDetail.getProduct().getProductcode());
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrderDetail persistentOrderDetail = em.find(OrderDetail.class, orderDetail.getOrderDetailPK());
            Orders ordersOld = persistentOrderDetail.getOrders();
            Orders ordersNew = orderDetail.getOrders();
            Product productOld = persistentOrderDetail.getProduct();
            Product productNew = orderDetail.getProduct();
            if (ordersNew != null) {
                ordersNew = em.getReference(ordersNew.getClass(), ordersNew.getOrdernumber());
                orderDetail.setOrders(ordersNew);
            }
            if (productNew != null) {
                productNew = em.getReference(productNew.getClass(), productNew.getProductcode());
                orderDetail.setProduct(productNew);
            }
            orderDetail = em.merge(orderDetail);
            if (ordersOld != null && !ordersOld.equals(ordersNew)) {
                ordersOld.getOrderDetailList().remove(orderDetail);
                ordersOld = em.merge(ordersOld);
            }
            if (ordersNew != null && !ordersNew.equals(ordersOld)) {
                ordersNew.getOrderDetailList().add(orderDetail);
                ordersNew = em.merge(ordersNew);
            }
            if (productOld != null && !productOld.equals(productNew)) {
                productOld.getOrderDetailList().remove(orderDetail);
                productOld = em.merge(productOld);
            }
            if (productNew != null && !productNew.equals(productOld)) {
                productNew.getOrderDetailList().add(orderDetail);
                productNew = em.merge(productNew);
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
                OrderDetailPK id = orderDetail.getOrderDetailPK();
                if (findOrderDetail(id) == null) {
                    throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(OrderDetailPK id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            OrderDetail orderDetail;
            try {
                orderDetail = em.getReference(OrderDetail.class, id);
                orderDetail.getOrderDetailPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orderDetail with id " + id + " no longer exists.", enfe);
            }
            Orders orders = orderDetail.getOrders();
            if (orders != null) {
                orders.getOrderDetailList().remove(orderDetail);
                orders = em.merge(orders);
            }
            Product product = orderDetail.getProduct();
            if (product != null) {
                product.getOrderDetailList().remove(orderDetail);
                product = em.merge(product);
            }
            em.remove(orderDetail);
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

    public List<OrderDetail> findOrderDetailEntities() {
        return findOrderDetailEntities(true, -1, -1);
    }

    public List<OrderDetail> findOrderDetailEntities(int maxResults, int firstResult) {
        return findOrderDetailEntities(false, maxResults, firstResult);
    }

    private List<OrderDetail> findOrderDetailEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrderDetail.class));
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

    public OrderDetail findOrderDetail(OrderDetailPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrderDetail.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrderDetailCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrderDetail> rt = cq.from(OrderDetail.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

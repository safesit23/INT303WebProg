/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.jpa.model.controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import sit.int303.first.jpa.model.ProductLine;
import sit.int303.first.jpa.model.OrderDetail;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import sit.int303.first.jpa.model.Product;
import sit.int303.first.jpa.model.controller.exceptions.IllegalOrphanException;
import sit.int303.first.jpa.model.controller.exceptions.NonexistentEntityException;
import sit.int303.first.jpa.model.controller.exceptions.PreexistingEntityException;
import sit.int303.first.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author INT303
 */
public class ProductJpaController implements Serializable {

    public ProductJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Product product) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (product.getOrderDetailList() == null) {
            product.setOrderDetailList(new ArrayList<OrderDetail>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductLine productline = product.getProductline();
            if (productline != null) {
                productline = em.getReference(productline.getClass(), productline.getProductline());
                product.setProductline(productline);
            }
            List<OrderDetail> attachedOrderDetailList = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailListOrderDetailToAttach : product.getOrderDetailList()) {
                orderDetailListOrderDetailToAttach = em.getReference(orderDetailListOrderDetailToAttach.getClass(), orderDetailListOrderDetailToAttach.getOrderDetailPK());
                attachedOrderDetailList.add(orderDetailListOrderDetailToAttach);
            }
            product.setOrderDetailList(attachedOrderDetailList);
            em.persist(product);
            if (productline != null) {
                productline.getProductList().add(product);
                productline = em.merge(productline);
            }
            for (OrderDetail orderDetailListOrderDetail : product.getOrderDetailList()) {
                Product oldProductOfOrderDetailListOrderDetail = orderDetailListOrderDetail.getProduct();
                orderDetailListOrderDetail.setProduct(product);
                orderDetailListOrderDetail = em.merge(orderDetailListOrderDetail);
                if (oldProductOfOrderDetailListOrderDetail != null) {
                    oldProductOfOrderDetailListOrderDetail.getOrderDetailList().remove(orderDetailListOrderDetail);
                    oldProductOfOrderDetailListOrderDetail = em.merge(oldProductOfOrderDetailListOrderDetail);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProduct(product.getProductcode()) != null) {
                throw new PreexistingEntityException("Product " + product + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Product product) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Product persistentProduct = em.find(Product.class, product.getProductcode());
            ProductLine productlineOld = persistentProduct.getProductline();
            ProductLine productlineNew = product.getProductline();
            List<OrderDetail> orderDetailListOld = persistentProduct.getOrderDetailList();
            List<OrderDetail> orderDetailListNew = product.getOrderDetailList();
            List<String> illegalOrphanMessages = null;
            for (OrderDetail orderDetailListOldOrderDetail : orderDetailListOld) {
                if (!orderDetailListNew.contains(orderDetailListOldOrderDetail)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain OrderDetail " + orderDetailListOldOrderDetail + " since its product field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (productlineNew != null) {
                productlineNew = em.getReference(productlineNew.getClass(), productlineNew.getProductline());
                product.setProductline(productlineNew);
            }
            List<OrderDetail> attachedOrderDetailListNew = new ArrayList<OrderDetail>();
            for (OrderDetail orderDetailListNewOrderDetailToAttach : orderDetailListNew) {
                orderDetailListNewOrderDetailToAttach = em.getReference(orderDetailListNewOrderDetailToAttach.getClass(), orderDetailListNewOrderDetailToAttach.getOrderDetailPK());
                attachedOrderDetailListNew.add(orderDetailListNewOrderDetailToAttach);
            }
            orderDetailListNew = attachedOrderDetailListNew;
            product.setOrderDetailList(orderDetailListNew);
            product = em.merge(product);
            if (productlineOld != null && !productlineOld.equals(productlineNew)) {
                productlineOld.getProductList().remove(product);
                productlineOld = em.merge(productlineOld);
            }
            if (productlineNew != null && !productlineNew.equals(productlineOld)) {
                productlineNew.getProductList().add(product);
                productlineNew = em.merge(productlineNew);
            }
            for (OrderDetail orderDetailListNewOrderDetail : orderDetailListNew) {
                if (!orderDetailListOld.contains(orderDetailListNewOrderDetail)) {
                    Product oldProductOfOrderDetailListNewOrderDetail = orderDetailListNewOrderDetail.getProduct();
                    orderDetailListNewOrderDetail.setProduct(product);
                    orderDetailListNewOrderDetail = em.merge(orderDetailListNewOrderDetail);
                    if (oldProductOfOrderDetailListNewOrderDetail != null && !oldProductOfOrderDetailListNewOrderDetail.equals(product)) {
                        oldProductOfOrderDetailListNewOrderDetail.getOrderDetailList().remove(orderDetailListNewOrderDetail);
                        oldProductOfOrderDetailListNewOrderDetail = em.merge(oldProductOfOrderDetailListNewOrderDetail);
                    }
                }
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
                String id = product.getProductcode();
                if (findProduct(id) == null) {
                    throw new NonexistentEntityException("The product with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Product product;
            try {
                product = em.getReference(Product.class, id);
                product.getProductcode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The product with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<OrderDetail> orderDetailListOrphanCheck = product.getOrderDetailList();
            for (OrderDetail orderDetailListOrphanCheckOrderDetail : orderDetailListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Product (" + product + ") cannot be destroyed since the OrderDetail " + orderDetailListOrphanCheckOrderDetail + " in its orderDetailList field has a non-nullable product field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            ProductLine productline = product.getProductline();
            if (productline != null) {
                productline.getProductList().remove(product);
                productline = em.merge(productline);
            }
            em.remove(product);
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

    public List<Product> findProductEntities() {
        return findProductEntities(true, -1, -1);
    }

    public List<Product> findProductEntities(int maxResults, int firstResult) {
        return findProductEntities(false, maxResults, firstResult);
    }

    private List<Product> findProductEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Product.class));
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

    public Product findProduct(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Product.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Product> rt = cq.from(Product.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

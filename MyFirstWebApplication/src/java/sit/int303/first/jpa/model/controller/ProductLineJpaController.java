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
import sit.int303.first.jpa.model.Product;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import sit.int303.first.jpa.model.ProductLine;
import sit.int303.first.jpa.model.controller.exceptions.IllegalOrphanException;
import sit.int303.first.jpa.model.controller.exceptions.NonexistentEntityException;
import sit.int303.first.jpa.model.controller.exceptions.PreexistingEntityException;
import sit.int303.first.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author INT303
 */
public class ProductLineJpaController implements Serializable {

    public ProductLineJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ProductLine productLine) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (productLine.getProductList() == null) {
            productLine.setProductList(new ArrayList<Product>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            List<Product> attachedProductList = new ArrayList<Product>();
            for (Product productListProductToAttach : productLine.getProductList()) {
                productListProductToAttach = em.getReference(productListProductToAttach.getClass(), productListProductToAttach.getProductcode());
                attachedProductList.add(productListProductToAttach);
            }
            productLine.setProductList(attachedProductList);
            em.persist(productLine);
            for (Product productListProduct : productLine.getProductList()) {
                ProductLine oldProductlineOfProductListProduct = productListProduct.getProductline();
                productListProduct.setProductline(productLine);
                productListProduct = em.merge(productListProduct);
                if (oldProductlineOfProductListProduct != null) {
                    oldProductlineOfProductListProduct.getProductList().remove(productListProduct);
                    oldProductlineOfProductListProduct = em.merge(oldProductlineOfProductListProduct);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findProductLine(productLine.getProductline()) != null) {
                throw new PreexistingEntityException("ProductLine " + productLine + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ProductLine productLine) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ProductLine persistentProductLine = em.find(ProductLine.class, productLine.getProductline());
            List<Product> productListOld = persistentProductLine.getProductList();
            List<Product> productListNew = productLine.getProductList();
            List<String> illegalOrphanMessages = null;
            for (Product productListOldProduct : productListOld) {
                if (!productListNew.contains(productListOldProduct)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Product " + productListOldProduct + " since its productline field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Product> attachedProductListNew = new ArrayList<Product>();
            for (Product productListNewProductToAttach : productListNew) {
                productListNewProductToAttach = em.getReference(productListNewProductToAttach.getClass(), productListNewProductToAttach.getProductcode());
                attachedProductListNew.add(productListNewProductToAttach);
            }
            productListNew = attachedProductListNew;
            productLine.setProductList(productListNew);
            productLine = em.merge(productLine);
            for (Product productListNewProduct : productListNew) {
                if (!productListOld.contains(productListNewProduct)) {
                    ProductLine oldProductlineOfProductListNewProduct = productListNewProduct.getProductline();
                    productListNewProduct.setProductline(productLine);
                    productListNewProduct = em.merge(productListNewProduct);
                    if (oldProductlineOfProductListNewProduct != null && !oldProductlineOfProductListNewProduct.equals(productLine)) {
                        oldProductlineOfProductListNewProduct.getProductList().remove(productListNewProduct);
                        oldProductlineOfProductListNewProduct = em.merge(oldProductlineOfProductListNewProduct);
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
                String id = productLine.getProductline();
                if (findProductLine(id) == null) {
                    throw new NonexistentEntityException("The productLine with id " + id + " no longer exists.");
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
            ProductLine productLine;
            try {
                productLine = em.getReference(ProductLine.class, id);
                productLine.getProductline();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The productLine with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Product> productListOrphanCheck = productLine.getProductList();
            for (Product productListOrphanCheckProduct : productListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ProductLine (" + productLine + ") cannot be destroyed since the Product " + productListOrphanCheckProduct + " in its productList field has a non-nullable productline field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(productLine);
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

    public List<ProductLine> findProductLineEntities() {
        return findProductLineEntities(true, -1, -1);
    }

    public List<ProductLine> findProductLineEntities(int maxResults, int firstResult) {
        return findProductLineEntities(false, maxResults, firstResult);
    }

    private List<ProductLine> findProductLineEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ProductLine.class));
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

    public ProductLine findProductLine(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ProductLine.class, id);
        } finally {
            em.close();
        }
    }

    public int getProductLineCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ProductLine> rt = cq.from(ProductLine.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

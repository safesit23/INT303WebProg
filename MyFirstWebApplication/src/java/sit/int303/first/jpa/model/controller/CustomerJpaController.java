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
import sit.int303.first.jpa.model.Employee;
import sit.int303.first.jpa.model.Payment;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import sit.int303.first.jpa.model.Customer;
import sit.int303.first.jpa.model.Orders;
import sit.int303.first.jpa.model.controller.exceptions.IllegalOrphanException;
import sit.int303.first.jpa.model.controller.exceptions.NonexistentEntityException;
import sit.int303.first.jpa.model.controller.exceptions.PreexistingEntityException;
import sit.int303.first.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author INT303
 */
public class CustomerJpaController implements Serializable {

    public CustomerJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customer customer) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (customer.getPaymentList() == null) {
            customer.setPaymentList(new ArrayList<Payment>());
        }
        if (customer.getOrdersList() == null) {
            customer.setOrdersList(new ArrayList<Orders>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee salesrepemployeenumber = customer.getSalesrepemployeenumber();
            if (salesrepemployeenumber != null) {
                salesrepemployeenumber = em.getReference(salesrepemployeenumber.getClass(), salesrepemployeenumber.getEmployeenumber());
                customer.setSalesrepemployeenumber(salesrepemployeenumber);
            }
            List<Payment> attachedPaymentList = new ArrayList<Payment>();
            for (Payment paymentListPaymentToAttach : customer.getPaymentList()) {
                paymentListPaymentToAttach = em.getReference(paymentListPaymentToAttach.getClass(), paymentListPaymentToAttach.getPaymentPK());
                attachedPaymentList.add(paymentListPaymentToAttach);
            }
            customer.setPaymentList(attachedPaymentList);
            List<Orders> attachedOrdersList = new ArrayList<Orders>();
            for (Orders ordersListOrdersToAttach : customer.getOrdersList()) {
                ordersListOrdersToAttach = em.getReference(ordersListOrdersToAttach.getClass(), ordersListOrdersToAttach.getOrdernumber());
                attachedOrdersList.add(ordersListOrdersToAttach);
            }
            customer.setOrdersList(attachedOrdersList);
            em.persist(customer);
            if (salesrepemployeenumber != null) {
                salesrepemployeenumber.getCustomerList().add(customer);
                salesrepemployeenumber = em.merge(salesrepemployeenumber);
            }
            for (Payment paymentListPayment : customer.getPaymentList()) {
                Customer oldCustomerOfPaymentListPayment = paymentListPayment.getCustomer();
                paymentListPayment.setCustomer(customer);
                paymentListPayment = em.merge(paymentListPayment);
                if (oldCustomerOfPaymentListPayment != null) {
                    oldCustomerOfPaymentListPayment.getPaymentList().remove(paymentListPayment);
                    oldCustomerOfPaymentListPayment = em.merge(oldCustomerOfPaymentListPayment);
                }
            }
            for (Orders ordersListOrders : customer.getOrdersList()) {
                Customer oldCustomernumberOfOrdersListOrders = ordersListOrders.getCustomernumber();
                ordersListOrders.setCustomernumber(customer);
                ordersListOrders = em.merge(ordersListOrders);
                if (oldCustomernumberOfOrdersListOrders != null) {
                    oldCustomernumberOfOrdersListOrders.getOrdersList().remove(ordersListOrders);
                    oldCustomernumberOfOrdersListOrders = em.merge(oldCustomernumberOfOrdersListOrders);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findCustomer(customer.getCustomernumber()) != null) {
                throw new PreexistingEntityException("Customer " + customer + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customer customer) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customer persistentCustomer = em.find(Customer.class, customer.getCustomernumber());
            Employee salesrepemployeenumberOld = persistentCustomer.getSalesrepemployeenumber();
            Employee salesrepemployeenumberNew = customer.getSalesrepemployeenumber();
            List<Payment> paymentListOld = persistentCustomer.getPaymentList();
            List<Payment> paymentListNew = customer.getPaymentList();
            List<Orders> ordersListOld = persistentCustomer.getOrdersList();
            List<Orders> ordersListNew = customer.getOrdersList();
            List<String> illegalOrphanMessages = null;
            for (Payment paymentListOldPayment : paymentListOld) {
                if (!paymentListNew.contains(paymentListOldPayment)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Payment " + paymentListOldPayment + " since its customer field is not nullable.");
                }
            }
            for (Orders ordersListOldOrders : ordersListOld) {
                if (!ordersListNew.contains(ordersListOldOrders)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Orders " + ordersListOldOrders + " since its customernumber field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (salesrepemployeenumberNew != null) {
                salesrepemployeenumberNew = em.getReference(salesrepemployeenumberNew.getClass(), salesrepemployeenumberNew.getEmployeenumber());
                customer.setSalesrepemployeenumber(salesrepemployeenumberNew);
            }
            List<Payment> attachedPaymentListNew = new ArrayList<Payment>();
            for (Payment paymentListNewPaymentToAttach : paymentListNew) {
                paymentListNewPaymentToAttach = em.getReference(paymentListNewPaymentToAttach.getClass(), paymentListNewPaymentToAttach.getPaymentPK());
                attachedPaymentListNew.add(paymentListNewPaymentToAttach);
            }
            paymentListNew = attachedPaymentListNew;
            customer.setPaymentList(paymentListNew);
            List<Orders> attachedOrdersListNew = new ArrayList<Orders>();
            for (Orders ordersListNewOrdersToAttach : ordersListNew) {
                ordersListNewOrdersToAttach = em.getReference(ordersListNewOrdersToAttach.getClass(), ordersListNewOrdersToAttach.getOrdernumber());
                attachedOrdersListNew.add(ordersListNewOrdersToAttach);
            }
            ordersListNew = attachedOrdersListNew;
            customer.setOrdersList(ordersListNew);
            customer = em.merge(customer);
            if (salesrepemployeenumberOld != null && !salesrepemployeenumberOld.equals(salesrepemployeenumberNew)) {
                salesrepemployeenumberOld.getCustomerList().remove(customer);
                salesrepemployeenumberOld = em.merge(salesrepemployeenumberOld);
            }
            if (salesrepemployeenumberNew != null && !salesrepemployeenumberNew.equals(salesrepemployeenumberOld)) {
                salesrepemployeenumberNew.getCustomerList().add(customer);
                salesrepemployeenumberNew = em.merge(salesrepemployeenumberNew);
            }
            for (Payment paymentListNewPayment : paymentListNew) {
                if (!paymentListOld.contains(paymentListNewPayment)) {
                    Customer oldCustomerOfPaymentListNewPayment = paymentListNewPayment.getCustomer();
                    paymentListNewPayment.setCustomer(customer);
                    paymentListNewPayment = em.merge(paymentListNewPayment);
                    if (oldCustomerOfPaymentListNewPayment != null && !oldCustomerOfPaymentListNewPayment.equals(customer)) {
                        oldCustomerOfPaymentListNewPayment.getPaymentList().remove(paymentListNewPayment);
                        oldCustomerOfPaymentListNewPayment = em.merge(oldCustomerOfPaymentListNewPayment);
                    }
                }
            }
            for (Orders ordersListNewOrders : ordersListNew) {
                if (!ordersListOld.contains(ordersListNewOrders)) {
                    Customer oldCustomernumberOfOrdersListNewOrders = ordersListNewOrders.getCustomernumber();
                    ordersListNewOrders.setCustomernumber(customer);
                    ordersListNewOrders = em.merge(ordersListNewOrders);
                    if (oldCustomernumberOfOrdersListNewOrders != null && !oldCustomernumberOfOrdersListNewOrders.equals(customer)) {
                        oldCustomernumberOfOrdersListNewOrders.getOrdersList().remove(ordersListNewOrders);
                        oldCustomernumberOfOrdersListNewOrders = em.merge(oldCustomernumberOfOrdersListNewOrders);
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
                Integer id = customer.getCustomernumber();
                if (findCustomer(id) == null) {
                    throw new NonexistentEntityException("The customer with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customer customer;
            try {
                customer = em.getReference(Customer.class, id);
                customer.getCustomernumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customer with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Payment> paymentListOrphanCheck = customer.getPaymentList();
            for (Payment paymentListOrphanCheckPayment : paymentListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the Payment " + paymentListOrphanCheckPayment + " in its paymentList field has a non-nullable customer field.");
            }
            List<Orders> ordersListOrphanCheck = customer.getOrdersList();
            for (Orders ordersListOrphanCheckOrders : ordersListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Customer (" + customer + ") cannot be destroyed since the Orders " + ordersListOrphanCheckOrders + " in its ordersList field has a non-nullable customernumber field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Employee salesrepemployeenumber = customer.getSalesrepemployeenumber();
            if (salesrepemployeenumber != null) {
                salesrepemployeenumber.getCustomerList().remove(customer);
                salesrepemployeenumber = em.merge(salesrepemployeenumber);
            }
            em.remove(customer);
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

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customer.class));
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

    public Customer findCustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customer> rt = cq.from(Customer.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

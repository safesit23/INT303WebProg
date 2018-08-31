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
import sit.int303.first.jpa.model.Office;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import sit.int303.first.jpa.model.Customer;
import sit.int303.first.jpa.model.controller.exceptions.NonexistentEntityException;
import sit.int303.first.jpa.model.controller.exceptions.PreexistingEntityException;
import sit.int303.first.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author INT303
 */
public class EmployeeJpaController implements Serializable {

    public EmployeeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employee employee) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (employee.getEmployeeList() == null) {
            employee.setEmployeeList(new ArrayList<Employee>());
        }
        if (employee.getCustomerList() == null) {
            employee.setCustomerList(new ArrayList<Customer>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee reportsto = employee.getReportsto();
            if (reportsto != null) {
                reportsto = em.getReference(reportsto.getClass(), reportsto.getEmployeenumber());
                employee.setReportsto(reportsto);
            }
            Office officecode = employee.getOfficecode();
            if (officecode != null) {
                officecode = em.getReference(officecode.getClass(), officecode.getOfficecode());
                employee.setOfficecode(officecode);
            }
            List<Employee> attachedEmployeeList = new ArrayList<Employee>();
            for (Employee employeeListEmployeeToAttach : employee.getEmployeeList()) {
                employeeListEmployeeToAttach = em.getReference(employeeListEmployeeToAttach.getClass(), employeeListEmployeeToAttach.getEmployeenumber());
                attachedEmployeeList.add(employeeListEmployeeToAttach);
            }
            employee.setEmployeeList(attachedEmployeeList);
            List<Customer> attachedCustomerList = new ArrayList<Customer>();
            for (Customer customerListCustomerToAttach : employee.getCustomerList()) {
                customerListCustomerToAttach = em.getReference(customerListCustomerToAttach.getClass(), customerListCustomerToAttach.getCustomernumber());
                attachedCustomerList.add(customerListCustomerToAttach);
            }
            employee.setCustomerList(attachedCustomerList);
            em.persist(employee);
            if (reportsto != null) {
                reportsto.getEmployeeList().add(employee);
                reportsto = em.merge(reportsto);
            }
            if (officecode != null) {
                officecode.getEmployeeList().add(employee);
                officecode = em.merge(officecode);
            }
            for (Employee employeeListEmployee : employee.getEmployeeList()) {
                Employee oldReportstoOfEmployeeListEmployee = employeeListEmployee.getReportsto();
                employeeListEmployee.setReportsto(employee);
                employeeListEmployee = em.merge(employeeListEmployee);
                if (oldReportstoOfEmployeeListEmployee != null) {
                    oldReportstoOfEmployeeListEmployee.getEmployeeList().remove(employeeListEmployee);
                    oldReportstoOfEmployeeListEmployee = em.merge(oldReportstoOfEmployeeListEmployee);
                }
            }
            for (Customer customerListCustomer : employee.getCustomerList()) {
                Employee oldSalesrepemployeenumberOfCustomerListCustomer = customerListCustomer.getSalesrepemployeenumber();
                customerListCustomer.setSalesrepemployeenumber(employee);
                customerListCustomer = em.merge(customerListCustomer);
                if (oldSalesrepemployeenumberOfCustomerListCustomer != null) {
                    oldSalesrepemployeenumberOfCustomerListCustomer.getCustomerList().remove(customerListCustomer);
                    oldSalesrepemployeenumberOfCustomerListCustomer = em.merge(oldSalesrepemployeenumberOfCustomerListCustomer);
                }
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            if (findEmployee(employee.getEmployeenumber()) != null) {
                throw new PreexistingEntityException("Employee " + employee + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employee employee) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee persistentEmployee = em.find(Employee.class, employee.getEmployeenumber());
            Employee reportstoOld = persistentEmployee.getReportsto();
            Employee reportstoNew = employee.getReportsto();
            Office officecodeOld = persistentEmployee.getOfficecode();
            Office officecodeNew = employee.getOfficecode();
            List<Employee> employeeListOld = persistentEmployee.getEmployeeList();
            List<Employee> employeeListNew = employee.getEmployeeList();
            List<Customer> customerListOld = persistentEmployee.getCustomerList();
            List<Customer> customerListNew = employee.getCustomerList();
            if (reportstoNew != null) {
                reportstoNew = em.getReference(reportstoNew.getClass(), reportstoNew.getEmployeenumber());
                employee.setReportsto(reportstoNew);
            }
            if (officecodeNew != null) {
                officecodeNew = em.getReference(officecodeNew.getClass(), officecodeNew.getOfficecode());
                employee.setOfficecode(officecodeNew);
            }
            List<Employee> attachedEmployeeListNew = new ArrayList<Employee>();
            for (Employee employeeListNewEmployeeToAttach : employeeListNew) {
                employeeListNewEmployeeToAttach = em.getReference(employeeListNewEmployeeToAttach.getClass(), employeeListNewEmployeeToAttach.getEmployeenumber());
                attachedEmployeeListNew.add(employeeListNewEmployeeToAttach);
            }
            employeeListNew = attachedEmployeeListNew;
            employee.setEmployeeList(employeeListNew);
            List<Customer> attachedCustomerListNew = new ArrayList<Customer>();
            for (Customer customerListNewCustomerToAttach : customerListNew) {
                customerListNewCustomerToAttach = em.getReference(customerListNewCustomerToAttach.getClass(), customerListNewCustomerToAttach.getCustomernumber());
                attachedCustomerListNew.add(customerListNewCustomerToAttach);
            }
            customerListNew = attachedCustomerListNew;
            employee.setCustomerList(customerListNew);
            employee = em.merge(employee);
            if (reportstoOld != null && !reportstoOld.equals(reportstoNew)) {
                reportstoOld.getEmployeeList().remove(employee);
                reportstoOld = em.merge(reportstoOld);
            }
            if (reportstoNew != null && !reportstoNew.equals(reportstoOld)) {
                reportstoNew.getEmployeeList().add(employee);
                reportstoNew = em.merge(reportstoNew);
            }
            if (officecodeOld != null && !officecodeOld.equals(officecodeNew)) {
                officecodeOld.getEmployeeList().remove(employee);
                officecodeOld = em.merge(officecodeOld);
            }
            if (officecodeNew != null && !officecodeNew.equals(officecodeOld)) {
                officecodeNew.getEmployeeList().add(employee);
                officecodeNew = em.merge(officecodeNew);
            }
            for (Employee employeeListOldEmployee : employeeListOld) {
                if (!employeeListNew.contains(employeeListOldEmployee)) {
                    employeeListOldEmployee.setReportsto(null);
                    employeeListOldEmployee = em.merge(employeeListOldEmployee);
                }
            }
            for (Employee employeeListNewEmployee : employeeListNew) {
                if (!employeeListOld.contains(employeeListNewEmployee)) {
                    Employee oldReportstoOfEmployeeListNewEmployee = employeeListNewEmployee.getReportsto();
                    employeeListNewEmployee.setReportsto(employee);
                    employeeListNewEmployee = em.merge(employeeListNewEmployee);
                    if (oldReportstoOfEmployeeListNewEmployee != null && !oldReportstoOfEmployeeListNewEmployee.equals(employee)) {
                        oldReportstoOfEmployeeListNewEmployee.getEmployeeList().remove(employeeListNewEmployee);
                        oldReportstoOfEmployeeListNewEmployee = em.merge(oldReportstoOfEmployeeListNewEmployee);
                    }
                }
            }
            for (Customer customerListOldCustomer : customerListOld) {
                if (!customerListNew.contains(customerListOldCustomer)) {
                    customerListOldCustomer.setSalesrepemployeenumber(null);
                    customerListOldCustomer = em.merge(customerListOldCustomer);
                }
            }
            for (Customer customerListNewCustomer : customerListNew) {
                if (!customerListOld.contains(customerListNewCustomer)) {
                    Employee oldSalesrepemployeenumberOfCustomerListNewCustomer = customerListNewCustomer.getSalesrepemployeenumber();
                    customerListNewCustomer.setSalesrepemployeenumber(employee);
                    customerListNewCustomer = em.merge(customerListNewCustomer);
                    if (oldSalesrepemployeenumberOfCustomerListNewCustomer != null && !oldSalesrepemployeenumberOfCustomerListNewCustomer.equals(employee)) {
                        oldSalesrepemployeenumberOfCustomerListNewCustomer.getCustomerList().remove(customerListNewCustomer);
                        oldSalesrepemployeenumberOfCustomerListNewCustomer = em.merge(oldSalesrepemployeenumberOfCustomerListNewCustomer);
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
                Integer id = employee.getEmployeenumber();
                if (findEmployee(id) == null) {
                    throw new NonexistentEntityException("The employee with id " + id + " no longer exists.");
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
            Employee employee;
            try {
                employee = em.getReference(Employee.class, id);
                employee.getEmployeenumber();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employee with id " + id + " no longer exists.", enfe);
            }
            Employee reportsto = employee.getReportsto();
            if (reportsto != null) {
                reportsto.getEmployeeList().remove(employee);
                reportsto = em.merge(reportsto);
            }
            Office officecode = employee.getOfficecode();
            if (officecode != null) {
                officecode.getEmployeeList().remove(employee);
                officecode = em.merge(officecode);
            }
            List<Employee> employeeList = employee.getEmployeeList();
            for (Employee employeeListEmployee : employeeList) {
                employeeListEmployee.setReportsto(null);
                employeeListEmployee = em.merge(employeeListEmployee);
            }
            List<Customer> customerList = employee.getCustomerList();
            for (Customer customerListCustomer : customerList) {
                customerListCustomer.setSalesrepemployeenumber(null);
                customerListCustomer = em.merge(customerListCustomer);
            }
            em.remove(employee);
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

    public List<Employee> findEmployeeEntities() {
        return findEmployeeEntities(true, -1, -1);
    }

    public List<Employee> findEmployeeEntities(int maxResults, int firstResult) {
        return findEmployeeEntities(false, maxResults, firstResult);
    }

    private List<Employee> findEmployeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employee.class));
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

    public Employee findEmployee(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employee> rt = cq.from(Employee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}

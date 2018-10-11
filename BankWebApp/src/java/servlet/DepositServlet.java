/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.UserTransaction;
import model.Account;
import model.History;
import model.controller.AccountJpaController;
import model.controller.HistoryJpaController;
import model.controller.exceptions.NonexistentEntityException;
import model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author jatawatsafe
 */
public class DepositServlet extends HttpServlet {
@PersistenceUnit (unitName = "BankWebAppPU")
EntityManagerFactory emf;
@Resource
UserTransaction utx;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String depositText = request.getParameter("deposit");
        Account accSession = (Account) session.getAttribute("account");
        if(depositText !=null){
            int deposit = Integer.parseInt(depositText);
            String message = null;
            AccountJpaController aCtrl = new AccountJpaController(utx, emf);
            HistoryJpaController hCtrl = new HistoryJpaController(utx, emf);
            Account account = aCtrl.findAccount(accSession.getAccountid());
            if(account !=null){
                boolean checkDo = account.deposit(deposit);
                if(checkDo){
                    History history = new History(account,"deposit", deposit, new Date(), account.getBalance());
                    try {
                        aCtrl.edit(account);
                        hCtrl.create(history);
                        message = "Deposit Successful";
                        session.setAttribute("account", account);
                    } catch (NonexistentEntityException ex) {
                        Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (RollbackFailureException ex) {
                        Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(DepositServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } 
                }else{
                    message = "Deposit Unsuccessful";
                }
                request.setAttribute("message", message);
            }
            response.sendRedirect("MyAccount.jsp");
            return;
        }
        getServletContext().getRequestDispatcher("/Deposit.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

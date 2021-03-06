/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.transaction.UserTransaction;
import sit.int303.first.jpa.model.Register;
import sit.int303.first.jpa.model.controller.RegisterJpaController;
import sit.int303.first.jpa.model.controller.exceptions.RollbackFailureException;

/**
 *
 * @author jatawatsafe
 */
public class ActivationServlet extends HttpServlet {

    @PersistenceUnit(unitName = "MyFirstWebAppPU")
    EntityManagerFactory emf;
    
    @Resource
    UserTransaction utx;

    /**
     *
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
        response.setContentType("text/html;charset=UTF-8");
        String email = request.getParameter("email");
        String activatekey = request.getParameter("activationkey");
        if (email != null && activatekey != null) {
            String message ="Activation Failed";
            RegisterJpaController regJpaCtrl = new RegisterJpaController(utx, emf);
            Register reg = regJpaCtrl.findRegister(email);
            
            if (activatekey.equals(reg.getActivatekey())) {
                reg.setActivatedate(new Date());
                try {
                    regJpaCtrl.edit(reg);
                    message = "Congratulation !!! Activation Complete";
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(ActivationServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(ActivationServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            request.setAttribute("message", message);
            getServletContext().getRequestDispatcher("/Activation.jsp").forward(request, response);
        }
        getServletContext().getRequestDispatcher("/RegisterForm.jsp").forward(request, response);
    }
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

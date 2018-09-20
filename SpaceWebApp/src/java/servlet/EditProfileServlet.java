/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import model.Astronomer;
import model.jpa.AstronomerJpaController;
import model.jpa.exceptions.RollbackFailureException;

/**
 *
 * @author jatawatsafe
 */
public class EditProfileServlet extends HttpServlet {

    @PersistenceUnit(unitName = "SpaceWebAppPU")
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
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (name != null && email != null && password != null) {
            Astronomer oldAst = (Astronomer) session.getAttribute("astronomer");
            boolean update = false;
            System.out.println("------find old-----");
            if (oldAst != null) {
                int id = oldAst.getId();
                AstronomerJpaController ctrl = new AstronomerJpaController(utx, emf);
                Astronomer ast = ctrl.findAstronomer(id);
                System.out.println("------find ast-----");
                if (ast != null && ast.getPassword().equals(password)) {
                    ast.setName(name);
                    ast.setEmail(email);
                    System.out.println("------set ast-----");
                    try {
                        System.out.println("--------edit------");
                        ctrl.edit(ast);
                        session.setAttribute("astronomer", ast);
                        System.out.println("-------edit finish------");
                        update = true;
                    } catch (RollbackFailureException ex) {
                        Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(EditProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            session.setAttribute("updateProfile", update);
            response.sendRedirect("EditProfile");
            //getServletContext().getRequestDispatcher("/EditProfile.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/EditProfile.jsp").forward(request, response);
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

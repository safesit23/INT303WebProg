/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AllStudent;
import model.Student;

/**
 *
 * @author jatawatsafe
 */
public class AddServlet extends HttpServlet {

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
        HttpSession session = request.getSession(true);
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String scoreText = request.getParameter("score");
        if (id != null & name != null & scoreText != null) {
            int score = Integer.parseInt(scoreText);
            AllStudent allStudent = (AllStudent) session.getAttribute("allStudent");
            if (allStudent == null) {
                allStudent = new AllStudent();
                session.setAttribute("allStudent", allStudent);
                System.out.println("------Create new allStudent");
            }
            Student std = new Student(id, name, score);
            boolean check = allStudent.add(std);
            String message;
            if(check){
                message = "Add Successful";
            }else{
                message = "Add not successful";
            }
            session.setAttribute("message", message);
            System.out.println("------------Number of Student: "+allStudent.getAllData().size());
            List<Student> dataList = allStudent.getAllData();
            session.setAttribute("allStudent", allStudent);
        }
        request.getServletContext().getRequestDispatcher("/AddRemoveStudent.jsp").forward(request, response);
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

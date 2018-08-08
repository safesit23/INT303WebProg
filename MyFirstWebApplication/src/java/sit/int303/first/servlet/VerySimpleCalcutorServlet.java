/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sit.int303.first.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DefaultEditorKit;
import sit.int303.first.model.SimpleCalculator;

/**
 *
 * @author INT303
 */
public class VerySimpleCalcutorServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            try {
                int x = Integer.parseInt(request.getParameter("x"));
                int y = Integer.parseInt(request.getParameter("y"));
                String operator = request.getParameter("operator");
                if (" ".equals(operator)) {     //ถ้าอ้างถึง op ขึ้นก่อนอาจเกิด null pointer
                    operator = "+";
                }
                //--------------------เรียก Object------------------------
                SimpleCalculator sc = new SimpleCalculator(); //Ctrl+Space 2 ครั้ง
                sc.setX(x);
                sc.setY(y);
                sc.setOperator(operator);
                
                //ในJSP ที่ตั้งชื่อ Calculator จะเชื่อมกับ sc
                request.setAttribute("calculator", sc);
                //JSP
                getServletContext().getRequestDispatcher("/SimpleCalulatorView.jsp").forward(request, response);
                
                /*------------------ย้ายไปแสดงผลที่ JSP---------------------//
//                int result=0;
//                switch(operator){
//                    case "+":   result=x+y;  break;
//                    case "-":   result=x-y;  break;
//                    case "*":   result=x*y;  break;
//                    case "/":   result=x/y;  break;
//                }

                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet VerySimpleCalcutorServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>VerySimpleCalculator</h1>");
                
                //--------------------เรียก Object------------------------
                //out.println(String.format("<h2>x = %d , y = %d <br> %d %s %d = %d</h2>", x,y,x,operator,y,result));
                out.println("<h2 style=color:blue>");
                out.println(sc.toString());
                out.println("</h2>");
                

                //out.println("<p>Checking = "+operator+"</p>");
                out.println("</body>");
                out.println("</html>");
                 */

            } catch (Exception e) {
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Servlet VerySimpleCalcutorServlet</title>");
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<h1>VerySimpleCalculator</h1>");
//                out.println("Please Try Again !");
//                out.println("</body>");
//                out.println("</html>");
            }

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

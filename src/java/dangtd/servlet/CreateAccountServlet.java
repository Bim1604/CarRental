/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.servlet;

import dangtd.carrentaldao.TblUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class CreateAccountServlet extends HttpServlet {

    private final String loginPage = "login";
    private final String createPage = "create";

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
        PrintWriter out = response.getWriter();
        String email = request.getParameter("txtEmail");
        String password = request.getParameter("txtPassword");
        String phone = request.getParameter("txtPhone");
        String name = request.getParameter("txtName");
        String address = request.getParameter("txtAddress");
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        String url = map.get(loginPage);;
        try {
            if (!email.isEmpty() && !password.isEmpty() && !phone.isEmpty() && !name.isEmpty() && !address.isEmpty()) {
                TblUserDAO userDAO = new TblUserDAO();
                boolean checkExist = userDAO.checkExistAccount(email);
                if (!checkExist) {
                    boolean rs = userDAO.createNewAccount(email, password, phone, name, address);
                    if (rs == true) {
                        String msg = "Create Successful";
                        request.setAttribute("CreateSuccess", msg);
                    } else {
                        url = map.get(createPage);
                        String msg = "Create Failded";
                        request.setAttribute("CreateFaild", msg);
                    }
                } else {
                    url = map.get(createPage);
                    String msg = "Existed Email !!";
                    request.setAttribute("CreateFaild", msg);
                }
            } else {
                url = map.get(createPage);
                String msg = "Please fill all information !!";
                request.setAttribute("CreateFaild", msg);
            }

        } catch (SQLException | NamingException ex) {
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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

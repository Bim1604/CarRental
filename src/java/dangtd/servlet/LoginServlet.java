/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.servlet;

import com.restfb.types.User;
import dangtd.carrentaldao.TblUserDAO;
import dangtd.loginfb.RestFB;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class LoginServlet extends HttpServlet {

    private final String loginPage = "login";
    private final String carPage = "";
    private final String activePage = "login";

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
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String code = request.getParameter("code");
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        HttpSession session = request.getSession(true);
        String url = map.get(loginPage);
        try {
            TblUserDAO dao = new TblUserDAO();
            if (code == null || code.isEmpty()) {
                if (username.isEmpty() || password.isEmpty()) {
                    String msg = "Please fill all information !!";
                    request.setAttribute("LOGINFAILED", msg);
                } else {
                    
                    String name = dao.checkLogin(username, password);
                    if (name != null) {
                        boolean statusAccount = dao.checkStatusAccount(username);
                        if (statusAccount) {
                            session.setAttribute("NAME", name);
                            url = map.get(carPage);
                        } else {
                            session.setAttribute("NAME", name);
                            url = map.get(activePage);
                        }
                    } else {
                        String msg = "Invalid password or username !!";
                        request.setAttribute("LOGINFAILED", msg);
                    }
                }
            } else {
                String accessToken = RestFB.getToken(code, "http://localhost:8084/CarRental/Login");
                User user = RestFB.getUserInfo(accessToken);
                username = user.getId();
                String fullname = user.getName();
                String result = dao.checkLogin(username);
                if (result != null) {
                    session.setAttribute("NAME", fullname);
                    boolean role = dao.getRole(username);
                    url = map.get(carPage);
                    session.setAttribute("ROLE", role);
                } else {
                    session.setAttribute("MSG", "Login facebook fail. Please try again.");
                }
            }

        } catch (SQLException | NamingException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
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

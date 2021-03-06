/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.servlet;

import com.restfb.types.User;
import dangtd.carrentaldao.TblUserDAO;
import dangtd.carrentaldto.TblUserDTO;
import dangtd.loginfb.RestFB;
import dangtd.verify.GmailSend;
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
public class CreateAccountServlet extends HttpServlet {

    private final String verifyPage = "verify";
    private final String createPage = "create";
    private final String loginPage = "login"; 
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
        String codeFB = request.getParameter("code");
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        String url = map.get(verifyPage);
        HttpSession session = request.getSession();
        try {
            if (codeFB == null || codeFB.isEmpty()) {
                String email = request.getParameter("txtEmail");
                String password = request.getParameter("txtPassword");
                String confirm = request.getParameter("txtConfirm");
                String phone = request.getParameter("txtPhone");
                String name = request.getParameter("txtName");
                String address = request.getParameter("txtAddress");
                if (!email.isEmpty() && !password.isEmpty() && !phone.isEmpty() && !name.isEmpty() && !address.isEmpty()) {
                    TblUserDAO userDAO = new TblUserDAO();
                    if (password.equals(confirm)) {
                        boolean checkExist = userDAO.checkExistAccount(email);
                        if (!checkExist) {
                            boolean rs = userDAO.createNewAccount(email, password, phone, name, address);
                            if (rs == true) {
                                GmailSend mail = new GmailSend();
                                String code = mail.sendEmail(email);
                                TblUserDTO dto = new TblUserDTO(email, password, phone, name, address, code);
                                session.setAttribute("VERIFYACCOUNT", dto);
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
                        String msg = "Password is not equal to confirm";
                        request.setAttribute("CreateFaild", msg);
                    }
                } else {
                    url = map.get(createPage);
                    String msg = "Please fill all information !!";
                    request.setAttribute("CreateFaild", msg);
                }
            } else {
                String accessToken = RestFB.getToken(codeFB, "http://localhost:8084/CarRental/Create");
                User user = RestFB.getUserInfo(accessToken);
                String username = user.getId();
                String fullname = user.getName();
                TblUserDAO dao = new TblUserDAO();
                try {
                    boolean rs = dao.createNewAccount(username, "", "", fullname, "");
                    if (rs) {
                        String msg = "Create Successful";
                        request.setAttribute("CreateSuccess", msg);
                    }
                } catch (SQLException ex) {
                    if (ex.getMessage().contains("duplicate")) {
                        url = map.get(loginPage);
                        request.setAttribute("MSG", "This Facebook account is duplicatied");
                    }
//                        LOGGER.error("CreateRecordServlet_SQLException: " + ex.getMessage());
                }
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

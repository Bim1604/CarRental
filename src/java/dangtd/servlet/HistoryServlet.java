/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.servlet;

import dangtd.carrentaldao.TblHistoryDAO;
import dangtd.carrentaldao.TblRentalDAO;
import dangtd.carrentaldto.TblHistoryDTO;
import dangtd.carrentaldto.TblRentalDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
public class HistoryServlet extends HttpServlet {
    private final String historyPage = "history";
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
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("EMAIL");
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        String url = map.get(historyPage);
        try {
            TblRentalDAO rentalDAO = new TblRentalDAO();
            TblHistoryDAO historyDAO = new TblHistoryDAO();
            historyDAO.getHistory(email);
            List<TblHistoryDTO> listHistory = historyDAO.getList();            
            Integer[] billID = new Integer[listHistory.size()];
            for (int i = 0; i < billID.length; i++) {
                billID[i] = listHistory.get(i).getBillID();
                
                rentalDAO.getRentalBill(billID[i]);
            }
            List<TblRentalDTO> listRental = rentalDAO.getList();
            for (TblRentalDTO tblRentalDTO : listRental) {
                System.out.println("ls: " + tblRentalDTO.getBillID());
            }
            session.setAttribute("HISTORYLIST", listHistory);
            session.setAttribute("RENTALLIST", listRental);
        } catch (SQLException ex) {
            Logger.getLogger(HistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(HistoryServlet.class.getName()).log(Level.SEVERE, null, ex);
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

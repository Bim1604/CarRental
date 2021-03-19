/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.servlet;

import dangtd.carrentaldao.TblCarDAO;
import dangtd.carrentaldao.TblDetailsRentDAO;
import dangtd.carrentaldao.TblGuestDAO;
import dangtd.carrentaldao.TblRentalDAO;
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
public class CartInfoServlet extends HttpServlet {
    private final String displayPage = "";
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
        String[] carID = request.getParameterValues("txtCarID");
        String[] txtAmount = request.getParameterValues("txtAmount");
        Integer[] amount = new Integer[txtAmount.length];
        String[] txtTotal = request.getParameterValues("txtTotal");
        Float[] total = new Float[txtTotal.length];
        String guestName = request.getParameter("txtGuestName");
        String phone = request.getParameter("txtGuestPhone");
        String address = request.getParameter("txtGuestAddress");
        String rentalDate = request.getParameter("txtRentalDate");
        String returnDate = request.getParameter("txtReturnDate");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("EMAIL");
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        String url = map.get(displayPage);
        try {
//            Thông tin khách hàng
            TblGuestDAO guestDAO = new TblGuestDAO();
            int guestID = guestDAO.getGuestID();
            boolean rsGuest = guestDAO.addGuestInfo(guestID, guestName, phone, address);
            if (rsGuest) {
//            bill thuê xe
                TblRentalDAO rentalDAO = new TblRentalDAO();
                int billID = rentalDAO.getBillID();
                boolean rsRental = rentalDAO.addRentalBill(billID, guestID, rentalDate, returnDate, guestID, email);
                if (rsRental) {
                    for (int i = 0; i < carID.length; i++) {
                        amount[i] = Integer.parseInt(txtAmount[i]);
                        total[i] = Float.parseFloat(txtTotal[i]);
                        TblDetailsRentDAO detailsDAO = new TblDetailsRentDAO();
                        TblCarDAO carDAO = new TblCarDAO();
                        float price = carDAO.getPriceCar(carID[i]);
                        boolean rsDetails = detailsDAO.addDetailsRent(billID, carID[i], amount[i], price , total[i]);
                        if (rsDetails){
                            String msg = "Rent Car Sucessfully";
                            request.setAttribute("CHECKOUTSUCCESS", msg);
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CartInfoServlet.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.servlet;

import dangtd.carrentaldao.TblCarDAO;
import dangtd.carrentaldao.TblCategoryDAO;
import dangtd.carrentaldto.TblCarDTO;
import dangtd.carrentaldto.TblCategoryDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class CarLoadServlet extends HttpServlet {

    private final String cateServlet = "search";

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
        ServletContext context = request.getServletContext();
        Map<String, String> map = (Map<String, String>) context.getAttribute("MAP");
        HttpSession session = request.getSession(true);
        int indexPage = 1;
        String url = map.get(cateServlet);
        if (request.getParameter("txtPageIndex") != null) {
            indexPage = Integer.parseInt(request.getParameter("txtPageIndex"));
        }
        int sizePage = 1;
        try {
//            load car
            TblCarDAO carDAO = new TblCarDAO();
            int total = carDAO.countTotalCar();
            int pageEnd = total / sizePage;
            if (total % sizePage != 0) {
                pageEnd++;
            }
            carDAO.loadCar(indexPage, sizePage);
            List<TblCarDTO> listCar = carDAO.getListCar();
//            Load Cate
            TblCategoryDAO cateDAO = new TblCategoryDAO();
            cateDAO.getCategory();
            List<TblCategoryDTO> list = cateDAO.getList();
            session.setAttribute("CATECAR", list);
            session.setAttribute("LISTCAR", listCar);
            session.setAttribute("CARPAGEEND", pageEnd);
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(CarLoadServlet.class.getName()).log(Level.SEVERE, null, ex);
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

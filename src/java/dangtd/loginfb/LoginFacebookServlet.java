/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.loginfb;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.restfb.types.User;

@WebServlet("/loginFB")
public class LoginFacebookServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginFacebookServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        System.out.println("code: " + code);
        System.out.println("hi"); 
        if (code == null || code.isEmpty()) {
            System.out.println("qua");
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = RestFB.getToken(code);
            User user = RestFB.getUserInfo(accessToken);
            request.setAttribute("id", user.getId());
            System.out.println("id: " + user.getId());
            request.setAttribute("name", user.getName());
            System.out.println("name: " + user.getName());
            RequestDispatcher dis = request.getRequestDispatcher("search.jsp");
            dis.forward(request, response);
        }
        System.out.println("toi");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}



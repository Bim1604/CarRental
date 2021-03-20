<%-- Document : login Created on : Mar 7, 2021, 7:10:05 PM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/C.css">
        <title>Login</title>
    </head>

    <body <c:if test="${not empty requestScope.LOGINFAILED}">
            onload="alertLoginFailedMessage()"
        </c:if>
        <c:if test="${not empty requestScope.MSG}">
            onload="alertCreateFBFailedMessage()"
        </c:if>
        >
        <div class="headerLogin">

        </div>
        <div class="loginBar">

        </div>
        <div class="loginBody">
            <h2>
                Login
            </h2>
            <form action="Login" method="POST">
                <div>
                    <input class="username" type="text" name="txtUsername" value="" placeholder="Username" />
                    <input type="password" name="txtPassword" value="" placeholder="Password" />
                    <button type="submit">Login</button>
                </div>
                <hr>
                <div>
                    <div>
                        <a
                            href="https://www.facebook.com/dialog/oauth?client_id=133206675390895&redirect_uri=http://localhost:8084/CarRental/Login">
                            Login Facebook
                        </a>
                    </div>
                    <div>
                        <a href="createAccount.jsp"> Create a new account</a>
                    </div>
                    <div>
                        <a
                            href="https://www.facebook.com/dialog/oauth?client_id=133206675390895&redirect_uri=http://localhost:8084/CarRental/Create">Create
                            Account with Facebook</a>
                    </div>
                </div>
            </form>
        </div>
        <div class="backLink">
            <a href="CarLoadServlet">back to Home Page</a>
        </div>
        <div class="footer">

        </div>
        <div class="sfooter">

        </div>
        <script>
            function alertLoginFailedMessage() {
                alert("${requestScope.LOGINFAILED}");
            }
            function alertCreateFBFailedMessage() {
                alert("${requestScope.MSG}");
            }
        </script>
    </body>

</html>
<%-- Document : login Created on : Mar 7, 2021, 7:10:05 PM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>

    <body         
        <c:if test="${not empty requestScope.LOGINFAILED}">
            onload="alertLoginFailedMessage()"
        </c:if>
        >
        <h1>
            Login
        </h1>
        <form action="Login" method="POST">
            <div>
                <input type="text" name="txtUsername" value="" placeholder="Username" />
            </div>
            <div>
                <input type="password" name="txtPassword" value="" placeholder="Password" />
            </div>
            <div>
                <input type="submit" value="Login" />
            </div>
            <div>
                <div>
                    <a href="createAccount.jsp"> Create a new account</a>
                </div>
                <div>
                    <a href="https://www.facebook.com/dialog/oauth?client_id=133206675390895&redirect_uri=http://localhost:8084/CarRental/Create">Create Account with Facebook</a>
                </div>                
            </div>
            <div>
                <a href="https://www.facebook.com/dialog/oauth?client_id=133206675390895&redirect_uri=http://localhost:8084/CarRental/Login">
                    Login Facebook
                </a>
            </div>
        </form>
        <script>
            function alertLoginFailedMessage() {
                alert("${requestScope.LOGINFAILED}");
            }
        </script>
    </body>
</html>
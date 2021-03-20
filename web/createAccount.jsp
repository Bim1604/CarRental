<%-- Document : createAccount Created on : Mar 10, 2021, 10:00:16 AM Author : Admin --%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/C.css">
        <title>Create Account</title>
    </head>

    <body <c:if test="${not empty requestScope.CreateFaild}">
            onload="alertCreateFailedMessage()"
        </c:if>>
        <div class="headerLogin">

        </div>
        <div class="loginBar">

        </div>
        <div class="createBody">
            <form action="Create" method="POST">
                <div>
                    <input type="text" value="" name="txtEmail" placeholder="Email">
                </div>
                <div>
                    <input type="password" value="" name="txtPassword" placeholder="Password">
                </div>
                <div>
                    <input type="password" value="" name="txtConfirm" placeholder="Confirm Password">
                </div>
                <div>
                    <input type="text" value="" name="txtPhone" placeholder="Phone">
                </div>
                <div>
                    <input type="text" value="" name="txtName" placeholder="Name">
                </div>
                <div>
                    <input type="text" value="" name="txtAddress" placeholder="Address">
                </div>
                <div>
                    Automatic time current
                </div>
                <div>
                    Default status of new User is New
                </div>
                <button type="submit">Create</button>
            </form>
        </div>
        <div class="backLink">
            <a href="CarLoadServlet">back to Home Page</a><br>
            <a href="login">back to Login</a>
        </div>
        <div class="footer">

        </div>
        <div class="sfooter">

        </div>
        <script>
            function alertCreateFailedMessage() {
                alert("${requestScope.CreateFaild}");
            }
        </script>
    </body>

</html>
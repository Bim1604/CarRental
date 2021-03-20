<%-- 
    Document   : verifyPage
    Created on : Mar 14, 2021, 11:10:10 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/C.css">
        <title>Verify Page</title>
    </head>
    <body <c:if test="${not empty VERIFYFAILED}">
            onload="alertVerifyMessage()"
        </c:if>>
        <div class="headerLogin">

        </div>
        <div class="loginBar">

        </div>
        <div class="verifyBody">
            <form action="Verify" method="POST">
                <div>
                    Input verify 's code 
                    <input type="text" name="txtCode" value="" />                    
                    <button type="submit">Send</button>
                </div>
            </form>                
        </div>
        <div class="footer">

        </div>
        <div class="sfooter">

        </div>
    </body>
    <script>
        function alertVerifyMessage() {
            alert("${requestScope.VERIFYFAILED}");
        }
    </script>
</html>

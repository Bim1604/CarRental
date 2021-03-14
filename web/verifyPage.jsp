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
        <title>Verify Page</title>
    </head>
    <body <c:if test="${not empty VERIFYFAILED}">
            onload="alertVerifyMessage()"
        </c:if>>
        <div>
            <div>
                <form action="Verify" method="POST">
                    Input verify 's code 
                    <input type="text" name="txtCode" value="" />
                    <div>
                        <input type="submit" value="Send" />
                    </div>
                </form>                
            </div>
        </div>
    </body>
    <script>
        function alertVerifyMessage() {
                alert("${requestScope.VERIFYFAILED}");
            }
    </script>
</html>

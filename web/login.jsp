<%-- 
    Document   : login
    Created on : Mar 7, 2021, 7:10:05 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>
            Login
        </h1>
        <form action="Login">
            <input type="text" name="txtUsername" value="" placeholder="Username"/>
            <input type="password" name="txtPassword" value="" placeholder="Password"/>
            <input type="submit" value="Login" />
        </form>        
    </body>
</html>

<%-- 
    Document   : search
    Created on : Mar 7, 2021, 11:25:20 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/main.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <title>Car Rental</title>
    </head>
    <body>
        <!-- header -->
        <div class="header">
            <ul>
                <li>
                    Car Rent
                </li>
                <li>
                    <a href="">Find Car</a>                    
                </li>
                <li>
                    <a href="facebook.com"><i class="fa fa-facebook-square" aria-hidden="true">FaceBook</i></a> 
                </li>
                <li>
                    <i class="fa fa-phone" aria-hidden="true"> 0852573133</i> 
                </li>
                <li>
                    <a href="gmail.com"><i class="fa fa-envelope" aria-hidden="true"> dangvipro3@gmail.com</i></a>
                </li>
            </ul>
            <ul>
                <c:if test="${not empty requestScope.NAME}">
                    <li>
                        <font>
                        Welcome, ${requestScope.NAME}
                        </font>
                    </li>
                    <li>
                        <form action="Log">
                            <input type="submit" value="Logout" />
                        </form>                        
                    </li>

                </c:if>
                <c:if test="${empty requestScope.NAME}">
                    <li>
                        <form action="login">
                            <input type="submit" value="Login" />
                        </form>  
                    </li>
                </c:if>                 
            </ul>
        </div>
        <!-- Load Car Data -->
        <c:set var="result" value="${sessionScope.LISTCAR}" />
        <form action="">
            <c:if test="${not empty result}">
                <div class="searchBody">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>No.</th>
                                <th>Car Name</th>
                                <th>Color</th>
                                <th>Year</th>
                                <th>Category</th>
                                <th>Price</th>
                                <th>Quantity</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${result}" varStatus="counter">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${dto.carName}
                                    </td>
                                    <td>
                                        ${dto.color}
                                    </td>
                                    <td>
                                        ${dto.year}
                                    </td>
                                    <td>
                                        ${dto.category}
                                    </td>
                                    <td>
                                        ${dto.price}
                                    </td>
                                    <td>
                                        ${dto.quantity}
                                    </td>
                                </tr>
                            </c:forEach>
                        <td>
                            <c:forEach begin="1" end="${sessionScope.CARPAGEEND}" var="indexPage">
                                <div>
                                    <input type="submit" value="${indexPage}" name="txtPageIndex" />
                                </div>
                            </c:forEach>
                        </td>
                        </tbody>
                    </table>    
                </div>            
            </c:if>
        </form>
        <!-- Load Data Search -->
        
    </body>
</html>

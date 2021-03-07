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
        <title>Car Rental</title>
    </head>
    <body>
        <div class="header">
            <ul>
                <li>
                    Car Rent
                </li>
                <li>
                    Find Car
                </li>
                <li>
                    FaceBook
                </li>
                <li>
                    0852573133
                </li>
                <li>
                    dangvipro3@gmail.com
                </li>
            </ul>
            <ul>
                <li>
                    Login
                </li>
            </ul>
        </div>
        <c:set var="result" value="${sessionScope.LISTCAR}" />
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
                    </tbody>
                </table>    
            </div>
            
        </c:if>
    </body>
</html>

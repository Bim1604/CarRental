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
        <title>Car Rental</title>
    </head>
    <body>
        <c:set var="result" value="${sessionScope.LISTCAR}" />
        <c:if test="${not empty result}">
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

        </c:if>
    </body>
</html>

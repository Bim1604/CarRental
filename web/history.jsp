<%-- 
    Document   : history
    Created on : Mar 20, 2021, 3:43:23 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/C.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <title>History Page</title>
    </head>
    <body>
        <div class="header">
            <ul>
                <li>
                    <a href="CarLoadServlet">Car Rent</a> 
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
                <c:if test="${not empty sessionScope.NAME}">
                    <li>
                        <font>
                        Welcome, ${sessionScope.NAME}
                        </font>
                    </li>
                    <li>
                        <a href="CartServlet">View Cart</a>
                    </li>
                    <li>
                        <form action="Log">
                            <input type="submit" value="Logout" />
                        </form>                        
                    </li>

                </c:if>
                <c:if test="${empty sessionScope.NAME}">
                    <li>
                        <form action="login">
                            <input type="submit" value="Login" />
                        </form>  
                    </li>
                </c:if>                 
            </ul>
        </div>
        <div class="searchBody">
            <form class="searchBar" action="Search">
                <ul>
                    <li>
                        <input type="text" name="txtSearchValue" value="" placeholder="search car name" />
                        <input type="submit" value="Search" />
                    </li>
                    <li>
                        <select name="txtCate">
                            <option label="All" value="" />
                            <c:forEach var="cate" items="${sessionScope.CATECAR}">
                                <option label="${cate.categoryName}" value="${cate.categoryID}" />
                            </c:forEach>
                        </select>
                    </li>
                </ul>
            </form>
        </div>
        <!-- Load List History -->
        <c:set var="history" value="${sessionScope.HISTORYLIST}" />
        <c:if test="${not empty history}">
            <table border="1">
                <thead>
                    <tr>
                        <th>
                            Bill ID
                        </th>
                        <th>
                            Action
                        </th>
                        <th>
                            Status
                        </th>
                        <th>
                            User ID 
                        </th>
                        <th>
                            Rental Date
                        </th>
                        <th>
                            Return Date
                        </th>
                        <th>
                            Price Total 
                        </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${history}">
                        <c:forEach var="rentalDTO" items="${sessionScope.RENTALLIST}">
                            <c:if test="${dto.billID eq rentalDTO.billID}">
                                <tr>
                                    <td>
                                        ${dto.billID}
                                    </td>
                                    <td>
                                        ${dto.action}
                                    </td>
                                    <td>
                                        ${dto.status}
                                    </td>
                                    <td>
                                        ${dto.email}
                                    </td>
                                    <td>
                                        ${rentalDTO.rentalDate}
                                    </td>
                                    <td>
                                        ${rentalDTO.returnDate}
                                    </td>
                                    <td>
                                        ${rentalDTO.priceTotal}
                                    </td>
                                </tr>
                            </c:if>                            
                        </c:forEach>                        
                    </c:forEach>

                </tbody>
            </table>

        </c:if>
    </body>
</html>

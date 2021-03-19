<%-- 
    Document   : detailsCar
    Created on : Mar 17, 2021, 7:03:50 PM
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
        <title>Details Car</title>
    </head>
    <body>
        <!-- header -->
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
            <form action="Search">
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
        <!--Load details car-->
        <div>
            <table border="1">
                <c:forEach var="dto" items="${requestScope.DETAILSCAR}">
                    <tbody>
                    <form action="Add">
                        <tr>
                            <td>
                                <img src="image/${dto.img}" /> 
                            </td>
                            <td>
                                <div>
                                    Car Name: ${dto.carName}
                                </div>
                                <div>
                                    Car Color: ${dto.color}
                                </div>
                                <div>
                                    Car year: ${dto.year}
                                </div>
                                <div>
                                    Car category: ${dto.category}
                                </div>
                                <div>
                                    Price: ${dto.price}$
                                </div>
                                <div>
                                    Quantity: ${dto.quantity}
                                </div>                        
                                <div>
                                    <input type="hidden" name="pk" value="${dto.carID}" />
                                    <input type="submit" value="Rent" />
                                    <a href="CartServlet">View Cart</a>
                                </div>
                            </td>
                        </tr>
                    </form>
                    </tbody>
                </c:forEach>
            </table>
        </div>
        <div>
            <a href="CarLoadServlet">Return Car Display</a>
        </div>
    </body>
</html>

<%-- 
    Document   : display
    Created on : Mar 16, 2021, 4:55:43 PM
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
        <title>Car display</title>
    </head>
    <body <c:if test="${not empty requestScope.CHECKOUTSUCCESS}">
            onload="getCheckOutMsg()"
        </c:if>>
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
        <!-- Load Car Data -->
        <c:set var="result" value="${sessionScope.LISTCAR}" />
        <c:if test="${not empty result}">
            <form action="Add">
                <table class="listCar">
                    <c:forEach var="dto" items="${result}">
                        <tbody>
                        <td>  
                            <c:url var="urlRewriting" value="DetailsCarServlet">
                                <c:param name="pk" value="${dto.carID}" />
                            </c:url>
                            <a href="${urlRewriting}"><img src="image/${dto.img}"></a>
                            <div>
                                ${dto.carName}(${dto.year}): ${dto.price}$
                            </div>
                            <div>
                                <input type="hidden" name="pk" value="${dto.carID}" />
                                <input type="submit" value="Rent" />
                            </div>
                        </td>                                                                  
                        </tbody>
                    </c:forEach>  
                </table>    
            </form> 
            <div class="pageIndex">
                <c:forEach begin="1" end="${sessionScope.CARPAGEEND}" var="indexPage">
                    <div>
                        <a href="CarLoadServlet?txtPageIndex=${indexPage}">${indexPage}</a>
                    </div>
                </c:forEach>
            </div>       
        </c:if>  
        <script>
            function getCheckOutMsg() {
                alert("${requestScope.CHECKOUTSUCCESS}");
            }
        </script>
    </body>
</html>

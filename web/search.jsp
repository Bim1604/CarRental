<%-- Document : search Created on : Mar 7, 2021, 11:25:20 PM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="CSS/C.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <title>Car Rental</title>
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
                    <a href="gmail.com"><i class="fa fa-envelope" aria-hidden="true">
                            dangvipro3@gmail.com</i></a>
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
                        <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" placeholder="search car name" />
                        <input type="submit" value="Search" />
                    </li>
                    <li>
                        <select name="txtCate">
                            <option label="All" value="" />
                            <c:forEach var="cate" items="${sessionScope.CATECAR}">
                                <option label="${cate.categoryName}" value="${cate.categoryID}" <c:if test="${cate.categoryID eq param.txtCate}">
                                        selected="selected"
                                    </c:if> />
                            </c:forEach>
                        </select>
                    </li>
                </ul>
            </form>
        </div>
        <!-- Load Data Search -->
        <div class="searchBody">
            <c:if test="${not empty requestScope.LISTCARSEARCH}">
                <table border="1">
                    <tbody>
                    <form action="Add">
                        <c:forEach var="dto" items="${requestScope.LISTCARSEARCH}">
                            <tr>
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
                            </tr>
                        </c:forEach>
                    </form>
                    </tbody>
                </table>
            </c:if>
        </div>
        <div>
            <c:forEach begin="1" end="${requestScope.ENDPAGE}" var="indexPage">
                <div>
                    <a href="Search?txtSearchValue=${param.txtSearchValue}&txtCate=${param.txtCate}&txtPageIndex=${indexPage}">${indexPage}</a>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
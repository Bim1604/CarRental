<%-- Document : viewCart Created on : Mar 18, 2021, 1:43:22 PM Author : Admin --%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <link rel="stylesheet" href="CSS/C.css">
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
        <title>Cart Page</title>
    </head>

    <body onload="return checkNumberOfCar()">
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
        <!--Load cart-->
        <div >
            <c:set var="cart" value="${sessionScope.CUSCART}" />
            <c:if test="${not empty cart}">
                <form action="CartInfo" onsubmit="return check()" method="POST">
                    <!--Cart-->
                    <div class="cartBody">
                        <table id="CarTable" border="1">
                            <thead>
                                <tr>
                                    <th>Car Name</th>
                                    <th>Car Type</th>
                                    <th>Amount</th>
                                    <th>Price</th>
                                    <th>Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="dto" items="${sessionScope.CarCART}">
                                    <c:forEach var="entry" items="${cart.items}" varStatus="counter">
                                        <c:if test="${entry.key eq dto.carID}">
                                        <input id="carID" type="hidden" name="txtCarID" value="${dto.carID}" />
                                        <tr>
                                            <td>
                                                ${dto.carName}
                                            </td>
                                            <td>
                                                ${dto.category}
                                            </td>
                                            <td>
                                                <span class="increase" onclick="return increase('${dto.carID}')">+</span>
                                                <span class="amount" id="amount${dto.carID}">${entry.value}</span>
                                                <span class="decrease" onclick="return decrease('${dto.carID}')">-</span>
                                                <input id="amount2${dto.carID}" type="hidden" name="txtAmount"
                                                       value="${entry.value}" />
                                            </td>
                                            <td>
                                                ${dto.price}
                                                <input id="price${dto.carID}" type="hidden"
                                                       value="${dto.price}">
                                            </td>
                                            <td>
                                                <span id="total${dto.carID}">${dto.price * entry.value}</span>
                                                <input id="total2${dto.carID}" type="hidden" name="txtTotal"
                                                       value="${dto.price * entry.value}">
                                            </td>
                                            <td>
                                                <c:url var="urlRewriting" value="CartRemove">
                                                    <c:param name="ckbRemove" value="${entry.key}" />
                                                </c:url>
                                                <a href="${urlRewriting}"
                                                   onclick="return confirmRemove('${dto.carName}')">Remove</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>
                        <!--Details guest-->
                        <c:if test="${not empty sessionScope.CUSCART}">
                            <div id="GuestTable">
                                <table border="1">
                                    <tbody>
                                        <tr>
                                            <td>
                                                Guest Name* <input id="guestName" type="text" name="txtGuestName"
                                                                   value="${param.txtGuestName}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Phone* <input id="phone" type="text" name="txtGuestPhone"
                                                              value="${param.txtGuestPhone}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Address* <input id="address" type="text" name="txtGuestAddress"
                                                                value="${param.txtGuestAddress}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Rental Date* <input id="rentalDate" type="date" name="txtRentalDate"
                                                                    value="${param.txtRentalDate}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Return Date* <input id="returnDate" type="date" name="txtReturnDate"
                                                                    value="${param.txtReturnDate}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                Discount <input id="discount" type="text" name="txtDiscount" value="${param.txtDiscount}" />
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                                <div>
                                    <!-- <input id="Confirm" type="submit" value="Confirm"/> -->
                                    <button id="Confirm" type="submit" value="Rent" name="btAction">Confirm</button>
                                </div>
                            </div>
                        </c:if>
                    </div>                   
                </form>
            </c:if>
        </div>
        <c:if test="${not empty requestScope.MSG}">
            <c:forEach var="msg" items="${requestScope.MSG}">
                <font color="red">
                <div>${msg}</div>
                </font>
            </c:forEach>
        </c:if>
        <div class="backViewLink">
            <a href="CarLoadServlet">Return Car Display</a>
        </div>
        <div class="footer">

        </div>
        <div class="sfooter">

        </div>
        <script>
            function checkNumberOfCar() {
                if (document.getElementById("carID") === null) {
                    document.getElementById("GuestTable").style.display = 'none';
                    document.getElementById("CarTable").style.display = 'none';
                }
            }
            function check() {
                var guestName = document.getElementById("guestName").value;
                var phone = document.getElementById("phone").value;
                var address = document.getElementById("address").value;
                var rentalDate = document.getElementById("rentalDate").value;
                var returnDate = document.getElementById("returnDate").value;
                if (guestName === "") {
                    alert("Please fill Guest Name");
                    return false;
                }
                if (phone === "") {
                    alert("Please fill Phone");
                    return false;
                }
                if (address === "") {
                    alert("Please fill Address");
                    return false;
                }
                if (rentalDate === "") {
                    alert("Please choose Rental Date");
                    return false;
                }
                if (returnDate === "") {
                    alert("Please choose Return Date");
                    return false;
                }
            }
            function increase(id) {
                var amount = parseInt(document.getElementById("amount" + id).innerHTML);
                var price = parseInt(document.getElementById("price" + id).value);
                amount++;
                var total = price * amount;
                document.getElementById("amount" + id).innerHTML = amount;
                document.getElementById("total" + id).innerHTML = total;
                document.getElementById("amount2" + id).value = amount;
                document.getElementById("total2" + id).value = total;
            }
            function decrease(id) {
                var amount = parseInt(document.getElementById("amount" + id).innerHTML);
                var price = parseInt(document.getElementById("price" + id).value);
                amount--;
                var total = price * amount;
                if (amount === 0) {
                    return;
                }
                document.getElementById("amount" + id).innerHTML = amount;
                document.getElementById("total" + id).innerHTML = total;
                document.getElementById("amount2" + id).value = amount;
                document.getElementById("total2" + id).value = total;
            }
            function confirmRemove(carName) {
                var rs = confirm("Are you want to Remove " + carName + " from the cart");
                if (rs) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
    </body>

</html>
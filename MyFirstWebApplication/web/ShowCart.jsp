<%-- 
    Document   : ShowCart
    Created on : Aug 9, 2018, 4:04:13 PM
    Author     : INT303
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    </head>
    <body>
        <style>
            body{
                background-color: #FFFDFA;
            }
        </style>
        <nav class="navbar navbar-inverse">
             <jsp:include page="include/Header.jsp"/>
        </nav>
        <div class="container">
            <div class="container" align="center" style="padding: 20px;">
                <h1>--Show Cart--</h1>
            </div>
            <table id="example" class="table">
                <thead>
                <th>No</th>
                <th>Product Code</th>
                <th>Product name</th>
                <th>Unit Price</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Remove</th>
                </thead>
                
                <!--use c:set-->
                <c:set var="items" value="${sessionScope.cart.lineItems}"/>
                <c:set var="colorX" value="DodgerBlue"/>
                <c:set var="colorY" value="SlateBlue"/>
                
                <!-- Loop -->
                <!--get list line items from cart-->
                <c:forEach items="${cart.lineItems}" var="lineItem" varStatus="vs">
                    <tr style="color: ${vs.count%2==1?colorX:colorY}">
                        <td>${vs.count}</td>
                        <td>${lineItem.product.productCode}</td>
                        <td>${lineItem.product.productName}</td>
                        <td>${lineItem.product.msrp}</td>
                        <td>${lineItem.quantity}</td>
                        <td>${lineItem.totalPrice}</td>
                        <td><a href="RemoveItemFromCart?productCode=${lineItem.product.productCode}">
                                <span class="glyphicon glyphicon-remove"></span>
                            </a></td>
                    </tr>
                </c:forEach>
            </table>
            <div>
                <h2>Total Price = <fmt:formatNumber value="${cart.totalPrice}" pattern="#,###.00"/></h2>
            </div>
        </div>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        <div id="buttonFooter" class="container" align="center">
            <a href="ProductList"><button class="btn btn-info">Back to Shopping</button></a>
        </div>
    </body>
</html>

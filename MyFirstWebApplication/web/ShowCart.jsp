<%-- 
    Document   : ShowCart
    Created on : Aug 9, 2018, 4:04:13 PM
    Author     : INT303
--%>

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
            #buttonFooter{
                padding: 25px;
            }
        </style>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">INT303 WebProgramming</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="/MyFirst/index.html">Home</a></li>
                    <li><a href="PrimeNumber">1.Prime Number</a></li>
                    <li><a href="/MyFirst/CalculatorForm.html">2.Simple Calculator</a></li>
                    <li><a href="TestRequestParam">3.TestRequestParam</a></li>
                    <li class="active"><a href="ProductList">4.Our Product</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="ShowCart"><span class="glyphicon glyphicon-shopping-cart"></span> CART (${cart.totalQuantity})  </a></li>
                </ul>
            </div>
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
                <!-- Loop -->
                <!--get list line items from cart-->
                <c:forEach items="${cart.lineItems}" var="lineItem" varStatus="vs">
                    <tr>
                        <td>${vs.count}</td>
                        <td>${lineItem.product.productCode}</td>
                        <td>${lineItem.product.productName}</td>
                        <td>${lineItem.product.msrp}</td>
                        <td>${lineItem.quantity}</td>
                        <td>${lineItem.totalPrice}</td>
                        <td><a href="RemoveItemFromCart?productCode=${lineItem.product.productCode}">
                                <input type="button" value="Remove from cart"/>
                            </a></td>
                    </tr>
                </c:forEach>
            </table>
            <div>
                <h2>Total Price = ${cart.totalPrice}</h2>
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

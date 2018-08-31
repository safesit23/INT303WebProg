<%-- 
    Document   : ProductList
    Created on : Aug 8, 2018, 3:23:03 PM
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
             <jsp:include page="include/Header.jsp"/>
        </nav>
        <div class="container">
            <div class="container" align="center" style="padding: 20px;">
                <h1>--Product List--</h1>
            </div>
            <div>
                <h5>Total Price = ${cart.totalPrice}</h5>
            </div>
            <table id="example" class="table">
                <thead>
                <th>Image</th>
                <th>No</th>
                <th>Product Code</th>
                <th>Product name</th>
                <th>Product Line</th>
                <th>Scale</th>
                <th>Price</th>
                <th>Add to Cart</th>
                </thead>
                <!-- Loop -->
                <c:forEach items="${products}" var="p" varStatus="vs">
                    <tr>
                        <td><img src="model-images/${p.productCode}.jpg" width="120"</td>
                        <td>${vs.count}</td>
                        <td><a href="GetProduct?productcode=${p.productCode}">${p.productCode}</a></td>
                        <td>${p.productName}</td>
                        <td>${p.productLine}</td>
                        <td>${p.productScale}</td>
                        <td>${p.msrp}</td>
                        <!--sent query string-->
                        <td><a href="AddItemToCart?productCode=${p.productCode}">
                                <span class="glyphicon glyphicon-plus"></span>
<!--                                <input type="button" value="Add To Cart"/>-->
                            </a>
                            <!--UsePOSTMethod-->
<!--                            <form action="AddItemToCart" method="post">
                                <input type="hidden" value="${p.productCode}" name="productCode"/>
                                <input type="submit" value="Add To Cart(POST)"/>
                            </form>
-->
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        <div id="buttonFooter" class="container" align="center">
            <a href="/MyFirst/index.html"><button class="btn btn-info">Back to menu</button></a>
        </div>
    </body>
</html>

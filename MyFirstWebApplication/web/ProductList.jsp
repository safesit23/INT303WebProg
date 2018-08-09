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
        <div>
            <h1>Product List::</h1>
            <table id="example" class="table">
                <thead>
                <th>Image</th>
                <th>No</th>
                <th>Product Code</th>
                <th>Product name</th>
                <th>Product Line</th>
                <th>Scale</th>
                <th>Price</th>
                </thead>
                <!-- Loop -->
                <c:forEach items="${products}" var="p" varStatus="vs">
                    <tr>
                        <td><img src="model-images/${p.productCode}.jpg" width="120"</td>
                        <td>${vs.count}</td>
                        <td>${p.productCode}</td>
                        <td>${p.productName}</td>
                        <td>${p.productLine}</td>
                        <td>${p.productScale}</td>
                        <td>${p.msrp}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <script>
            $(document).ready(function () {
                $('#example').DataTable();
            });
        </script>
        <a href="/MyFirst/index.html"><button>Back to menu</button></a>
    </body>
</html>

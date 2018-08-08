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
    </head>
    <body>
        <h1>Product List::</h1>
        <table>
            <thead>
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
                <td>${vs.count}</td>
                <td>${p.productCode}</td>
                <td>${p.productName}</td>
                <td>${p.productLine}</td>
                <td>${p.productScale}</td>
                <td>${p.msrp}</td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>

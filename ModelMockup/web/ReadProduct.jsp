<%-- 
    Document   : ReadProduct
    Created on : Aug 6, 2018, 9:06:31 PM
    Author     : Khaitong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="sit.int303.mockup.model.ProductMockup"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            //String x = ReadProduct.getProduct();
            String path = application.getRealPath("/").toString() + "\\WEB-INF\\products.txt";
            ProductMockup.setFileLocation(path);
            pageContext.setAttribute("x", ProductMockup.getProducts());
        %>
        <table>
            <thead>
            <th>#</th>
            <th>Code</th>
            <th>Name</th>
            <th>Scale</th>
            <th>Vendor</th>
            <th>Price</th>
        </thead>
        <c:forEach items = "${x}" var="product" varStatus="vs" >
            <tr>
                <td>${vs.count}</td>
                <td>${product.productCode}</td>
                <td>${product.productName}</td>
                <td>${product.productLine}</td>
                <td>${product.productVendor}</td>
                <td>${product.msrp}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>

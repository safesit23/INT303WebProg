<%-- 
    Document   : Header
    Created on : Aug 10, 2018, 3:12:11 PM
    Author     : INT303
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container-fluid" style="background-color: ${cookie.bgColor.value}">
    <div class="navbar-header">
        <a class="navbar-brand">INT303 WebProgramming</a>
    </div>
    <ul class="nav navbar-nav">
        <li class="active"><a href="/MyFirst/index.html">Home</a></li>
        <li><a href="PrimeNumber">1.Prime Number</a></li>
        <li><a href="/MyFirst/CalculatorForm.html">2.Simple Calculator</a></li>
        <li><a href="TestRequestParam">3.TestRequestParam</a></li>
        <li><a href="ProductList">4.Our Product</a></li>
        <li><a href="ChooseBackground">5. Choose Background</a></li>
    </ul>
    <ul class="nav navbar-nav navbar-right">
        <c:choose>
            <c:when test="${sessionScope.user!=null}">
                <li><a href=#><span class="glyphicon glyphicon-user"> Hello ${sessionScope.user.name}</span></a></li>
                </c:when>
                <c:otherwise><li><a href=#><span class="glyphicon glyphicon-user"> HelloGuest</span></a></li></c:otherwise>
            </c:choose>
            <c:if test="${cart!=null}">
            <li><a href="ShowCart"><span class="glyphicon glyphicon-shopping-cart"></span> CART (${cart.totalQuantity})</a></li>
            </c:if>
    </ul>
</div>
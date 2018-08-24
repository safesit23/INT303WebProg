<%-- 
    Document   : Header
    Created on : Aug 10, 2018, 3:12:11 PM
    Author     : INT303
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:choose>
    <c:when test="${sessionScope.user!=null}">
        <li><a href=#><span class="glyphicon glyphicon-user"> Hello ${sessionScope.user.name}</span></a></li>
    </c:when>
    <c:otherwise><li><a href=#><span class="glyphicon glyphicon-user"> HelloGuest</span></a></li></c:otherwise>
</c:choose>
    <c:if test="${cart!=null}">
        <li><a href="ShowCart"><span class="glyphicon glyphicon-shopping-cart"></span> CART (${cart.totalQuantity})</a></li>
    </c:if>

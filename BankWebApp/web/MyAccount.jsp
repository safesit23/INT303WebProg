<%-- 
    Document   : MyAccount
    Created on : Sep 27, 2018, 4:28:11 PM
    Author     : jatawatsafe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank</title>
    </head>
    <body>
        <h1>MyAccount</h1>
        <c:if test="${message !=null}">
        <div>${message}</div>
        </c:if>
        <h2>${account.name}</h2>
        <h2>${account.balance}</h2>
        <br>
        <a href="Deposit">Deposit</a><br>
        <a href="Withdraw">Withdraw</a><br>
        <a href="History">History</a><br>
        <br>
        <a href="Logout">Logout</a>
    </body>
</html>

<%-- 
    Document   : History
    Created on : Sep 28, 2018, 1:01:47 AM
    Author     : jatawatsafe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>BankApp</title>
    </head>
    <body>
        <h1>History</h1>
        <br>
        <table>
            <tr>
                <td>#</td>
                <td>Method</td>
                <td>Amount</td>
                <td>Time</td>
                <td>Balance</td>
            </tr>
            <c:forEach items="${listHistory}" var="history" varStatus="vs">
                <tr>
                    <td>${vs.count}</td>
                    <td>${history.method}</td>
                    <td>${history.amount}</td>
                    <td>${history.time}</td>
                    <td>${history.balance}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <a href="MyAccount.jsp">Back</a>
    </body>
</html>

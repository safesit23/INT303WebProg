<%-- 
    Document   : Login
    Created on : Sep 27, 2018, 4:40:16 PM
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
        <h1>Login</h1>
        <form action="Login" method="post">
            Account Id: <input type="number" name="id" required><br>
            Pin: <input type="number" name="pin" required><br>
            <input type="submit" value="submit">
        </form>
        <c:if test="${loginmessage!=null}">
            <p style="color: red">${loginmessage}</p>
        </c:if>
    </body>
</html>

<%-- 
    Document   : Login
    Created on : Sep 20, 2018, 4:11:53 PM
    Author     : jatawatsafe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    </head>
    <style>
            body{
                font-family: sans-serif;
                font-size: 2em;
            }
        </style>
        <div class="container" style="text-align: center">
            <h1>Login</h1>
            <hr>
            <form action="Login" method="post">
                Id: <input type="number" name="id" required>
                Password: <input type="password" name="password" required>
                <input type="submit" value="Login">
            </form>
            <br>
            <c:if test="${message!=null}">
                <div class="alert alert-danger" role="alert">${message}</div>
            </c:if>
            
        </div>
</html>

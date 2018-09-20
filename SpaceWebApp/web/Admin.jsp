<%-- 
    Document   : Admin
    Created on : Sep 20, 2018, 7:12:25 PM
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
    <body>
        <style>
            body{
                font-family: sans-serif;
                font-size: 2em;
            }
        </style>
        <div class="container" style="text-align: center">
            <h1>ADMIN PAGE</h1>
            <hr>
            <table>
                <tr>
                    <td>no.</td>
                    <td>id</td>
                    <td>name</td>
                    <td>email</td>
                    <td>password</td>
                </tr>
                <c:forEach items="${astList}" var="ast" varStatus="vs">
                    <tr>
                        <td>${vs.count}</td>
                        <td>${ast.id}</td>
                        <td>${ast.name}</td>
                        <td>${ast.email}</td>
                        <td>${ast.password}</td>
                    </tr>
                </c:forEach>
            </table>
            <br>
            <a href="/Space/index.html">Home Page</a>
            <a href="WeightConverter">Program</a>
        </div>
    </body>
</html>

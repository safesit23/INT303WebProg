<%-- 
    Document   : EditProfile
    Created on : Sep 20, 2018, 6:20:04 PM
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
            <h1>EDIT PROFILE</h1>
            <hr>
            <form action="EditProfile" method="post">
                Name: <input type="text" name="name" value="${astronomer.name}" required><br>
                Email: <input type="email" name="email" value="${astronomer.email}" required><br>
                <br>
                Confirm Password: <input type="password" name="password" required><br>
                <input type="submit" value="EDIT DATA">
            </form>
            <c:if test="${updateProfile!=null}">
                <c:choose>
                    <c:when test="${updateProfile==false}">
                        <div class="alert alert-danger" role="alert">Try again!</div>
                    </c:when>
                    <c:otherwise>
                        <div class="alert alert-success" role="alert">Edit Success</div>
                    </c:otherwise>
                </c:choose>
            </c:if>
            <br>
            <a href="/Space/index.html">Home Page</a>
            <a href="WeightConverter">Program</a>
        </div>
    </body>
</html>

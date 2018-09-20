<%-- 
    Document   : Register
    Created on : Sep 20, 2018, 5:30:48 PM
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
            <h1>REGISTER</h1>
            <hr>
            <form action="Register" method="post">
                Name: <input type="text" name="name" required><br>
                Email: <input type="email" name="email" required><br>
                Password: <input type="password" name="password" required><br>
                <input type="submit" value="Register">
            </form>
            <br>
            <div>
                <c:if test="${valueOfRegister!=null}">
                    <c:choose>
                        <c:when test="${valueOfRegister==0}">
                            <div class="alert alert-danger" role="alert">Not success</div>
                        </c:when>
                        <c:otherwise>
                            <div class="alert alert-success" role="alert">Register Success, your id is ${valueOfRegister}</div>
                        </c:otherwise>
                    </c:choose>
                </c:if>
            </div>
            <a href="/Space/index.html">Home Page</a>
        </div>
    </body>
</html>

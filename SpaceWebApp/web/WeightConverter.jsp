<%-- 
    Document   : WeightConverter
    Created on : Sep 20, 2018, 4:50:39 PM
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
        <h1>Weight Converter</h1>
        <hr>
        <h3>
            Welcome ${astronomer.name} | 
            <a href="Logout">Logout</a>
            <a href="Edit">Edit</a>
        </h3>
        <br>
        <form action="WeightConverter" method="post">
            Weight: <input type="number" name="weight" required><br>
            Planet: <select name="planet" required>
                <option value="1">x1</option>
                <option value="2">x2</option>
                <option value="3">x3</option>
            </select>
            <br><br>
            <input type="submit" value="CONVERT">
        </form>
        <br>
        <hr>
        <c:if test="${newWeight!=null}">
            <div>
                Result : ${newWeight}
            </div>
        </c:if>
    </div>
</html>

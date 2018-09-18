<%-- 
    Document   : Task
    Created on : Sep 18, 2018, 4:11:44 PM
    Author     : jatawatsafe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TASK</title>
    </head>
    <body>
        <style>
            body{
                padding: 10px;
                font-size: 1.2em;
            }
        </style>
        <h1>User: ${account.username}</h1>
        <form action="Task" method="post">
            New Task: <input type="text" name="todolist" required>
            <input type="submit" value="Add">
        </form>
        <table>
            <tr>
                <td>Date</td>
                <td>Task</td>
                <td>Status</td>
            </tr>
            <c:forEach items="${taskList}" var="task">
            <tr>
                <td>${task.createdate}</td>
                <td>${task.task}</td>
                <td>${task.status}</td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>

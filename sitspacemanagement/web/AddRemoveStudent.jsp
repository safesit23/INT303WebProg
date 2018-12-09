<%-- 
    Document   : AddRemoveStudent
    Created on : Dec 9, 2018, 12:07:26 PM
    Author     : jatawatsafe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD/REMOVE Student</title>
    </head>
    <body>
        <h1>ADD/REMOVE Student</h1>
        Page: <a href="AddRemoveStudent.jsp">ADD/REMOVE Student</a> | <a href="AllData.jsp">Show All Data</a>
        
        <h3>Add Student Data</h3>
        <form action="AddServlet" method="post">
            ID: <input type="text" name="id" required>  Name: <input type="text" name="name" required><br>
            Score: <input type="text" name="score" required><br>
            <input type="submit" value="ADD">
        </form>
        <hr>
        <h3>Remove Student Data</h3>
        <form action="RemoveServlet" method="post">
            ID: <input type="text" name="id" required> <br>
            <input type="submit" value="ADD">
        </form>
        <hr>
        ${message}
    </body>
</html>

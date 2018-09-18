<%-- 
    Document   : Task
    Created on : Sep 18, 2018, 4:11:44 PM
    Author     : jatawatsafe
--%>

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
        <h1>User:Steve</h1>
        <form action="Task" method="post">
            New Task: <input type="text" name="todolist" required>
            <input type="submit">
        </form>
    </body>
</html>

<%-- 
    Document   : RegisterForm
    Created on : Sep 14, 2018, 11:08:14 AM
    Author     : jatawatsafe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    </head>
    <body>
        <nav class="navbar navbar-inverse">
             <jsp:include page="include/Header.jsp"/>
        </nav>
        <h1>Register Form</h1>
        <form action="Register" method="post">
            Email: <input type="email" name="email" required/><br>
            Password: <input type="password" name="password" required/><br>
            <input type="submit" value="Register"/>
        </form>
    </body>
</html>

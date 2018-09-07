<%-- 
    Document   : Login
    Created on : Sep 7, 2018, 11:56:47 AM
    Author     : INT303
--%>

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
        <nav class="navbar navbar-inverse">
             <jsp:include page="include/Header.jsp"/>
        </nav>
        <div style="padding: 10px;" class="container">
            <h1>Authentication::</h1><hr>
            <form action="Login" method="post">
                <table>
                    <tr>
                        <td>Username: </td>
                        <td><input type="number" name="userName" required/></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input type="password" name="password" required/></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Login"/></td>
                    </tr>
                    <tr>
                        <td colspan="2"><p style="color: red">${message}</p></td>
                    </tr>
                </table>
            </form>
    </body>
</html>

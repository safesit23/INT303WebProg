<%-- 
    Document   : ChooseBackground
    Created on : Aug 24, 2018, 9:52:43 AM
    Author     : INT303
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
            <form action="ChooseBackground" method="post">
                <input type="radio" name="bgColor" value="dark" />Dark 
                <input type="text" size="3" disabled style="background-color: slategray" />
                <br>
                <input type="radio" name="bgColor" value="navy" />Navy
                <input type="text" size="3" disabled style="background-color: navy" />
                <br>
                <input type="radio" name="bgColor" value="silver" />Silver 
                <input type="text" size="3" disabled style="background-color: silver" />
                <br>
                <input type="submit">
            </form>    
    </body>
</html>

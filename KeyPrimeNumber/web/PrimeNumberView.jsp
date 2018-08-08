<%-- 
    Document   : PrimeNumberView
    Created on : Aug 8, 2018, 9:49:23 AM
    Author     : INT303
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prime Number</title>
    </head>
    <body>
        <h1>Prime Number Application::</h1><br>
        <form action="PrimeNumber" method="post">
            Please enter number:
            <input type="number" required min="2"/>
            <input type="submit" value="CHECKING"/>
        </form>
        <hr>
        ${pn.number}is ${pn.primeNumber ? "":"not"} Prime Number.
    </body>
</html>

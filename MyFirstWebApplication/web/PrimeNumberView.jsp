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
        <style>
            body{
                font-family: sans-serif;
                text-align: center;
            }
        </style>
        <h1>Prime Number Application::</h1><hr><br>
        <form action="PrimeNumber" method="post">
            Please enter number:
            <input type="number" name="number" required min="2"/>
            <input type="submit" value="CHECKING"/>
        </form>
        <br><hr>
        <h4>${pn.number} is ${pn.primeNumber ? "":"not"} Prime Number.</h4>
        <p>Checking Object: ${pn}</p>
        <a href="/MyFirst/index.html"><button>Back to menu</button></a>
    </body>
</html>

<%-- 
    Document   : PrimeNumberView
    Created on : Aug 6, 2018, 11:50:10 PM
    Author     : jatawatsafe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <style>
            body{
                padding: 50px;
                text-align: center;
                font-family: sans-serif;
                background-color: #FFFFED;
            }
        </style>
        <h1>CHECKING PRIME NUMBER</h1>
        <h4>${primeNum.number} is ${primeNum.resultPrimeNumber}</h4>
        <a href="/Homework/index.html"><button>Try Again!</button></a>
        <p>Cr.Jatawatsafe</p>
    </body>
</html>

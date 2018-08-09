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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    </head>
    <body>
        <style>
            body{
                font-family: sans-serif;
                text-align: center;
            }
        </style>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">INT303 WebProgramming</a>
                </div>
                <ul class="nav navbar-nav">
                    <li><a href="/MyFirst/index.html">Home</a></li>
                    <li class="active"><a href="PrimeNumber">1.Prime Number</a></li>
                    <li><a href="/MyFirst/CalculatorForm.html">2.Simple Calculator</a></li>
                    <li><a href="TestRequestParam">3.TestRequestParam</a></li>
                    <li><a href="ProductList">4.Our Product</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-shopping-cart"></span> CART (${cart.totalQuantity})  </a></li>
                </ul>
            </div>
        </nav>
        <h1>Prime Number Application::</h1><hr><br>
        <form action="PrimeNumber" method="post">
            Please enter number:
            <input type="number" name="number" required min="2"/>
            <input type="submit" value="CHECKING"/>
        </form>
        <br><hr>
        <h4>${pn.number} is ${pn.primeNumber ? "":"not"} Prime Number.</h4>
        <p>Checking Object: ${pn}</p>
        <div id="buttonFooter" class="container" align="center">
            <a href="/MyFirst/index.html"><button class="btn btn-info">Back to menu</button></a>
        </div>
    </body>
</html>

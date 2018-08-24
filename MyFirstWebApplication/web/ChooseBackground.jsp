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
        <style>
            body{
                background-color: #FFFDFA;
            }
        </style>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand">INT303 WebProgramming</a>
                </div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/MyFirst/index.html">Home</a></li>
                    <li><a href="PrimeNumber">1.Prime Number</a></li>
                    <li><a href="/MyFirst/CalculatorForm.html">2.Simple Calculator</a></li>
                    <li><a href="TestRequestParam">3.TestRequestParam</a></li>
                    <li><a href="ProductList">4.Our Product</a></li>
                    <li><a href="ChooseBackground">5. Choose Background</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <jsp:include page="include/Header.jsp"/>
                </ul>
            </div>
        </nav>
        <div style="padding: 20px;">        
            <form  action="" >
                <input type="radio" name="bgColor" value="dark" />Dark 
                <input type="text" size="3" disabled style="background-color: slategray" />
                <br>
                <input type="radio" name="bgColor" value="navy" />Navy
                <input type="text" size="3" disabled style="background-color: navy" />
                <br>
                <input type="radio" name="bgColor" value="silver" />Silver 
                <input type="text" size="3" disabled style="background-color: silver" />
                <br>
            </form>    
        </div>
        <h1>Test!</h1>
    </body>
</html>

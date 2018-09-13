<%-- 
    Document   : WeightConverterView.jsp
    Created on : Sep 13, 2018, 9:11:21 PM
    Author     : jatawatsafe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SIT SPACE</title>
    </head>
    <body>
        <style>
            h1{
                text-align: center;
            }
        </style>
        
        <h1>Weight Converter</h1>
        <form action="WeightConverter" method="post">
            Name: <input type="text" name="name" /><br>
            Weight: <input type="number" name="weight" /> kg<br>
            <input type="submit" value="ENTER">
        </form>
        <div>
            <h5>DATA THAT WE GET:</h5>
            <p>
                NAME: ${name}<br>
                WEIGHT: ${weight} kg
        </div>
                
    </body>
</html>



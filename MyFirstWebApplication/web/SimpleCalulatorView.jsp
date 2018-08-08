<%-- 
    Document   : SimpleCalulatorView
    Created on : Aug 6, 2018, 4:10:40 PM
    Author     : INT303
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>RESULT ::</h1><hr>
        <table>
            <tr>
                <td>x</td>
                <td> = </td>
                <td> ${calculator.x} </td>
            </tr>
            <tr>
                <td>y</td>
                <td> = </td>
                <td> ${calculator.y} </td>
            </tr>
            <tr>
                <td>operator</td>
                <td> = </td>
                <td> ${calculator.operator} </td>
            </tr>
            <tr>
                <td>result</td>
                <td> = </td>
                <td> ${calculator.result} </td>
            </tr>
        </table>
            <a href="/MyFirst/index.html"><button>Back to menu</button></a>
    </body>
</html>

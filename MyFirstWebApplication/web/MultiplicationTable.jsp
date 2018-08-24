<%-- 
    Document   : MultiplicationTable
    Created on : Aug 10, 2018, 2:08:56 PM
    Author     : INT303
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MultiplicationTable</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    </head>
    <body>
        <style>
            body{
                background-color: #FFFDFA;
            }
            #buttonFooter{
                padding: 25px;
            }
            .multiTable{
                font-size: 2em;
            }
        </style>
        <nav class="navbar navbar-inverse">
             <jsp:include page="include/Header.jsp"/>
        </nav>
        <!------------------------Code----------------->
        <div align="center" class="multiTable">
            <h1>Multiplication Table</h1>
            <table>
                <tr>
                    <td colspan="5">Table of ${param.n}</td>
                </tr>
                <c:forEach begin="2" end="12" var="x">
                    <tr>
                        <td>${param.n}</td>
                        <td> x </td>
                        <td>${x}</td>
                        <td> = </td>
                        <td>${param.n * x}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>



    </body>
</html>

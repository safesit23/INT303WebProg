<%-- 
    Document   : AllData
    Created on : Dec 9, 2018, 3:27:46 PM
    Author     : jatawatsafe
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AllData</title>
    </head>
    <body>
        <style>
            table{
                border-spacing: 0px;
            }
            td, th{
                border: 1px black solid;
                width: 100px;
                text-align: center;
                margin: 0px;
            }
        </style>
        <h1>SitSpaceManagement</h1>
        Page: <a href="AddRemoveStudent.jsp">ADD/REMOVE Student</a> | <a href="AllData.jsp">Show All Data</a>

        <h3>All Data</h3>
        <table>
            <tr>
                <th>#</th>
                <th>ID</th>
                <th>Name</th>
                <th>Score</th>
                <th>Grade</th>
            </tr>
            <c:forEach items="${sessionScope.allStudent.allData}" var="student" varStatus="vs">
                <tr>
                    <td>${vs.count}</td>
                    <td>${student.id}</td>
                    <td>${student.name}</td>
                    <td>${student.score}</td>
                    <td>${student.calculateGrade}</td>
                </tr>
            </c:forEach>
        </table>



    </body>
</html>

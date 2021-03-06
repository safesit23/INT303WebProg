<%-- 
    Document   : ViewParameter
    Created on : Aug 8, 2018, 1:57:28 PM
    Author     : INT303
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TestRequestParam</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap.min.css">
    </head>
    <body>
        <nav class="navbar navbar-inverse">
             <jsp:include page="include/Header.jsp"/>
        </nav>
        <div style="padding: 10px;">
            <h1>Favorite Subject::</h1><hr>
            <form action="TestRequestParam" method="post">
                Your Student Id: <input type="text" name="id" required/>
                Your name: <input type="text" name="name" required/><br>
                Favorite Subject: <br>
                <input type="checkbox" name="subjects" value="Web Programming">Web Programming<br>
                <input type="checkbox" name="subjects" value="Database Administrator">Database Administrator<br>
                <input type="checkbox" name="subjects" value="Computer Network">Computer Network<br>
                <input type="checkbox" name="subjects" value="Computer Security">Computer Security<br>
                <input type="checkbox" name="subjects" value="Network Programming">Network Programming<br>
                <input type="checkbox" name="subjects" value="Software Process">Software Process<br>
                <input type="submit"/>
            </form>
            <hr>
            <table class="table-condensed">
                <tr>
                    <td>Student ID:</td>
                    <!---ชื่อเดียวกับที่ตั้งใน Servlet--->
                    <td>${param.id}</td>
                </tr>
                <tr>
                    <td>Student name:</td>
                    <td>${param.name}</td>
                </tr>
                <tr>
                    <td>Your Favorite Subjects :</td>
                    <td>
                        <c:forEach items="${subjectList}" var="str">
                            ${str}, 
                        </c:forEach>
                    </td>
                </tr>
            </table>
            <div id="buttonFooter" class="container" align="center">
            <a href="/MyFirst/index.html"><button class="btn btn-info">Back to menu</button></a>
        </div>
        </div>

    </body>
</html>

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
    </head>
    <body>
        <h1>Favorite Subject::</h1><hr>
        <form action="TestRequestParam" method="post">
            Your Student Id: <input type="text" name="id" required/><br>
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
        <table>
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
            <a href="/MyFirst/index.html"><button>Back to menu</button></a>
    </body>
</html>

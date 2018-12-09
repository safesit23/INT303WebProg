<%-- 
    Document   : TestParameter
    Created on : Dec 9, 2018, 10:52:34 PM
    Author     : jatawatsafe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TestParam</title>
    </head>
    <body>
        <h1>Test Parameter</h1>
        <form action="TestParamServlet" method="get">
            <input type="checkbox" name="music" value="pop1">Pop<br>
            <input type="checkbox" name="music" value="pop2">Pop<br>
            <input type="checkbox" name="music" value="pop3">Pop<br>
            <input type="checkbox" name="music" value="pop4">Pop<br>
            <input type="checkbox" name="music" value="pop5">Pop<br>
            <input type="submit" value="SEND">
        </form>
        <hr>
        <h1>OUTPUT</h1>
        JSPTag: <%= request.getAttribute("output")%><br>
        JSP EL: ${output} <br>
        <a href="index.html">Go Back</a>
    </body>
</html>

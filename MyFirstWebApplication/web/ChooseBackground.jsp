<%-- 
    Document   : ChooseBackground
    Created on : Aug 24, 2018, 9:52:43 AM
    Author     : INT303
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            #bgColor{
                padding: 25px;
            }
            .colorbox{
                height: 300px;
            }
        </style>
        <nav class="navbar navbar-inverse">
            <jsp:include page="include/Header.jsp"/>
        </nav>
        <div id="bgColor">
            <h1 align="center">Change Background Color</h1>
            <form action="ChooseBackground" method="post">
                <div class="container">
                    <div class="row">
                        <div class="col-sm-3 colorbox" style="background-color: #232324">
                        </div>
                        <div class="col-sm-3 colorbox" style="background-color: #450000">
                        </div>
                        <div class="col-sm-3 colorbox" style="background-color: #172E18">
                        </div>
                        <div class="col-sm-3 colorbox" style="background-color: #050E54">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3" >
                            <input type="radio" name="bgColor" value="#232324" 
                                   ${cookie.bgColor.value == "#232324" ? "checked": ""} 
                                   />Dark 
                        </div>
                        <div class="col-sm-3" >
                            <input type="radio" name="bgColor" value="#450000" 
                                   ${cookie.bgColor.value == "#450000" ? "checked": ""} 
                                   />Brown
                        </div>
                        <div class="col-sm-3" >
                            <input type="radio" name="bgColor" value="#172E18" 
                                   ${cookie.bgColor.value == "#172E18" ? "checked": ""} 
                                   />Green 
                        </div>
                        <div class="col-sm-3" >
                            <input type="radio" name="bgColor" value="#050E54" 
                                   ${cookie.bgColor.value == "#050E54" ? "checked": ""}
                                   />blue 
                        </div>
                    </div>
                </div>
                <div>
                    <input type="submit">
                </div>
            </form>
            <hr>
            Session ID: ${cookie.JSESSIONID.value}
        </div>
    </body>
</html>

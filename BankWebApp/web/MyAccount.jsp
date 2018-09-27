<%-- 
    Document   : MyAccount
    Created on : Sep 27, 2018, 4:28:11 PM
    Author     : jatawatsafe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bank</title>
    </head>
    <body>
        <h1>MyAccount</h1>
        <h2>${account.name}</h2>
        <h2>${account.balance}</h2>
        <br>
        <a href="Deposit">Deposit</a><br>
        <a href="Withdraw">Withdraw</a><br>
    </body>
</html>

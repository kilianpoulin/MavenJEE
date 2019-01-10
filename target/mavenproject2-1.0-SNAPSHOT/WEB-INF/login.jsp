<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JavaEEProject - Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <!-- If there is an error message set as an attribute, that message will be printed -->
        <p>${ErrMessage}</p>
      
        <form method="POST" action="Controller">
            <input type="text" name="login" placeholder="Login"/> <br/>
            <input type="password" name="pwd" placeholder="Password"/><br/>
            <input type="submit" value="Login" />
        </form>
    </body>
</html>

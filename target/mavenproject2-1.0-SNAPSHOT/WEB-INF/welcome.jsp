<%@page import="model.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/page.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    
</head><body>
    <!-- To logout -->
    <p class="logout">
        <font color="blue">Your session is active</font>
        <a href="Controller?sub=Disconnect"><img style="width:20px;" src="img/logout.png" alt="Log out"></a>
    </p>
        <div class="w3-container">
            <h1>List of Employees</h1><br/>

            <table class='w3-table w3-striped w3-centered'>
                <form method ='GET' action='Controller'>
                    <tr class='w3-blue'>
                        <th>Sel</th>
                        <th>NAME</th>
                        <th>FIRST NAME</th>
                        <th>HOME PHONE</th>
                        <th>MOBILE PHONE</th>
                        <th>WORK PHONE</th>
                        <th>ADDRESS</th>
                        <th>POSTAL CODE</th>
                        <th>CITY</th>
                        <th>EMAIL</th>
                    </tr>
                    <!-- 
                    Because we don't have Java anymore here, we need to display the list in EL.
                    To make it so, we will put in the variable named employee (var = "employee") the value of the list that we put as an attribute
                    into the servlet Controller.
                    Then we will loop through it with a forEach and print for each of the node, the attributes of that node.
                    If a button is clicked (Delete or Details) we'll need the ID of the employee.
                    Each time an employee is printed, the value of his ID is stocked on a radiobox.
                    When a radiobox is full and we will on the buttons, it will send the value of the ID to do work.
                    -->
                     <c:forEach items = "${keyListEmployees}" var = "employee">
                    <tr>
                            <td><input type='radio' name='edit' value='<c:out value = "${employee.getEmployeeId()}"/>'/></td>
                            <td><c:out value = "${employee.getName()}"/></td>
                            <td><c:out value = "${employee.getFirstname()}"/></td>
                            <td><c:out value = "${employee.getHomephone()}"/></td>
                            <td><c:out value = "${employee.getMobilephone()}"/></td>
                            <td><c:out value = "${employee.getWorkphone()}"/></td>
                            <td><c:out value = "${employee.getAddress()}"/></td>
                            <td><c:out value = "${employee.getZipcode()}"/></td>
                            <td><c:out value = "${employee.getCity()}"/></td>
                            <td><c:out value = "${employee.getEmail()}"/></td>
                    </tr>
                    </c:forEach>
            </table>
        </div>
        <br/><br/>
        <!-- Depending on the button clicked, the value of sub will change and the servlet will adapt the demand -->
        <div class="w3-container">
            <input type="submit" name="sub" value="Delete" class="button"/>
            <input type="submit" name="sub" value="Details" class="button"/>
            <input type="submit" name="sub" value="Add" class="button"/>
        </div>
    </form>
</body>
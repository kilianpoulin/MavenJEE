<%@page import="java.util.ArrayList"%>
<%@page import="model.Employee"%>
<%@page import="model.Employee"%>
<%@page import="model.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- 
    On this page, there are two possibilities for the user : Add or Details depending on what he clicked.
    On the servlet, the value of choice was set depending on which button the user clicked.
    If he clicked on Details, each value of the HTML input is already filled with the attributes of the employee, else it is empty and free to fill.
-->

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/complete.css">
        <title>JSP Page</title>
    </head>
    <body>
    <p class="logout">
        <font color="blue">Your session is active</font>
        <a href="Controller?sub=Disconnect"><img style="width:20px;" src="img/logout.png" alt="Log out"></a>
    </p>
    <center><h2>Detail of ${choice == "Details" && e != null ? "employee : " : " new Employee"} 
                          ${choice == "Details" && e != null ? e.getName() : ""} 
    </h2></center>
    
     <fieldset id="fieldset">
            <form method="POST" action="Controller">
                <table class="tableform">
                    <input type="hidden" name="id_mod" value="${choice == "Details" && e != null ? e.getEmployeeId() : ""}"/>
                    <tr>
                        <td><label for="name">Name</label></td>
                        <td colspan="3"><input type="text" name="name" value="${choice == "Details" ? e.getName() : ""} " class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="firstname">First name</label></td>
                        <td colspan="3"><input type="text" name="firstname" value="${choice == "Details" ? e.getFirstname() : ""}" class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="home_phone">Home Pho</label></td>
                        <td colspan="3"><input type="text" name="home_phone" value="${choice == "Details" ? e.getHomephone() : ""}" class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="mobile_phone">Mob Pho</label></td>
                        <td colspan="3"><input type="text" name="mobile_phone" value="${choice == "Details" ? e.getMobilephone() : ""}" class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="pro_phone">Pro Pho</label></td>
                        <td colspan="3"><input type="text" name="pro_phone" value="${choice == "Details" ? e.getWorkphone() : ""}" class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="address">Address</label></td>
                        <td colspan="3"><input type="text" name="address" value="${choice == "Details" ? e.getAddress() : ""}" class="field"><br/><br/></td>
                    </tr>
                    <tr>
                        <td><label for="zip_code">Zip code</label></td>
                        <td colspan="3"><input type="text" name="zip_code" value="${choice == "Details" ? e.getZipcode() : ""}" class="field"><br/><br/></td>
                    </tr>
                                        
                    <tr>
                        <td><label for="city">City</label></td>
                        <td><input type="text" name="city" value="${choice == "Details" ? e.getCity() : ""}" class="field2"><br/><br/></td>
                        <td><label for="email">Email address</label></td>
                        <td><input type="email" name="email" value="${choice == "Details" ? e.getEmail() : ""}" class="field2"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        ${choice == "Details" ? "<td><input type='submit' name='sub' value='Save' id='izipanier'></td>" : 
                          "<td><input type='submit' name='sub' value='AddNew' id='izipanier'></td>"}
         
                        <td><a href="Controller?sub=success"><input type="button" name="cancel" value="Cancel" id="cancel"></a><br/></td>
                        <td></td>
                    </tr>
                    
                </table>
             </form>
        </fieldset>
    </body>
</html>

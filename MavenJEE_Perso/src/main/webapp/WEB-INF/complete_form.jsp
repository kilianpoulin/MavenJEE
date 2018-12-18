<%-- 
    Document   : complete_form.jsp
    Created on : 14 déc. 2018, 08:40:40
    Author     : Kilian
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Employee"%>
<%@page import="model.Employee"%>
<%@page import="model.DataAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    DataAccess dTransac = new DataAccess(); 
    
    String id = (String) request.getAttribute("id");
    String choice = (String) request.getAttribute("choice");
    String query = "SELECT * FROM EMPLOYEES WHERE ID_EMPLOYEES = " + id;
    //" + session.getAttribute(id_employees);
    
    ArrayList <Employee> Employees = dTransac.getDBEmployees(dTransac.getResultSet(dTransac.getStatement(dTransac.getConnection()), query));
    Employee e = Employees.get(0);
    
    request.setAttribute("id", id);
    request.setAttribute("details", choice);
    
    boolean tr = false;
    if(choice.equals("Details"))
        tr = true;
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>JSP Page</title>
    </head>
    <body>
    <center><h2>Detail of employee : <% if(tr) out.println(e.getName() + " " + e.getFirstName()); %></h2></center>
        
        <fieldset id="fieldset">
            <form method="POST" action="AddtoBDD">
                <table class="tableform">
                    <tr>
                        <td><label for="name">Name</label></td>
                        <td colspan="3"><input type="text" name="name" value="<% if(tr) out.println(e.getName()); %>" class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="firstname">First name</label></td>
                        <td colspan="3"><input type="text" name="firstname" value="<% if(tr) out.println(e.getFirstName()); %>" class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="home_phone">Home Pho</label></td>
                        <td colspan="3"><input type="text" name="home_phone" value="<% if(tr) out.println(e.getHomePhone()); %>" class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="mobile_phone">Mob Pho</label></td>
                        <td colspan="3"><input type="text" name="mobile_phone" value="<% if(tr) out.println(e.getMobilePhone()); %>" class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="pro_phone">Pro Pho</label></td>
                        <td colspan="3"><input type="text" name="pro_phone" value="<% if(tr) out.println(e.getWorkPhone()); %>" class="field"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td><label for="address">Address</label></td>
                        <td colspan="3"><input type="text" name="address" value="<% if(tr) out.println(e.getAddress()); %>" class="field"><br/><br/></td>
                    </tr>
                    <tr>
                        <td><label for="zip_code">Zip code</label></td>
                        <td colspan="3"><input type="text" name="zip_code" value="<% if(tr) out.println(e.getPostalCode()); %>" class="field"><br/><br/></td>
                    </tr>
                                        
                    <tr>
                        <td><label for="city">City</label></td>
                        <td><input type="text" name="city" value="<% if(tr) out.println(e.getCity()); %>" class="field2"><br/><br/></td>
                        <td><label for="email">Email address</label></td>
                        <td><input type="email" name="email" value="<% if(tr) out.println(e.getEmail()); %>" class="field2"><br/><br/></td>
                    </tr>
                    
                    <tr>
                        <td></td>
                        <td><input type="submit" name="save" value="Save" id="izipanier"></td>
                        <td><a href="welcome.jsp"><input type="button" name="cancel" value="Cancel" id="cancel"></a><br/></td>
                        <td></td>
                    </tr>
                    
                </table>
                
                
                

                
                

                
                
             </form>
        </fieldset>
    </body>
</html>

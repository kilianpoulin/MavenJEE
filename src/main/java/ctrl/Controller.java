package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;
import model.Credentials;
import model.DataAccess;
import model.Employee;

public class Controller extends HttpServlet 
{
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mathieu.EtchepareBg_mavenproject2_war_1.0-SNAPSHOTPU");
    EntityManager em = emf.createEntityManager();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws javax.transaction.NotSupportedException
     * @throws javax.transaction.SystemException
     * @throws javax.transaction.RollbackException
     * @throws javax.transaction.HeuristicMixedException
     * @throws javax.transaction.HeuristicRollbackException
     */
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NotSupportedException, SystemException, RollbackException, HeuristicMixedException, HeuristicRollbackException 
    {
        try(PrintWriter out = response.getWriter())
        {
            String login = request.getParameter("login");
            String pwd = request.getParameter("pwd");
            
            String sub = (String)request.getParameter("sub");
            
            String name = request.getParameter("name");
            String fname = request.getParameter("firstname");
            String hphone = request.getParameter("home_phone");
            String mphone = request.getParameter("mobile_phone");
            String pphone = request.getParameter("pro_phone");
            String address = request.getParameter("address");
            String zipcode = request.getParameter("zip_code");
            String city = request.getParameter("city");
            String email = request.getParameter("email");
            String id = request.getParameter("edit");
            String id_mod = request.getParameter("id_mod");
            
            HttpSession session = request.getSession();
            Credentials user = (Credentials) session.getAttribute("user");
            String query;
            DataAccess dTransac = new DataAccess();

            if(user != null){
                if(sub != null)
                {
                    switch (sub) {
                        case "Delete":
                            //This code is left here because we didn't succeed to make any transaction with the BDD, we didn't want to work for nothing
                            /*
                            Query query3 = em.createNamedQuery("Employee.deleteById");
                            query3.setParameter("emploID", Integer.parseInt(id)).executeUpdate();*/
                            query = "DELETE FROM employee WHERE EMPLOYEE_ID=" + Integer.parseInt(id);
                            dTransac.executeUpdate(query);
                            request.getRequestDispatcher("Controller?sub=success").forward(request, response);
                        case "Add":
                            request.setAttribute("choice", "Add");
                            request.getRequestDispatcher("WEB-INF/complete_form.jsp").forward(request, response);
                            break;
                        case "Details":
                            request.setAttribute("id", id);
                            request.setAttribute("choice", "Details");
                            if(id != null)
                            {
                                //By using createNamedQuery, we can directly give the value into query1 by calling Employee.findByEmployeeId
                                Query query1 = em.createNamedQuery("Employee.findByEmployeeId");
                                /*
                                Then, we need an ID to use that query. We will use setParameter to give to the query the value of the employee
                                chosen by the user before.
                                getSingleResult is used because we only want to print the details of one employee.
                                */
                                Employee e = (Employee) em.createNamedQuery("Employee.findByEmployeeId")
                                        .setParameter("employeeId", Integer.parseInt(id))
                                        .getSingleResult();
                                
                                request.setAttribute("e", e);                           
                            }
                            request.getRequestDispatcher("WEB-INF/complete_form.jsp").forward(request, response);
                            break;
                        case "AddNew":
                            //Add a new employee to the BDD
                            query = "INSERT INTO EMPLOYEE(NAME, FIRSTNAME, HOMEPHONE, MOBILEPHONE, WORKPHONE, ADDRESS, ZIPCODE, CITY, EMAIL)" +
                                    "VALUES('"+ name + "','" + fname+ "','" + hphone + "','" + mphone + "','" + pphone + "','" + address + "','" + zipcode + "','" + city + "','" + email+"')";
                            dTransac.executeUpdate(query);
                            request.getRequestDispatcher("Controller?sub=success").forward(request, response);
                        case "Save":
                            //Save the details entered by the user
                            query = "UPDATE employee SET NAME='" + name +"' ,FIRSTNAME='" + fname +"' ,HOMEPHONE='" + hphone +"' ,MOBILEPHONE='" + mphone+"' ,WORKPHONE='" + pphone+"' ,ADDRESS='" + address+"' ,ZIPCODE='" + zipcode+"' ,CITY='" + city+"' ,EMAIL='" + email+"' WHERE EMPLOYEE_ID=" + Integer.parseInt(id_mod);
                            dTransac.executeUpdate(query);
                            
                            request.getRequestDispatcher("Controller?sub=success").forward(request, response);
                        case "Disconnect":
                            session.setAttribute("user", null);
                            request.getRequestDispatcher("WEB-INF/goodbye.jsp").forward(request, response);
                            break;
                        default: 
                            //If the value of sub is none of the above, we will simply redirect the user to welcome.jsp
                            //To be sure that modification were updated, we will reload the list of employee
                            Query query2 = em.createNamedQuery("Employee.findAll");
                            List<Employee> Employees = query2.getResultList();

                            session.setAttribute("user", user);
                            request.setAttribute("keyListEmployees", Employees);
                            request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
                            break;
                    }
                } 
            }
            else if(login == null && pwd == null){
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
            else{
                if(login.equals("") || pwd.equals(""))
                {
                    request.setAttribute("ErrMessage", "You must enter values in both fields");
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                }
                else
                {
                    //Créer un utilisateur avec les identifiants rentrés
                    user = new Credentials();
                    user.setLogin(login);
                    user.setPwd(pwd);

                    //That query is used to get all the users in the table Credentials
                    Query query1 = em.createNamedQuery("Credentials.findAll");
                    //By putting them on a list with getResultList, we can try the validation of the account that the user entered
                    List<Credentials> userList = query1.getResultList();
                    for(Credentials u : userList)
                    {
                        //We call the function in the file Credentials.java to compare 2 instances of Credentials
                        if(u.equals(user))
                        {
                            /*
                            If the connection is validated, we will have to go through welcome.jsp
                            Because of the fact that there is no Java on JSP files anymore, we will load the list of employee here and set
                            it as an attribute.
                            */
                            Query query2 = em.createNamedQuery("Employee.findAll");
                            List<Employee> Employees = query2.getResultList();

                            //To make sure that we can enter the first loop at the top (user != null) we will set the user validated as an attribute
                            session.setAttribute("user", user);
                            //We pass the list to retrieve it in welcome.jsp and then we redirect
                            request.setAttribute("keyListEmployees", Employees);
                            request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
                        }
                    }
                    request.setAttribute("ErrMessage", "Verify your login/password and try again!");
                    request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                } 
            }
            out.close();
            }
    }
    
    /**
     *
     * @param id
     */
    @Transactional
    public void delete(String id)
    {        
        Query query3 = em.createNamedQuery("Employee.deleteById");
        query3.setParameter("emploID", Integer.parseInt(id)).executeUpdate();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

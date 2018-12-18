package ctrl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DataAccess;
import model.userSession;

public class Controller extends HttpServlet {

    /**
     * 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()){
        String login = request.getParameter("login");
        String pwd = request.getParameter("pwd");
        String sub = (String)request.getParameter("sub");
        
        if(sub != null){
            request.setAttribute("id", request.getParameter("edit"));
            switch (sub) {
                case "Delete":
                    request.getRequestDispatcher("WEB-INF/delete.jsp").forward(request, response);
                    break;
                case "Add":
                    request.setAttribute("choice", "Add");
                    request.getRequestDispatcher("WEB-INF/complete_form.jsp").forward(request, response);
                    break;
                default:
                    request.setAttribute("choice", "Details");
                    request.getRequestDispatcher("WEB-INF/complete_form.jsp").forward(request, response);
                    break;
            }
            
        }
        else if(login == null || pwd == null){
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
        }
        else if(sub == null){
            if(login.equals("") || pwd.equals("")){
            request.setAttribute("ErrMessage", "<p>You must enter values in both fields</p>");
            request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
            }
            else
            {
                //Créer un utilisateur avec les identifiants rentrés
                userSession user = new userSession();
                user.setLogin(login);
                user.setPassword(pwd);
                
                DataAccess dTransac = new DataAccess(); 
                String query = "SELECT LOGIN, PWD FROM CREDENTIALS";
                ArrayList <userSession> userlist = dTransac.getDBUsers(dTransac.getResultSet(dTransac.getStatement(dTransac.getConnection()), query));
                for(userSession u : userlist)
                {
                    if(u.equals(user))
                    {
                       request.getRequestDispatcher("WEB-INF/welcome.jsp").forward(request, response);
                        
                    }
                }
                request.setAttribute("ErrMessage", "<p>Verify your login/password and try again! </p>");
                request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
                
                
            } 
        } 
        out.close();
        }
        
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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

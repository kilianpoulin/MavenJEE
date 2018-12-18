/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DataAccess {
        // declare globally, intialize locally
    private Connection dbConn;
    private Statement stmt;
    private ResultSet rs;
    private final String dbUrl = "jdbc:derby://localhost:1527/PROJET";
    private final String user = "adm";
    private final String pwd = "adm";

        public Connection getConnection() {
        try{
            dbConn = DriverManager.getConnection(dbUrl, user, pwd);
        } catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        return dbConn;
    }
    
        
    public Statement getStatement(Connection dbConn){
         try{
            stmt = dbConn.createStatement();
        } catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        return stmt;
    }
    
        public ResultSet getResultSet(Statement stmt, String query){
        try{
            rs = stmt.executeQuery(query);
        } catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        return rs;
    }
        
        public ArrayList getDBUsers(ResultSet rs){
        ArrayList<userSession> userDBList = new ArrayList<userSession>();
        try{
            while(rs.next()){
                userSession uS = new userSession();
                uS.setLogin(rs.getString("LOGIN"));
                uS.setPassword(rs.getString("PWD"));
                userDBList.add(uS);
            }
        } catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        return userDBList;
    }
        
    public ArrayList getDBEmployees(ResultSet rs){
        ArrayList<Employee> Employees = new ArrayList<Employee>();
        try{
            while(rs.next()){
                Employee e = new Employee();
                e.setName(rs.getString("NAME"));
                e.setFirstName(rs.getString("FIRSTNAME"));
                e.setHomePhone(rs.getString("HOMEPHONE"));
                e.setMobilePhone(rs.getString("MOBILEPHONE"));
                e.setWorkPhone(rs.getString("WORKPHONE"));
                e.setAddress(rs.getString("ADDRESS"));
                e.setPostalCode(rs.getString("POSTALCODE"));
                e.setCity(rs.getString("CITY"));
                e.setEmail(rs.getString("EMAIL"));
                e.setID(rs.getString("ID_EMPLOYEES"));
                Employees.add(e);
            }
        } catch(SQLException sqle){
            System.out.println(sqle.getMessage());
        }
        return Employees;
    }
    
    public void executeUpdate(String query){
        try {
            getStatement(getConnection()).executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

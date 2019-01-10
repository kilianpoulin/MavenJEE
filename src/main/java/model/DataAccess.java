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


public class DataAccess {
    // declare globally, intialize locally
    private Connection dbConn;
    private Statement stmt;
    private ResultSet rs;
    private final String dbUrl = "jdbc:mysql://localhost:3306/projet?zeroDateTimeBehavior=convertToNull";
    private final String user = "root";
    private final String pwd = "";

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
    
    public void executeUpdate(String query){
        try {
            getStatement(getConnection()).executeUpdate(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}

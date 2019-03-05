/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author tnd
 */
public class DataAccessHelper {
    public Connection conn=null;
    public String dbPath;
    public String username;
    public String password;
    private static DataAccessHelper instance=null;
    public static DataAccessHelper getInstance()
    {
        if(instance==null)
            instance=new DataAccessHelper();
        return instance;
    }
    private DataAccessHelper(){}

    public void getConnect() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(dbPath+"?user="+username+"&password="+password);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
        }        
    } 
    
    public void getClose(){
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataAccessHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

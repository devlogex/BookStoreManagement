/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
/**
 *
 * @author tnd
 */
public class Account {
    private String userName;
    private String displayName;
    private String password;
    private String typeAccount;
    
    public Account(){}
    public Account(String userName, String displayName, String password, String typeAccount)
    {
        this.userName=userName;
        this.displayName=displayName;
        this.password=password;
        this.typeAccount=typeAccount;
    }
    
    public boolean Login(String username,String password) throws SQLException
    {
        String SQL="call USP_Login(\""+username+"\",\""+password+"\")";
        try{
            DataAccessHelper.getInstance().getConnect();
            Statement statement =DataAccessHelper.getInstance().conn.createStatement();
            ResultSet rs=statement.executeQuery(SQL);
            if(rs.next())
            {
                DataAccessHelper.getInstance().getClose();
                return true;
            }
            DataAccessHelper.getInstance().getClose();
        } catch (Exception e) {}
        return false;
    }

    public Account getAccountByUsername(String username) {
        String SQL="call USP_Login(\""+username+"\")";
        Account acc=null;
        try{
            DataAccessHelper.getInstance().getConnect();
            Statement statement =DataAccessHelper.getInstance().conn.createStatement();
            ResultSet rs=statement.executeQuery(SQL);
            acc=new Account(rs.getString("TenDangNhap"),rs.getString("TenHienThi"),rs.getString("MatKhau"),rs.getString("LoaiTaiKhoan"));
            
            DataAccessHelper.getInstance().getClose();
        } catch (Exception e) {}
        return acc;
    }
}

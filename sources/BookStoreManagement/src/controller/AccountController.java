/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.sql.SQLException;
import model.Account;
/**
 *
 * @author tnd
 */
public class AccountController {
    Account Model=new Account();
    public boolean Login(String username,String password) throws SQLException{
        return Model.Login(username,password);
    }

    public Account getAccountByUsername(String username) {
         return Model.getAccountByUsername(username);
    }


}

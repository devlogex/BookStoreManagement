/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author tnd
 */
public class ImportBook {
    private String id;
    private String bookID;
    private int count;
    private float price;
    private float total;
    private Date date;
    private float valueImport;
    
    public ImportBook(){}
    public ImportBook(String id,String bookID,int count, float price, float total, Date date, float valueImport)
    {
        this.id=id;
        this.bookID=bookID;
        this.count=count;
        this.price=price;
        this.total=total;
        this.date=date;
        this.valueImport=valueImport;
    }


    public boolean AddImportBook(Date date, float value) {
        SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd");
        String SQL="call USP_AddImportBook(\""+df.format(date)+"\",\""+value+"\")";
        try{
            DataAccessHelper.getInstance().getConnect();
            Statement statement =DataAccessHelper.getInstance().conn.createStatement();
            int rs=statement.executeUpdate(SQL);
            if(rs>0)
            {
                DataAccessHelper.getInstance().getClose();
                return true;
            }
            {
                DataAccessHelper.getInstance().getClose();
                return false;
            }
        } catch (Exception e) {return false;}
    }

    public boolean AddImportBookInfo(String bookID, int count, float price, float total) {
        String SQL="call USP_AddImportBookInfo(\""+bookID+"\",\""+count+"\",\""+price+"\",\""+total+"\")";
        try{
            DataAccessHelper.getInstance().getConnect();
            Statement statement =DataAccessHelper.getInstance().conn.createStatement();
            int rs=statement.executeUpdate(SQL);
            if(rs>0)
            {
                DataAccessHelper.getInstance().getClose();
                return true;
            }
            {
                DataAccessHelper.getInstance().getClose();
                return false;
            }
        } catch (Exception e) {return false;}
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author Corazon
 */
public class ReportAmount {
    private int month;
    private int year;
    private int idBook;
    private int tondau;
    private int phatsinh;
    private int toncuoi;
    
    public ReportAmount(){}
    public ReportAmount(int month, int year, int idBook, int tondau, int phatsinh, int toncuoi)
    {
        this.month=month;
        this.year=year;
        this.idBook=idBook;
        this.tondau=tondau;
        this.phatsinh=phatsinh;
        this.toncuoi=toncuoi;
    }
    public int Month(){return month;}
    public int Year(){return year;}
    public int IdBook(){return idBook;}
    public int TonDau(){return tondau;}
    public int PhatSinh(){return phatsinh;}
    public int TonCuoi(){return toncuoi;}
    
    public ArrayList<ReportAmount> getAmountReport(int thang, int nam) {
        String SQL="call USP_GetAmountByDate('" + thang + "','" + nam + "')";
        ArrayList<ReportAmount> list=new ArrayList<ReportAmount>();
        try{
            DataAccessHelper.getInstance().getConnect();
            Statement statement =DataAccessHelper.getInstance().conn.createStatement();
            ResultSet rs=statement.executeQuery(SQL);
            while(rs.next())
            {
                int id=Integer.parseInt(rs.getString("MaSach"));
                int firstAmount=Integer.parseInt(rs.getString("TonDau"));
                int count=Integer.parseInt(rs.getString("PhatSinh"));
                int lastAmount=Integer.parseInt(rs.getString("TonCuoi"));
                ReportAmount reportAmount=new ReportAmount(thang, nam, id, firstAmount, count, lastAmount);
                list.add(reportAmount);
            }
            DataAccessHelper.getInstance().getClose();
        } catch (Exception e) {}
        return list;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import model.Book;
import java.util.ArrayList;
import javax.swing.JTable;
import model.ReportAmount;

/**
 *
 * @author Corazon
 */
public class ReportAmountController {
    ReportAmount Model = new ReportAmount();
    
    public void LoadBook(JTable table)
    {
        ArrayList<Book>list= (new Book()).getBook();
        for(int i=0;i<list.size();i++)
        {
            table.getModel().setValueAt(list.get(i).id(),i, 0);
            table.getModel().setValueAt(list.get(i).name(),i, 1);
        }
    }
    
    public void getReportTable(JTable table, int thang, int nam)
    {
        String[] head=new String[]{"Tháng/Năm","Mã sách","Tồn đầu","Phát sinh","Tồn cuối"};
        ArrayList<ReportAmount> list = Model.getAmountReport(thang,nam);
        for(int i=0;i<list.size();i++)
        {
            table.getModel().setValueAt(list.get(i).IdBook(),i, 0);
            table.getModel().setValueAt(list.get(i).TonDau(),i, 2);
            table.getModel().setValueAt(list.get(i).PhatSinh(),i, 3);
            table.getModel().setValueAt(list.get(i).TonCuoi(),i, 4);
        }
        /*DefaultTableModel dtm = new DefaultTableModel(body,head){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        table.setModel(dtm);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(200);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(220);
        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        table.getColumnModel().getColumn(8).setPreferredWidth(200);*/
    }
}

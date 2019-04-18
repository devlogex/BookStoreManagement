/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Bill;
import model.Book;
import model.Customer;

/**
 *
 * @author tnd
 */
public class BillController {
    Bill Model=new Bill();

    public void loadCustomer(JComboBox<String> cbCustomer) {
        cbCustomer.removeAllItems();
        ArrayList<Customer>list= (new Customer()).getCustomer();
        for(int i=0;i<list.size();i++)
        {
            cbCustomer.addItem(list.get(i).name()+":"+list.get(i).id());
        }
    }

    public void loadBook(JTable table) {
        String[] head=new String[]{"STT","Mã sách","Tên sách","Số lượng","Giá bán"};
        ArrayList<Book> list= (new Book()).getBook();
        Object[][] body=new Object[list.size()][5];
        for(int i=0;i<list.size();i++)
        {
            body[i][0]=i;
            body[i][1]=list.get(i).id();
            body[i][2]=list.get(i).name();
            body[i][3]=list.get(i).count();
            body[i][4]=list.get(i).price()*1.2;
        }
        DefaultTableModel dtm = new DefaultTableModel(body,head){
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
    }

    public void searchBook(String text, JTable table) {
        String[] head=new String[]{"STT","Mã sách","Tên sách","Số lượng","Giá bán"};
        ArrayList<Book> list= (new Book()).getBook();
        for(int i=0;i<list.size();i++)
        {
            if(!list.get(i).name().contains(text) && !list.get(i).id().equals(text))
            {
                list.remove(i);
                i--;
            }
        }

       Object[][] body=new Object[list.size()][5];
       for(int i=0;i<list.size();i++)
        {
            body[i][0]=i;
            body[i][1]=list.get(i).id();
            body[i][2]=list.get(i).name();
            body[i][3]=list.get(i).count();
            body[i][4]=list.get(i).price()*1.2;
        }
        DefaultTableModel dtm = new DefaultTableModel(body,head){
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
    }

    public boolean AddBill(String[][] data, String date, String value, String moneyReceive, String moneyChange, String customerID) throws ParseException {
        if(!Model.AddBill(new SimpleDateFormat("dd/MM/yyyy").parse(date),Float.parseFloat(value),Float.parseFloat(moneyReceive),Float.parseFloat(moneyChange),customerID))
            return false;
        for(int i=0;i<data.length;i++)
        {
            String id=data[i][0];
            int count=Integer.parseInt(data[i][1]);
            float price=Float.parseFloat(data[i][2]);
            float total=Float.parseFloat(data[i][3]);
            if(!Model.AddBillInfo(id,count,price,total))
                return false;
        }
        return true;
    }
}

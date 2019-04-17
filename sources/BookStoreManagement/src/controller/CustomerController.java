/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Customer;

/**
 *
 * @author tnd
 */
public class CustomerController {
    Customer Model=new Customer();

    public boolean AddCustomer(String name, String phone, String email, String address) {
        return Model.AddCustomer(name,phone,email,address);
    }

    public void loadCustomer(JTable table) {
        String[] head=new String[]{"STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Địa chỉ", "Số nợ"};
        ArrayList<Customer> list= Model.getCustomer();
        Object[][] body=new Object[list.size()][7];
        for(int i=0;i<list.size();i++)
        {
            body[i][0]=i;
            body[i][1]=list.get(i).id();
            body[i][2]=list.get(i).name();
            body[i][3]=list.get(i).phoneNumber();
            body[i][4]=list.get(i).email();
            body[i][5]=list.get(i).address();
            body[i][6]=list.get(i).owe();
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
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
    }

    public void SearchCustomer(JTable table, String text) {
        String[] head=new String[]{"STT", "Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Email", "Địa chỉ", "Số nợ"};
        ArrayList<Customer> list= Model.getCustomer();
        for(int i=0;i<list.size();i++)
        {
            if(!list.get(i).name().contains(text) && !list.get(i).id().equals(text) 
                    && !list.get(i).address().contains(text) && !list.get(i).phoneNumber().contains(text)
                    && !list.get(i).email().contains(text))
            {
                list.remove(i);
                i--;
            }
        }
        Object[][] body=new Object[list.size()][7];
        for(int i=0;i<list.size();i++)
        {
            body[i][0]=i;
            body[i][1]=list.get(i).id();
            body[i][2]=list.get(i).name();
            body[i][3]=list.get(i).phoneNumber();
            body[i][4]=list.get(i).email();
            body[i][5]=list.get(i).address();
            body[i][6]=list.get(i).owe();
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
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(200);
    }
    
    
}

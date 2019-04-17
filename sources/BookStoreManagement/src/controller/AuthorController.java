/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Author;

/**
 *
 * @author tnd
 */
public class AuthorController {
    Author Model=new Author();
    
    public boolean AddCategory(String author) {
        if(author.equals(""))
            return false;
        return Model.AddAuthor(author);
    }

    public void getAuthor(JTable table) {
        String[] head=new String[]{"STT","Mã tác giả","Tên tác giả"};
        ArrayList list= Model.getAuthor();
        Object[][] body=new Object[list.size()][3];
        for(int i=0;i<list.size();i++)
        {
            body[i][0]=i;
            body[i][1]=((Author)list.get(i)).id();
            body[i][2]=((Author)list.get(i)).name();
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
    }

    public void SearchAuthor(JTable table, String text) {
        String[] head=new String[]{"STT","Mã tác giả","Tên tác giả"};
        ArrayList list= Model.getAuthor();
        for(int i=0;i<list.size();i++)
        {
            if(!((Author)list.get(i)).name().contains(text) && !((Author)list.get(i)).id().equals(text))
            {
                list.remove(i);
                i--;
            }
        }
        Object[][] body=new Object[list.size()][3];
        for(int i=0;i<list.size();i++)
        {
            body[i][0]=i;
            body[i][1]=((Author)list.get(i)).id();
            body[i][2]=((Author)list.get(i)).name();
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
    }
    
}

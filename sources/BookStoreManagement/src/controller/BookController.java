/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Author;
import model.Book;
import model.Category;

/**
 *
 * @author tnd
 */
public class BookController {
    Book Model=new Book();

    public void getBook(JTable table) {
        String[] head=new String[]{"STT","Mã sách","Tên sách","Thể loại","Tác giả","Nhà xuất bản","Năm xuất bản","Số lượng tồn","Đơn giá nhập"};
        ArrayList<Book> list= Model.getBook();
        Object[][] body=new Object[list.size()][9];
        for(int i=0;i<list.size();i++)
        {
            body[i][0]=i;
            body[i][1]=((Book)list.get(i)).id();
            body[i][2]=((Book)list.get(i)).name();
            body[i][3]=((Book)list.get(i)).category().name();
            String authors="";
            for(int j=0;j<((Book)list.get(i)).authors().size();j++)
                authors+=((Book)list.get(i)).authors().get(j).name() +"-";
            body[i][4]=authors;
            body[i][5]=((Book)list.get(i)).publishCompany();
            body[i][6]=((Book)list.get(i)).publishYear();
            body[i][7]=((Book)list.get(i)).count();
            body[i][8]=Math.round(((Book)list.get(i)).price()*10)/10;
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
        table.getColumnModel().getColumn(6).setPreferredWidth(220);
        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        table.getColumnModel().getColumn(8).setPreferredWidth(200);
    }

    public void loadCBCategory(JComboBox<String> cbCategory) {
        cbCategory.removeAllItems();
        ArrayList<Category>list= (new Category()).getCategory();
        for(int i=0;i<list.size();i++)
        {
            cbCategory.addItem(list.get(i).name()+":"+list.get(i).id());
        }
    }

    public void loadCBAuthor(JComboBox<String> cbAuthor) {
        cbAuthor.removeAllItems();
        ArrayList<Author>list= (new Author()).getAuthor();
        for(int i=0;i<list.size();i++)
        {
            cbAuthor.addItem(list.get(i).name()+":"+list.get(i).id());
        }
    }

    public boolean AddBook(String name, String category, String authors, String publishCompany, int publishYear) {
        String categoryID=category.split(":")[1];
        ArrayList<String> authorsID=new ArrayList<String>();
        for(String temp:authors.split("-"))
        {
            authorsID.add(temp.split(":")[1]);
        }
        return Model.AddBook(name, authorsID, categoryID, publishCompany, publishYear);
    }

    public void searchBook(String text,JTable table) {
        String[] head=new String[]{"STT","Mã sách","Tên sách","Thể loại","Tác giả","Nhà xuất bản","Năm xuất bản","Số lượng tồn","Đơn giá nhập"};
        ArrayList<Book> list= Model.getBook();
        for(int i=0;i<list.size();i++)
        {
            String authors="";
            for(int j=0;j<list.get(i).authors().size();j++)
            {
                authors+=list.get(i).authors().get(j).name();
            }
            if(!((Book)list.get(i)).name().contains(text) && !((Book)list.get(i)).id().equals(text)
                    && !((Book)list.get(i)).category().name().contains(text)
                    && !authors.contains(text)
                    && !((Book)list.get(i)).publishCompany().contains(text)  )
            {
                list.remove(i);
                i--;
            }
        }

       Object[][] body=new Object[list.size()][9];
       for(int i=0;i<list.size();i++)
        {
            body[i][0]=i;
            body[i][1]=((Book)list.get(i)).id();
            body[i][2]=((Book)list.get(i)).name();
            body[i][3]=((Book)list.get(i)).category().name();
            String authors="";
            for(int j=0;j<((Book)list.get(i)).authors().size();j++)
                authors+=((Book)list.get(i)).authors().get(j).name() +"-";
            body[i][4]=authors;
            body[i][5]=((Book)list.get(i)).publishCompany();
            body[i][6]=((Book)list.get(i)).publishYear();
            body[i][7]=((Book)list.get(i)).count();
            body[i][8]=Math.round(((Book)list.get(i)).price()*10)/10;
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
        table.getColumnModel().getColumn(6).setPreferredWidth(220);
        table.getColumnModel().getColumn(7).setPreferredWidth(200);
        table.getColumnModel().getColumn(8).setPreferredWidth(200);
    }
    
    
}

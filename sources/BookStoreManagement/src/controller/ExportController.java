/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTable;

/**
 *
 * @author tnd
 */
public class ExportController {
    public void ExportTable(JTable table, File file) throws IOException{
        FileWriter out = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(out);
        for (int i = 0; i < table.getColumnCount(); i++) {
            bw.write(table.getColumnName(i)+"\t");
        }
        bw.write("\n");
        for (int i = 0; i<table.getRowCount(); i++) {
            for(int j=0;j<table.getColumnCount();j++){
                bw.write(table.getValueAt(i, j)+"\t");
            }
            bw.write("\n");
        }
        bw.close();
    }
}

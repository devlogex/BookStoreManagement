/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import model.ReportIncome;
import java.util.Date;

/**
 *
 * @author Corazon
 */
public class ReportIncomeController {
    ReportIncome Model = new ReportIncome();
    public float GetIncome(Date ngaylap){
        return Model.getIncome(ngaylap);
    }
}

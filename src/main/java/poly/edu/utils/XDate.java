/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author leminhthanh
 */
public class XDate {
    static SimpleDateFormat format = new SimpleDateFormat();
    public static Date toDate(String date, String prarern) throws ParseException{
        try {
            format.applyPattern(prarern);
        } catch (Exception e) {
            e.printStackTrace();
        }
            return format.parse(date);
    }
    public static String toString(Date date, String prarern){
            format.applyPattern(prarern);
            return format.format(date);
    }
    public static Date addDays(Date date, long days){
        date.setTime(date.getTime() + days*24*60*60*1000);
        return date;
    }

    public static String toString(String nam, String ddMMyyyy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

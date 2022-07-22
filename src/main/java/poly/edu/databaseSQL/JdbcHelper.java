/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.databaseSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author leminhthanh
 */
public class JdbcHelper {
    public static Connection openConnection() throws Exception{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=Polypro;", "sa", "reallyStrongPwd123");
    }
    public static PreparedStatement getStatement(String sql, Object...args) throws SQLException, Exception{
        Connection con = openConnection();
        PreparedStatement statement;
        if(sql.trim().startsWith("{")){
            statement =con.prepareCall(sql);
        }else{
            statement = con.prepareStatement(sql);
        }
        for (int i = 0; i < args.length; i++) {
            statement.setObject(i+1, args[i]);
        }
        return statement;   
    }
    public static ResultSet query(String sql, Object...args) throws SQLException, Exception{
        PreparedStatement statement = JdbcHelper.getStatement(sql, args);
        return statement.executeQuery();
    }
    public static Object value(String sql, Object...args){
        try {
            ResultSet resultSet = JdbcHelper.query(sql, args);
            if(resultSet.next()){
                return resultSet.getObject(0);
            }
            resultSet.getStatement().getConnection().close();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static int update(String sql, Object...args){
        try {
            PreparedStatement statement = JdbcHelper.getStatement(sql, args);
            try {
                return statement.executeUpdate();
            } finally{
                statement.getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

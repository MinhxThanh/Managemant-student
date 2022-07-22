/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import poly.edu.databaseSQL.JdbcHelper;
import poly.edu.utils.XDate;

/**
 *
 * @author leminhthanh
 */
public class ThongKeDAO {

    private List<Object[]> getListOfArray(String sql, String[] cols, Object... args) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet resultSet = JdbcHelper.query(sql, args);
            while (resultSet.next()) {
                Object[] vals = new Object[cols.length];
                for (int i = 0; i < cols.length; i++) {
                    vals[i] = resultSet.getObject(cols[i]);
                }
                list.add(vals);
            }
            resultSet.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        
    }
    
    public List<Object[]> getBangDiem(Integer makh) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                try {
                    String sql = "{call sp_BangDiem (?)}";
                    rs = JdbcHelper.query(sql, makh);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                while (rs.next()) {

                    Object[] model = {
                        rs.getString("MaNH"),
                        rs.getString("HoTen"),
                        rs.getDouble("Diem"),
                        getXepLoai(rs.getDouble("Diem"))
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
    
    
//    public List<Object[]> getBangDiem(Integer maKH){
//        String sql = "{CALL sp_BangDiem(?)}";
//        String[] cols = {"MaNH", "HoTen", "Diem"};
//        return this.getListOfArray(sql, cols, cols);
//    }
    public List<Object[]> getLuongNguoiHoc(){
        String sql = "{CALL sp_ThongKeNguoiHoc}";
        String[] cols = {"Nam", "SoLuong", "DauTien", "CuoiCung"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getDiemChuyenDe(){
        String sql = "{CALL sp_ThongKeDiem}";
        String[] cols = {"ChuyenDe", "SoHV", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }
    public List<Object[]> getDoanhThu1(int nam){
        String sql = "{CALL sp_ThongKeDoanhThu(?)}";
        String[] cols = {"ChuyenDe", "SoKH", "SoHV", "DoanhThu", "ThapNhat", "CaoNhat", "TrungBinh"};
        return this.getListOfArray(sql, cols);
    }
    
    public List<Object[]> getDoanhThu(int nam) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                try {
                    String sql = "{call sp_ThongKeDoanhThu (?)}";
                    rs = JdbcHelper.query(sql, nam);
                } catch (Exception ex) {
                    Logger.getLogger(ThongKeDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("SoKH"),
                        rs.getInt("SoHV"),
                        rs.getDouble("DoanhThu"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    
    private String getXepLoai(double diem){
        if(diem < 5){
            return "Chưa đạt";
        }
        if(diem < 6.5){
            return "Trung bình";
        }
        if(diem < 7.5){
            return "Khá";
        }
        if(diem < 9){
            return "Giỏi";
        }
        return "Xuất sắc";
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.dao;

import java.sql.ResultSet;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import poly.edu.databaseSQL.JdbcHelper;
import poly.edu.entity.NhanVien;

/**
 *
 * @author leminhthanh
 */
public class NhanVienDAO extends EduSysDAO<NhanVien, String>{
    
    private String insert_sql = "insert into NhanVien(MaNV, MatKhau, HoTen, VaiTro) values (?, ?, ?, ?)";
    private String update_sql = "update NhanVien set MatKhau = ?, HoTen = ?, VaiTro = ? where MaNV = ?";
    private String delete_sql = "delete from NhanVien where MaNV = ?";
    private String select_all_sql = "select * from NhanVien";
    private String select_By_ID_sql = "select * from NhanVien where MaNV = ?";
    
    @Override
    public void insert(NhanVien entity) {
        JdbcHelper.update(insert_sql, entity.getMaNV(), entity.getMatKhau(), entity.getHoTen(), entity.getVaiTro());
    }

    @Override
    public void update(NhanVien entity) {
        JdbcHelper.update(update_sql, entity.getMatKhau(), entity.getHoTen(), entity.getVaiTro(), entity.getMaNV());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(delete_sql, key);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySQL(select_all_sql);
    }

    @Override
    public NhanVien selectByID(String key) {
        List<NhanVien> list = this.selectBySQL(select_By_ID_sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
                
    }

    @Override
    protected List<NhanVien> selectBySQL(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<NhanVien>();
        try {
            ResultSet resultSet = JdbcHelper.query(sql, args);
            while (resultSet.next()) {                
                NhanVien nhanVien = new NhanVien();
                nhanVien.setMaNV(resultSet.getString("MaNV"));
                nhanVien.setMatKhau(resultSet.getString("MatKhau"));
                nhanVien.setHoTen(resultSet.getString("HoTen"));
                nhanVien.setVaiTro(resultSet.getBoolean("VaiTro"));
                list.add(nhanVien);
            }
            resultSet.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}

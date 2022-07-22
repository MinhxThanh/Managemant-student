/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.edu.databaseSQL.JdbcHelper;
import poly.edu.entity.HocVien;

/**
 *
 * @author leminhthanh
 */
public class HocVienDAO extends EduSysDAO<HocVien, String> {

    private String insert_sql = "insert into HocVien (MaKH, MaNH, Diem) values (?, ?, ?)";
    private String update_sql = "update HocVien set Diem = ? where MaHV = ?";
    private String delete_sql = "delete from HocVien where MaHV = ?";
    private String select_all_sql = "select * from HocVien";
    private String select_By_ID_sql = "select * from HocVien where MaHV = ?";

    @Override
    public void insert(HocVien entity) {
        JdbcHelper.update(insert_sql, entity.getMaKH(), entity.getMaNH(), entity.getDiem());
    }

    @Override
    public void update(HocVien entity) {
        JdbcHelper.update(update_sql, entity.getDiem(), entity.getMaHV());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(delete_sql, key);
    }

    @Override
    public List<HocVien> selectAll() {
        return this.selectBySQL(select_all_sql);

    }

    @Override
    public HocVien selectByID(String key) {
        List<HocVien> list = this.selectBySQL(select_By_ID_sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<HocVien> selectBySQL(String sql, Object... args) {
        List<HocVien> list = new ArrayList<HocVien>();
        try {
            ResultSet resultSet = JdbcHelper.query(sql, args);
            while (resultSet.next()) {
                HocVien hocVien = new HocVien();

                hocVien.setMaHV(resultSet.getInt("MaHV"));
                hocVien.setMaHV(resultSet.getInt("MaHV"));
                hocVien.setMaNH(resultSet.getString("MaNH"));
                hocVien.setDiem(resultSet.getFloat("Diem"));

                list.add(hocVien);
            }
            resultSet.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<HocVien> selectByKhoaHoc(int maKH) {
        String sql = "select * from HocVien where MaKH = ?";
        return this.selectBySQL(sql, maKH);
    }

    public void delete(int key) {
        JdbcHelper.update(delete_sql, key);
    }

    public HocVien selectByID(int key) {
        List<HocVien> list = this.selectBySQL(select_By_ID_sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public boolean updatediem(HocVien hv) throws Exception {
        try (
                Connection con = JdbcHelper.openConnection();
                PreparedStatement ps = con.prepareStatement(update_sql);) {
            ps.setFloat(1, hv.getDiem());
            ps.setInt(2, hv.getMaHV());

            return ps.executeUpdate() > 0;
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.edu.databaseSQL.JdbcHelper;
import poly.edu.entity.KhoaHoc;

/**
 *
 * @author leminhthanh
 */
public class KhoaHocDAO extends EduSysDAO<KhoaHoc, String> {

    private String insert_sql = "insert into KhoaHoc (MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV, NgayTao) values (?, ?, ?, ?, ?, ?, ?)";
    private String update_sql = "update KhoaHoc set MaCD = ?, HocPhi = ?, ThoiLuong = ?, NgayKG = ?, GhiChu = ?, MaNV = ? where MaKH = ?";
    private String delete_sql = "delete from KhoaHoc where MaKH = ?";
    private String select_all_sql = "select * from KhoaHoc";
    private String select_By_ID_sql = "select * from KhoaHoc where MaKH = ?";
    private String select_By_ChuyenDe = "select * from KhoaHoc where MaCD = ?";
    private String select_By_Years = "select distinct year(NgayKG) from KhoaHoc order by year(NgayKG) desc";

    @Override
    public void insert(KhoaHoc entity) {
        JdbcHelper.update(insert_sql, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayTao());
    }

    @Override
    public void update(KhoaHoc entity) {
        JdbcHelper.update(update_sql, entity.getMaCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getNgayKG(), entity.getGhiChu(), entity.getMaNV(), entity.getMaKH());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(delete_sql, key);
    }

    @Override
    public List<KhoaHoc> selectAll() {
        return this.selectBySQL(select_all_sql);
    }

    @Override
    public KhoaHoc selectByID(String key) {
        List<KhoaHoc> list = this.selectBySQL(select_By_ID_sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<KhoaHoc> selectBySQL(String sql, Object... args) {
        List<KhoaHoc> list = new ArrayList<KhoaHoc>();
        try {
            ResultSet resultSet = JdbcHelper.query(sql, args);
            while (resultSet.next()) {
                KhoaHoc khoaHoc = new KhoaHoc();

                khoaHoc.setMaKH(resultSet.getInt("MaKH"));
                khoaHoc.setMaCD(resultSet.getString("MaCD"));
                khoaHoc.setHocPhi(resultSet.getFloat("HocPhi"));
                khoaHoc.setThoiLuong(resultSet.getInt("ThoiLuong"));
                khoaHoc.setNgayKG(resultSet.getDate("NgayKG"));
                khoaHoc.setGhiChu(resultSet.getString("GhiChu"));
                khoaHoc.setMaNV(resultSet.getString("MaNV"));
                khoaHoc.setNgayTao(resultSet.getDate("NgayTao"));

                list.add(khoaHoc);
            }
            resultSet.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<KhoaHoc> selectByChuyenDe(String macd) {
        return this.selectBySQL(select_By_ChuyenDe, macd);
    }

    public KhoaHoc selectBy_ID(int key) {
        List<KhoaHoc> list = this.selectBySQL(select_By_ID_sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<Integer> selectYears() {
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.query("select distinct year(NgayKG) as nam from KhoaHoc order by year(NgayKG) desc");
                while (rs.next()) {
                    int nam = rs.getInt(1);
                    list.add(nam);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public void delete(int maKH) {
        JdbcHelper.update(delete_sql, maKH);

    }
}

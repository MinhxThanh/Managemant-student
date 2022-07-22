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
import poly.edu.entity.NguoiHoc;

/**
 *
 * @author leminhthanh
 */
public class NguoiHocDAO extends EduSysDAO<NguoiHoc, String> {

    private String insert_sql = "insert into NguoiHoc (MaNH, HoTen, NgaySinh, GioiTinh, DienThoai, Email, GhiChu, MaNV, NgayDK) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private String update_sql = "update NguoiHoc set HoTen = ?, NgaySinh = ?, GioiTinh = ?, DienThoai = ?, Email = ?, GhiChu = ?, MaNV = ? where MaNH = ?";
    private String delete_sql = "delete from NguoiHoc where MaNH = ?";
    private String select_all_sql = "select * from NguoiHoc";
    private String select_By_ID_sql = "select * from NguoiHoc where MaNH = ?";
    private String select_By_Name = "select * from NguoiHoc where HoTen like ?";

    @Override
    public void insert(NguoiHoc entity) {
        JdbcHelper.update(insert_sql, entity.getMaNH(), entity.getHoTen(), entity.getNgaySinh(), entity.getGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getNgayDK());
    }

    @Override
    public void update(NguoiHoc entity) {
        JdbcHelper.update(update_sql, entity.getHoTen(), entity.getNgaySinh(), entity.getGioiTinh(), entity.getDienThoai(), entity.getEmail(), entity.getGhiChu(), entity.getMaNV(), entity.getMaNH());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(delete_sql, key);
    }

    @Override
    public List<NguoiHoc> selectAll() {
        return this.selectBySQL(select_all_sql);
    }

    @Override
    public NguoiHoc selectByID(String key) {
        List<NguoiHoc> list = this.selectBySQL(select_By_ID_sql, key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<NguoiHoc> selectBySQL(String sql, Object... args) {
        List<NguoiHoc> list = new ArrayList<NguoiHoc>();
        try {
            ResultSet resultSet = JdbcHelper.query(sql, args);
            while (resultSet.next()) {
                NguoiHoc nguoiHoc = new NguoiHoc();

                nguoiHoc.setMaNH(resultSet.getString("MaNH"));
                nguoiHoc.setHoTen(resultSet.getString("HoTen"));
                nguoiHoc.setNgaySinh(resultSet.getDate("NgaySinh"));
                nguoiHoc.setGioiTinh(resultSet.getBoolean("GioiTinh"));
                nguoiHoc.setDienThoai(resultSet.getString("DienThoai"));
                nguoiHoc.setEmail(resultSet.getString("Email"));
                nguoiHoc.setGhiChu(resultSet.getString("GhiChu"));
                nguoiHoc.setMaNV(resultSet.getString("MaNV"));
                nguoiHoc.setNgayDK(resultSet.getDate("NgayDK"));

                list.add(nguoiHoc);
            }
            resultSet.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<NguoiHoc> selectByKeyWord(String keyWord){
        return this.selectBySQL(select_By_Name, "%" + keyWord + "%");
    }
    
    public List<NguoiHoc> selectNotInCourse(int maKH, String keyWord){
        String sql = "select * from NguoiHoc"
                + " where HoTen like ? and "
                + "MaNH not in (select MaNH from HocVien where MaKH = ?)";
        return this.selectBySQL(sql, "%" + keyWord + "%", maKH);
    }

}

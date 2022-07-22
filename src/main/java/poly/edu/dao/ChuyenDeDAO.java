/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.dao;

import java.sql.Blob;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import poly.edu.databaseSQL.JdbcHelper;
import poly.edu.entity.ChuyenDe;

/**
 *
 * @author leminhthanh
 */
public class ChuyenDeDAO extends EduSysDAO<ChuyenDe, String>{
    private String insert_sql = "insert into ChuyenDe(MaCD, TenCD, HocPhi, ThoiLuong, Hinh, MoTa) values (?, ?, ?, ?, ?, ?)";
    private String update_sql = "update ChuyenDe set TenCD = ?, HocPhi = ?, ThoiLuong = ?, Hinh = ?, MoTa = ? where MaCD = ?";
    private String delete_sql = "delete from ChuyenDe where MaCD = ?";
    private String select_all_sql = "select * from ChuyenDe";
    private String select_By_ID_sql = "select * from ChuyenDe where MaCD = ?";

    @Override
    public void insert(ChuyenDe entity) {
        JdbcHelper.update(insert_sql, entity.getMaCD(), entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa());
    }

    @Override
    public void update(ChuyenDe entity) {
        JdbcHelper.update(update_sql, entity.getTenCD(), entity.getHocPhi(), entity.getThoiLuong(), entity.getHinh(), entity.getMoTa(), entity.getMaCD());
    }

    @Override
    public void delete(String key) {
        JdbcHelper.update(delete_sql, key);
    }

    @Override
    public List<ChuyenDe> selectAll() {
        return this.selectBySQL(select_all_sql);
    }

    @Override
    public ChuyenDe selectByID(String key) {
        List<ChuyenDe> list = this.selectBySQL(select_By_ID_sql, key);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    protected List<ChuyenDe> selectBySQL(String sql, Object... args) {
        List<ChuyenDe> list = new ArrayList<ChuyenDe>();
        try {
            ResultSet resultSet = JdbcHelper.query(sql, args);
            while (resultSet.next()) {                
                ChuyenDe chuyenDe = new ChuyenDe();
                
                chuyenDe.setMaCD(resultSet.getString("MaCD"));
                chuyenDe.setTenCD(resultSet.getString("TenCD"));
                chuyenDe.setHocPhi(resultSet.getFloat("HocPhi"));
                chuyenDe.setThoiLuong(resultSet.getInt("ThoiLuong"));
                Blob blob = resultSet.getBlob("Hinh");
                if(blob != null){
                    chuyenDe.setHinh(blob.getBytes(1, (int) blob.length()));
                }
                chuyenDe.setMoTa(resultSet.getString("MoTa"));
                
                list.add(chuyenDe);
            }
            resultSet.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.entity;

/**
 *
 * @author leminhthanh
 */
public class ChuyenDe {
    private String maCD, tenCD, moTa;
    private float hocPhi;
    private int thoiLuong;
    private byte[] hinh;
    
    @Override
    public String toString(){
        return this.tenCD;
    }
    
    @Override 
    public boolean equals(Object obj){
        ChuyenDe other = (ChuyenDe) obj;
        return other.getMaCD().equals(this.getMaCD());
    }

    public ChuyenDe() {
    }

    public ChuyenDe(String maCD, String tenCD, String moTa, float hocPhi, int thoiLuong, byte[] hinh) {
        this.maCD = maCD;
        this.tenCD = tenCD;
        this.moTa = moTa;
        this.hocPhi = hocPhi;
        this.thoiLuong = thoiLuong;
        this.hinh = hinh;
    }

    public String getMaCD() {
        return maCD;
    }

    public void setMaCD(String maCD) {
        this.maCD = maCD;
    }

    public String getTenCD() {
        return tenCD;
    }

    public void setTenCD(String tenCD) {
        this.tenCD = tenCD;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public float getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(float hocPhi) {
        this.hocPhi = hocPhi;
    }

    public int getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(int thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
    
}

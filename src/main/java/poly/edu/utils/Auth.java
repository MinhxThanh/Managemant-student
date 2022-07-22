/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package poly.edu.utils;

import poly.edu.entity.ChuyenDe;
import poly.edu.entity.NhanVien;

/**
 *
 * @author leminhthanh
 */
public class Auth {
    public static NhanVien user = null;
    public static ChuyenDe chuyenDe = null;
    public static void clear(){
        Auth.user = null;
    }
    public static boolean isLogin(){
        return Auth.user != null;
    }
    public static boolean isManager(){
        return Auth.isLogin() && user.getVaiTro();
    }
}

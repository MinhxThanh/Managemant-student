package poly.edu;

import org.testng.annotations.Test;

import poly.edu.dao.NhanVienDAO;
import poly.edu.entity.NhanVien;
import poly.edu.utils.Auth;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class testChangePassword {

	private NhanVienDAO dao;
	private NhanVien nhanVien;

	private String username;
	private String passwordOld;
	private String password1;
	private String password2;

	@Test
	public void f() {
		try {
			if (!username.equalsIgnoreCase(Auth.user.getMaNV())) {
				System.out.println("Sai tên đăng nhập!");
			} else if (!passwordOld.equals(Auth.user.getMatKhau())) {
				System.out.println("Sai mật khẩu!");
			} else if (!password1.equals(password2)) {
				System.out.println("Mật khẩu không khớp!");
			}else {
				Auth.user.setMatKhau(password2);
				dao.update(Auth.user);
				System.out.println("Đổi mật khẩu thành công!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@AfterMethod
	public void AfterMethod() {
		passwordOld = "";
		password1 = "";
		password2 = "";
		System.out.println(1235);
	}

	@BeforeClass
	public void beforeTest() {
		username = "nv02";
		passwordOld = "123";
		password1 = "1234";
		password2 = "1234";

		dao = new NhanVienDAO();
		nhanVien = dao.selectByID(username);
		Auth.user = nhanVien;
		System.out.println(12);

	}

	@AfterClass
	public void afterTest() {
		username = "";
		
		dao = null;
		nhanVien = null;
		Auth.user = null;
		System.out.println(1);
	}

}

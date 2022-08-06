package poly.edu;

import org.testng.annotations.Test;

import poly.edu.dao.NhanVienDAO;
import poly.edu.entity.NhanVien;
import poly.edu.utils.Auth;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class testLogin {

	private NhanVienDAO dao;
	private NhanVien nhanVien;
	
	private String username;
	private String password;
	

	@Test
	public void f() {
		assertEquals(Login(), Auth.user = nhanVien); 
	}
	
	@BeforeMethod
	public NhanVien Login() {
	    String matKhau = password;
	    
	    if (nhanVien == null) {
	       System.out.println("Sai tên đăng nhập!");
	    } else if (!matKhau.equals(nhanVien.getMatKhau())) {
	        System.out.println("Sai mật khẩu!");
	    } 
	    return Auth.user = nhanVien;
	}

	@BeforeClass
	public void beforeTest() {
		username = "NV01";
		password = "123";
		
		dao = new NhanVienDAO();
	    nhanVien = dao.selectByID(username);
	}

	@AfterClass
	public void afterTest() {
		username = "";
		password = "";
		
		dao = null;
		nhanVien = null;
	}

}

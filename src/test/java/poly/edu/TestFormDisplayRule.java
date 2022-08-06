package poly.edu;



import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import poly.edu.dao.NhanVienDAO;
import poly.edu.entity.NhanVien;

public class TestFormDisplayRule {
	private NhanVienDAO dao;
	private NhanVien nhanVien;
	//Tạp đối tượng 
	@BeforeClass
	public void setUpBeforeClass() throws Exception {
		dao = new NhanVienDAO();
		nhanVien = null;
	}
	//Thực hiện đăng nhập với quyền nhân viên
	@Test
	public void test() {
		//Kiểm tra vai trò nhân viên có id = 'NV01'
		nhanVien = dao.selectByID("NV01");
		assertFalse(nhanVien.getVaiTro());
	}
	//Xoa các tham số chuyền vào
	@AfterClass
	public void setUpAffterClass() throws Exception {
		nhanVien = null;
	}
	//Tạp đối tượng 
	@BeforeClass
	public void setUpBeforeClass2() throws Exception {
		dao = new NhanVienDAO();
		nhanVien = null;
	}
	//Thực hiện đăng nhập với quyền quản trị
	@Test
	public void test2() {
		//Kiểm tra vai trò trưởng phòng có id = 'NV04'
		nhanVien = dao.selectByID("NV04");
		assertTrue(nhanVien.getVaiTro());
	}
	//Xoa các tham số chuyền vào
	@AfterClass
	public void setUpAffterClass2() throws Exception {
		nhanVien = null;
	}
}

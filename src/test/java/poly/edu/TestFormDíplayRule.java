package poly.edu;



import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import poly.edu.dao.NhanVienDAO;
import poly.edu.entity.NhanVien;

public class TestFormDíplayRule {
	private NhanVienDAO dao;
	private NhanVien nhanVien;
	//Tạp đối tượng 
	@Before
	public void setUpBeforeClass() throws Exception {
		dao = new NhanVienDAO();
		nhanVien = null;
	}
	//Thực hiện đăng nhập với quyền nhân viên
	@Test
	public void test() {
		//Kiểm tra vai trò nhân viên có id = 'NV01'
		nhanVien = dao.selectByID("NV01");
		assertEquals(nhanVien.getVaiTro(), false);
	}
	//Xoa các tham số chuyền vào
	@After
	public void setUpAffterClass() throws Exception {
		nhanVien = null;
	}
	//Tạp đối tượng 
	@Before
	public void setUpBeforeClass2() throws Exception {
		dao = new NhanVienDAO();
		nhanVien = null;
	}
	//Thực hiện đăng nhập với quyền quản trị
	@Test
	public void test2() {
		//Kiểm tra vai trò trưởng phòng có id = 'NV04'
		nhanVien = dao.selectByID("NV04");
		assertEquals(nhanVien.getVaiTro(), true);
	}
	//Xoa các tham số chuyền vào
	@After
	public void setUpAffterClass2() throws Exception {
		nhanVien = null;
	}
}

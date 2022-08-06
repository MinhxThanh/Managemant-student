package poly.edu;

import org.testng.annotations.Test;

import poly.edu.dao.NhanVienDAO;
import poly.edu.entity.NhanVien;
import poly.edu.utils.Auth;
import poly.edu.utils.MsgBox;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;

public class testDeleteemployee {

	private NhanVien nv;
	private NhanVienDAO dao;

	// data input
	private String idManager; //id manager login
	private String id; //id employee need delete

	@Test
	public void f() {
		try {
			if (!id.isEmpty()) {
				if (dao.selectByID(id) != null) {
					if (!Auth.isManager()) {
						System.out.println("Bạn không có quyền xóa nhân viên!");
					} else {
						if (id.equals(Auth.user.getMaNV())) {
							System.out.println("Bạn không thể xóa chính bạn!");
						} else {
							dao.delete(id);
						}
					}
				} else {
					System.out.println("Mã nhân viên không tồn tại!");
				}
			} else {
				System.out.println("Mã nhân viên không được để trống!");
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Xóa thất bại!");
			assertTrue(false);
		}
	}

	//login
	@BeforeMethod
	public void BeforeMethod() {
		dao = new NhanVienDAO();
		nv = dao.selectByID(idManager);
		Auth.user = nv;

	}

	// create
	@BeforeClass
	public void beforeClass() {
		nv = new NhanVien();
		dao = new NhanVienDAO();

		// input
		id = "nv05";
		idManager = "nv04";
	}

	// clear data
	@AfterClass
	public void afterClass() {
		nv = null;
		dao = null;

		id = null;
		idManager = null;
		
	}

}

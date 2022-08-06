package poly.edu;

import org.testng.annotations.Test;

import poly.edu.dao.NhanVienDAO;
import poly.edu.entity.NhanVien;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.AfterClass;

public class testUpdateemployee {

	private NhanVien nv;
	private NhanVienDAO dao;

	// data input
	private String id;
	private String fullname;
	private String password1;
	private String password2;

	@Test
	public void f() {
		try {
			if (id.isEmpty() || fullname.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
				System.out.println("Vui lòng nhập dữ liệu!!");
				return;
			} else {

				dao.update(nv);

				System.out.println("Sửa mới thành công!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Sửa mới thất bại!!");
			assertTrue(false);
		}
	}

	@BeforeMethod
	public void BeforeMethod() {
		nv.setMaNV(id);
		nv.setHoTen(fullname);
		nv.setMatKhau(password1);
		nv.setMatKhau(password2);
		//role is management
		nv.setVaiTro(true);

	}

	// create
	@BeforeClass
	public void beforeClass() {
		nv = new NhanVien();
		dao = new NhanVienDAO();

		// input
		id = "nv10";
		fullname = "Pham Van Hung";
		password1 = "12323";
		password2 = "12323";
	}

	// clear data
	@AfterClass
	public void afterClass() {
		nv = null;
		dao = null;

		id = null;
		fullname = null;
		password1 = null;
		password2 = null;
	}

}

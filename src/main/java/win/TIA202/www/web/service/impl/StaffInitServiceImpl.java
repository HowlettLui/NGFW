package win.TIA202.www.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import win.TIA202.www.web.dao.StaffInitDao;
import win.TIA202.www.web.entity.Staff;
import win.TIA202.www.web.service.StaffInitService;

@Service
@Transactional
public class StaffInitServiceImpl implements StaffInitService {
	@Autowired
	private StaffInitDao staffInitDao;

	@Override
	public String staffRegister(Staff staff) {
		String staffName = staff.getStaffName();
		String staffPassword = staff.getStaffPassword();

		if (staffInitDao.selectByStaffEmail(staff.getStaffEmail()) != null) {
			return "此電子信箱已被註冊";
		}
		
		if (staffName == null || staffName.length() < 0 || staffName.length() > 30) {
			return "使用者名稱長度請介於 0 ~ 30 之間";
		}

		if (staffPassword == null || staffPassword.length() < 6 || staffPassword.length() > 12) {
			return "密碼長度需介於 6 ~ 12 之間";
		}

		staff.setStaffRoleId(12);
		int resultConut = staffInitDao.staffInitInsert(staff);
		
		return resultConut > 0 ? null : "發生錯誤，請聯絡業務主管";
	}

	@Override
	public Staff staffLogin(Staff staff) {
		final String staffEmail = staff.getStaffEmail();
		final String staffPassword = staff.getStaffPassword();

		if (staffEmail == null) {
			staff.setMessage("使用者名稱未輸入");
			staff.setSuccessfully(false);
			return staff;
		}

		if (staffPassword == null) {
			staff.setMessage("密碼未輸入");
			staff.setSuccessfully(false);
			return staff;
		}

		staff = staffInitDao.staffSelectForLogin(staffEmail, staffPassword);
		if (staff == null) {
			staff = new Staff();
			staff.setMessage("使用者名稱或密碼錯誤");
			staff.setSuccessfully(false);
			return staff;
		}
		staff.setMessage("登入成功");
		staff.setSuccessfully(true);
		return staff;
	}

	@Override
	public List<Staff> findAllStaff() {
		return staffInitDao.selectAllUser();
	}

	@Override
	public Staff selectById(Integer id) {
		return staffInitDao.selectStaffById(id);
	}

	@Override
	public Staff editStaff(Staff staff) {
//		final Staff oStaff = staffInitDao.selectStaffById(staff.getStaffId());
		final int resultCount = staffInitDao.updateStaff(staff);
		staff.setSuccessfully(resultCount > 0);
		staff.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		return staff;
	}
}

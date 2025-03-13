package win.TIA202.www.web.service;

import java.util.List;

import win.TIA202.www.web.entity.Staff;

public interface StaffInitService {
	
	String staffRegister(Staff staff);
	
//	Staff staffLogin(Staff staff);

	Staff staffLogin(Staff staff);

	List<Staff> findAllStaff();
}

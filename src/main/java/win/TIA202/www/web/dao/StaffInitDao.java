package win.TIA202.www.web.dao;

import win.TIA202.www.web.entity.Staff;

public interface StaffInitDao {
	
	int staffInitInsert(Staff staff);
	
	Staff staffSelectForLogin(String staffEmail, String staffPassword);
	
	Staff selectByStaffEmail(String staffEmail);
 
}

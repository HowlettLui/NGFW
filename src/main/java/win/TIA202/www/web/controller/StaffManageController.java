package win.TIA202.www.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import win.TIA202.www.web.entity.Staff;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.StaffInitService;

@RestController
@RequestMapping("staff")
public class StaffManageController {
	@Autowired
	private StaffInitService service;

	@GetMapping("staff_list")
    public List<Staff> getStaffLists() {
        return service.findAllStaff();
    }
	
	@GetMapping("staff_list/{id}")
    public Staff getSingleStaff(@PathVariable Integer id) {
        Staff staff = service.selectById(id);
        if (staff == null) {
            Staff staffError = new Staff();
            staffError.setSuccessfully(false);
            staffError.setMessage("出現問題，請聯絡管理員");
            return staffError;
        }
        staff.setSuccessfully(true);
        staff.setMessage("已選取工作人員");
        return staff;
    }
	
	@PutMapping("staff_manage")
	public Staff editStaffInfo(@RequestBody Staff reqStaff, @SessionAttribute("staff") Staff seStaff) {
		final Integer staffId = seStaff.getStaffId();
		reqStaff.setStaffId(staffId);
		return service.editStaff(reqStaff);
	}

}

package win.TIA202.www.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import win.TIA202.www.web.entity.Staff;
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

}

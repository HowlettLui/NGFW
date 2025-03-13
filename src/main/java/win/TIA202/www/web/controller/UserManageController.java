package win.TIA202.www.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

@RestController
@RequestMapping("user")
public class UserManageController {
	@Autowired
	private UserService service;

	@GetMapping("user_list")
    public List<User> getLists() {
        return service.findAllUser();
    }


}

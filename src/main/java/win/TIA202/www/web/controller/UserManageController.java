package win.TIA202.www.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("user_list/{id}")
	public User getSingleUser(@PathVariable Integer id) {
		User user = service.selectById(id);
		if (user == null) {
			User userError = new User();
			userError.setSuccessfully(false);
			userError.setMessage("出現問題，請聯絡管理員");
			return userError;
		}
		user.setSuccessfully(true);
		user.setMessage("已選取人員");
		return user;
	}

	@PutMapping("manage")
	public User editUser(@RequestBody User reqUser) {
		User newUser = service.editUserSR(reqUser);
		newUser.setSuccessfully(true);
		newUser.setMessage("更新成功");
		return service.editUserSR(newUser);
	}
}

package win.TIA202.www.web.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import win.TIA202.www.core.pojo.CoreMsg;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

@RestController
@RequestMapping("user")
public class EditController {
	@Autowired
	private UserService service;

	@PutMapping("user_edit")
	public User edit(@RequestBody User reqUser, @SessionAttribute("user") User seUser) {
		final Integer userId = seUser.getUserId();
		reqUser.setUserId(userId);
		return service.edit(reqUser);
	}

	@GetMapping("user_pwd/{password}")
	public CoreMsg checkPassword(@PathVariable String password, @SessionAttribute("user") User seUser) {
		final CoreMsg core = new CoreMsg();
		if (seUser == null) {
			core.setMessage("無會員資訊");
			core.setSuccessfully(false);
		} else {
			final String currentPassword = seUser.getPassword();
			if (Objects.equals(password, currentPassword)) {
				core.setSuccessfully(true);
			} else {
				core.setMessage("舊密碼錯誤");
				core.setSuccessfully(false);
			}
		}
		return core;
	}
	
	@PutMapping("user_pwd")
	public User editPassword(@RequestBody User reqUser, @SessionAttribute("user") User seUser) {
		final Integer userId = seUser.getUserId();
		reqUser.setUserId(userId);
		return service.editPassword(reqUser);
	}

}

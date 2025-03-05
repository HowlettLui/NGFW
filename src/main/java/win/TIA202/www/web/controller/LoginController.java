package win.TIA202.www.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {
	@Autowired
	private UserService service;
	
	@GetMapping("{email}/{password}")
	@ResponseBody
	public User login(HttpServletRequest request, @PathVariable String email, @PathVariable String password) {
		
		User user = new User();
		if (email == null || password == null) {
			user.setMessage("無會員資訊");
			user.setSuccessfully(false);
			return user;
		}

		user.setEmail(email);
		user.setPassword(password);
		user = service.login(user);
		if (user.isSuccessfully()) {
			if (request.getSession(false) != null) {
				request.changeSessionId();
			}
			final HttpSession session = request.getSession();
			session.setAttribute("login", true);
			session.setAttribute("user", user);
		}
		return user;
	}
}

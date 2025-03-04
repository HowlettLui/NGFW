package win.TIA202.www.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

//@WebServlet("/register")
@RestController
@RequestMapping("register")
public class RegisterController {
	@Autowired
	private UserService service;
	
//	@Override
//	public void init() throws ServletException {
//		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//        service = context.getBean(UserService.class);
//	}
	
	@PostMapping
	@ResponseBody
	public User register(@RequestBody User user) {
		if (user == null) {
			user = new User();
			user.setMessage("無會員資訊");
			user.setSuccessfully(false);
			return user;
		}

		user = service.register(user);
		return user;
	}
	
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Gson gson = new Gson();
//		User user = gson.fromJson(req.getReader(), User.class);
//		
//		String errMsg = service.register(user);
//		
//		JsonObject respBody = new JsonObject();
//		respBody.addProperty("successfully", errMsg == null);
//		respBody.addProperty("errMsg", errMsg);
//		resp.getWriter().write(respBody.toString());
//	}
}

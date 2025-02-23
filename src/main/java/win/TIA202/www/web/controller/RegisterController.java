package win.TIA202.www.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;
import win.TIA202.www.web.service.impl.UserServiceImpl;

@WebServlet("/register")
//@Controller
//@RequestMapping("register")
public class RegisterController extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	//	@Autowired
	private UserService service;
	
	@Override
	public void init() throws ServletException {
		service = new UserServiceImpl();
	}
	
//	@PostMapping
//	@ResponseBody
//	public User register(@RequestBody User user) {
//		if (user == null) {
//			user = new User();
//			user.setMessage("無會員資訊");
//			user.setSuccessfully(false);
//			return user;
//		}
//
//		user = service.register(user);
//		return user;
//	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		User user = gson.fromJson(req.getReader(), User.class);
		
		String errMsg = service.register(user);
		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successfully", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.getWriter().write(respBody.toString());
	}
}

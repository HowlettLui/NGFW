package win.TIA202.www.web.controller;

import java.io.IOException;

import javax.naming.NamingException;
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
public class registerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service;
	
	@Override
	public void init() throws ServletException {
		try {
			service = new UserServiceImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String account = req.getParameter("account");
//		String email = req.getParameter("email");
//		String password = req.getParameter("password");
//		String comfirmPassword = req.getParameter("comfirmPassword");
//		User user = new User();
//		user.setAccount(account);
//		user.setEmail(email);
//		user.setPassword(password);
//		user.setConfirmPassword(comfirmPassword);
		
		Gson gson = new Gson();
		User user = gson.fromJson(req.getReader(), User.class);
		
		String errMsg = service.register(user);
		
		JsonObject respBody = new JsonObject();
		respBody.addProperty("successfully", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.getWriter().write(respBody.toString());
	}
}

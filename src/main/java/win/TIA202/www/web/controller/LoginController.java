package win.TIA202.www.web.controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import win.TIA202.www.core.pojo.CoreMsg;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;
import win.TIA202.www.web.service.impl.UserServiceImpl;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Gson gson = new Gson();
			User user = gson.fromJson(req.getReader(), User.class);
			UserService service;
			service = new UserServiceImpl();
			user = service.login(user);
			if (user != null) {
				if (req.getSession(false) != null) {
					req.changeSessionId(); 
				} 
				req.getSession().setAttribute("User", user);
			}
			CoreMsg coreMsg = new CoreMsg();
			coreMsg.setSuccessfully(user != null);
			resp.getWriter().write(gson.toJson(coreMsg));
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}

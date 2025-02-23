package win.TIA202.www.web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gson.Gson;

import win.TIA202.www.core.pojo.CoreMsg;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService service;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        service = context.getBean(UserService.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		User user = gson.fromJson(req.getReader(), User.class);
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
	}
}

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
import win.TIA202.www.web.entity.Staff;
import win.TIA202.www.web.service.StaffInitService;

@WebServlet("/staff_login")
public class StaffLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StaffInitService service;

	@Override
	public void init() throws ServletException {
		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        service = context.getBean(StaffInitService.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Gson gson = new Gson();
		Staff staff = gson.fromJson(req.getReader(), Staff.class);
		staff = service.staffLogin(staff);
		if (staff != null) {
			if (req.getSession(false) != null) {
				req.changeSessionId();
			}
			req.getSession().setAttribute("Staff", staff);
		}
		CoreMsg coreMsg = new CoreMsg();
		coreMsg.setSuccessfully(staff != null);
		resp.getWriter().write(gson.toJson(coreMsg));
	}
}

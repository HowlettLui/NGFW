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
import win.TIA202.www.web.entity.Staff;
import win.TIA202.www.web.service.StaffInitService;
import win.TIA202.www.web.service.impl.StaffInitServiceImpl;

@WebServlet("/staff_login")
public class StaffLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			Gson gson = new Gson();
			Staff staff = gson.fromJson(req.getReader(), Staff.class);
			StaffInitService service;
			service = new StaffInitServiceImpl();
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
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
}

package win.TIA202.www.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import win.TIA202.www.web.entity.Staff;
import win.TIA202.www.web.service.StaffInitService;

@RestController
@RequestMapping("staff_login")
public class StaffLoginController {
	@Autowired
	private StaffInitService service;

//	@Override
//	public void init() throws ServletException {
//		ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//        service = context.getBean(StaffInitService.class);
//	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		Gson gson = new Gson();
//		Staff staff = gson.fromJson(req.getReader(), Staff.class);
//		staff = service.staffLogin(staff);
//		if (staff != null) {
//			if (req.getSession(false) != null) {
//				req.changeSessionId();
//			}
//			req.getSession().setAttribute("Staff", staff);
//		}
//		CoreMsg coreMsg = new CoreMsg();
//		coreMsg.setSuccessfully(staff != null);
//		resp.getWriter().write(gson.toJson(coreMsg));
//	}
	
	@GetMapping("{email}/{password}")
	@ResponseBody
	public Staff login(HttpServletRequest request, @PathVariable String email, @PathVariable String password) {
		
		Staff staff = new Staff();
		if (email == null || password == null) {
			staff.setMessage("無工作人員資訊");
			staff.setSuccessfully(false);
			return staff;
		}

		staff.setStaffEmail(email);
		staff.setStaffPassword(password);
		staff = service.staffLogin(staff);
		if (staff.isSuccessfully()) {
			if (request.getSession(false) != null) {
//				request.changeSessionId();
			}
			final HttpSession session = request.getSession();
			session.setAttribute("login", true);
			session.setAttribute("staff", staff);
		}
		return staff;
	}
}

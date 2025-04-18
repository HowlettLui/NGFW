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
import com.google.gson.JsonObject;

import win.TIA202.www.web.entity.Staff;
import win.TIA202.www.web.service.StaffInitService;

@WebServlet("/staff_register")
public class StaffRegisterController extends HttpServlet {
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

		String errMsg = service.staffRegister(staff);

		JsonObject respBody = new JsonObject();
		respBody.addProperty("successfully", errMsg == null);
		respBody.addProperty("errMsg", errMsg);
		resp.getWriter().write(respBody.toString());
	}
}

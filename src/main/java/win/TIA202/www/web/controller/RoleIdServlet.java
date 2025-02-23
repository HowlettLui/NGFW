package win.TIA202.www.web.controller;

import win.TIA202.www.web.service.RoleService;
import win.TIA202.www.web.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/roleid")
public class RoleIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private RoleService service;

    @Override
    public void init() throws ServletException {
        try {
            this.service = new RoleServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int roleId = Integer.parseInt(req.getParameter("roleId"));

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        //
        if (roleId <= 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid roleId parameter\"}");
            return;
        }

        // 預期角色權限 = ture
        boolean exists = service.selectByRoleId(roleId);
        resp.getWriter().write("{\"exists\": " + exists + "}");

    }
}

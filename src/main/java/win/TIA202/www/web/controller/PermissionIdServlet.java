package win.TIA202.www.web.controller;

import win.TIA202.www.web.service.PermissionService;
import win.TIA202.www.web.service.impl.PermissionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/per_id")
public class PermissionIdServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PermissionService service;

    @Override
    public void init() throws ServletException {
        try {
            this.service = new PermissionServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int permissionId = Integer.parseInt(req.getParameter("permissionId"));

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 錯誤情境補充中
        if (permissionId <= 0) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Invalid permissionId parameter\"}");
            return;
        }

        // 預期角色權限 = ture
        boolean exists = service.selectByPermissionId(permissionId);
        resp.getWriter().write("{\"exists\": " + exists + "}");
    }
}

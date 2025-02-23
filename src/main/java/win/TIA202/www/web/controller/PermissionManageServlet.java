package win.TIA202.www.web.controller;

import com.google.gson.Gson;
import win.TIA202.www.web.entity.Permission;
import win.TIA202.www.web.service.PermissionService;
import win.TIA202.www.web.service.impl.PermissionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/member/per_manage")
public class PermissionManageServlet extends HttpServlet {
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
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        List<Permission> roles = service.findAll();
        Gson gson = new Gson();
        String json = gson.toJson(roles);
        resp.getWriter().write(json);
    }
}

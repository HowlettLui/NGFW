package win.TIA202.www.web.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import win.TIA202.www.web.entity.Role;
import win.TIA202.www.web.entity.RolePermission;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.RolePermissionService;
import win.TIA202.www.web.service.impl.PermissionServiceImpl;
import win.TIA202.www.web.service.impl.RolePermissionServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/member/roleRegister")
public class RolePerSetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RolePermissionService service;

    @Override
    public void init() throws ServletException {
        try {
            this.service = new RolePermissionServiceImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 設置回應類型為 JSON
        resp.setContentType("application/json");

        try {
            // 沒資料?
//            roleId: null
//            permissionId: null

//            int roleId = Integer.parseInt(req.getParameter("roleId"));
//            int permissionId = Integer.parseInt(req.getParameter("permissionId"));
            int roleId = 2;
            int permissionId =4;
            System.out.println("roleId: " + req.getParameter("roleId"));
            System.out.println("permissionId: " + req.getParameter("permissionId"));
            // 從請求中獲取參數

            // 創建 RolePermission 對象
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);

            // 更新 RolePermission
            int result = service.updateRolePermission(rolePermission);

            // 構建回應
            if (result > 0) {
                resp.getWriter().write("{\"message\": \"RolePermission updated successfully\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\": \"Failed to update RolePermission\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("{\"error\": \"An error occurred while updating RolePermission\"}");
            e.printStackTrace();
        }
    }
}


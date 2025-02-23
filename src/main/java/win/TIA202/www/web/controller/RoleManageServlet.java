package win.TIA202.www.web.controller;

import com.google.gson.Gson;
import win.TIA202.www.web.entity.Role;

import win.TIA202.www.web.service.RoleService;
import win.TIA202.www.web.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/member/manage")
public class RoleManageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private RoleService service;

    @Override
    public void init() throws ServletException {
        try {
            this.service = new RoleServiceImpl();
//            RoleDao roleDao = new RoleDaoImpl();
//            service = new RoleServiceImpl(roleDao);
//            System.out.println("RoleManageController 初始化成功");
        } catch (Exception e) {
//            System.err.println("初始化 RoleService 失敗：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 設置回應類型為 JSON, 忘記那邊有先寫，後續在整理
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

//        if (service == null) {
//            resp.getWriter().write("{\"error\": \"Service 未初始化\"}");
//            System.err.println("Service 未初始化，請檢查 init() 方法是否執行成功");
//            return;
//        }

        // postmen test
        List<Role> roles = service.findAll();
        Gson gson = new Gson();
        String json = gson.toJson(roles);
        resp.getWriter().write(json);
//        return null;
    }
}

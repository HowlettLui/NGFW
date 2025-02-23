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

//@RestController
//@RequestMapping("roles")
@WebServlet("/member/roletype")
public class RoleTypeServlet extends HttpServlet {
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
        String roleType = req.getParameter("roleType");

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        // 沒帶參數唷
        if (roleType == null || roleType.trim().isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\": \"Missing roleType parameter\"}");
            return;
        }

        // 預期角色權限 = ture
        boolean exists = service.selectByRoleType(roleType);
        resp.getWriter().write("{\"exists\": " + exists + "}");

    }
}

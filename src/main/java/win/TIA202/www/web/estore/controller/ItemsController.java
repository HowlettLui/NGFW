package win.TIA202.www.web.estore.controller;

import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/estore/shop")
public class ItemsController extends HttpServlet {
    private static final long serialVersionUID = -910197655064027068L;
    private ItemService service;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        service = context.getBean(ItemService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        List<Item> items = service.showAllOnShop();
//        resp.setContentType("application/json");
        resp.getWriter().println(gson.toJson(items));
    }
}

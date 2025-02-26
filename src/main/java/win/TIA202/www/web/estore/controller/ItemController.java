package win.TIA202.www.web.estore.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/estore/item/*")
public class ItemController extends HttpServlet {
    private static final long serialVersionUID = -2471659853468382855L;
    private ItemService service;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        service = context.getBean(ItemService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String itemId = req.getPathInfo().substring(1);

        Item item = service.pickById(Integer.valueOf(itemId));

        List<ItemInfo> itemInfoList = service.findInfoByItemId(Integer.valueOf(itemId));

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("item", gson.toJson(item));
        jsonObject.addProperty("itemInfo", gson.toJson(itemInfoList));

        resp.getWriter().write(gson.toJson(item));

    }
}

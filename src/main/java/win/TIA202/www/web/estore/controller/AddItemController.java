package win.TIA202.www.web.estore.controller;

import com.google.gson.Gson;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemFromAddReq;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.entity.ItemModel;
import win.TIA202.www.web.estore.service.ItemService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/estoreadmin/add")
public class AddItemController extends HttpServlet {
    private static final long serialVersionUID = -1981937929386601493L;
    private ItemService service;

    @Override
    public void init() throws ServletException {
        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
        service = context.getBean(ItemService.class);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Gson gson = new Gson();

        ItemFromAddReq itemFromAddReq = null;
        try {
            itemFromAddReq = gson.fromJson(req.getReader(), ItemFromAddReq.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ItemModel itemModel = itemFromAddReq.getItemModel();
        Item item = itemFromAddReq.getItem();
        ItemInfo itemInfo = itemFromAddReq.getItemInfo();

        try {
            resp.getWriter().write(service.addNewItem(itemModel, item, itemInfo));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

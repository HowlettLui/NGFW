package win.TIA202.www.web.estore.controller;

import javax.servlet.http.HttpServlet;

//@WebServlet("/estoreadmin/add")
public class BeforeMVCAddItemController extends HttpServlet {
    private static final long serialVersionUID = -1981937929386601493L;
//    private ItemService service;
//
//    @Override
//    public void init() throws ServletException {
//        ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
//        service = context.getBean(ItemService.class);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
//        Gson gson = new Gson();
//
//        ItemFromAddReq itemFromAddReq = null;
//        try {
//            itemFromAddReq = gson.fromJson(req.getReader(), ItemFromAddReq.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        ItemModel itemModel = itemFromAddReq.getItemModel();
//        Item item = itemFromAddReq.getItem();
//        ItemInfo itemInfo = itemFromAddReq.getItemInfo();
//
//        try {
//            resp.getWriter().write(service.addNewItem(itemModel, item, itemInfo));
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

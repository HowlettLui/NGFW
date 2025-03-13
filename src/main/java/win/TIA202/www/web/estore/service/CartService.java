package win.TIA202.www.web.estore.service;

import win.TIA202.www.web.estore.entity.Cart;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;

import java.util.List;

public interface CartService {

    List<Cart> getCartListByUserId(Integer userId);

    ItemInfo getItemInfoByItemInfoId(Integer itemInfoId);

    Item getItemByItemId(Integer itemId);
}

package win.TIA202.www.web.estore.service;

import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.entity.ItemModel;

import java.util.List;

public interface ItemService {

    Item showById(Integer id);

    List<Item> showAllOnShop();

    String addNewItem(ItemModel itemModel, Item item, ItemInfo itemInfo);
}

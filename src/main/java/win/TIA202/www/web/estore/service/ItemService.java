package win.TIA202.www.web.estore.service;

import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.entity.ItemModel;

import java.util.List;

public interface ItemService {

    Item pickById(Integer id);

    List<Item> showAllOnShop();

    ItemInfo addNewItem(ItemModel itemModel, Item item, ItemInfo itemInfo);

    List<ItemInfo> findInfoByItemId(Integer itemId);
}

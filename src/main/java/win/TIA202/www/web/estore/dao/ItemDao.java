package win.TIA202.www.web.estore.dao;

import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.entity.ItemModel;

import java.util.List;

public interface ItemDao {

    Item selectItemById(Integer id);

    ItemModel selectItemModelByModelName(String modelName);

    Item selectItemByItemName(String itemName);

    ItemInfo selectItemInfoBySizeAndColor(ItemInfo itemInfo);

    List<Item> selectAllOnShop();

    Integer addItemModel(ItemModel itemModel);

    Integer addItem(Item item);

    Integer addItemInfo(ItemInfo itemInfo);

    List<ItemInfo> selectItemInfoByItemId(Integer itemId);
}

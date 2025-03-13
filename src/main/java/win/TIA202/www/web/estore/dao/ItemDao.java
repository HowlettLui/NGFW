package win.TIA202.www.web.estore.dao;

import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.entity.ItemModel;

import java.util.List;

public interface ItemDao {

    Item selectItemById(Integer id);

    ItemModel selectItemModelByModelName(String modelName);

    List<ItemModel> selectAllItemModel();

    Item selectItemByItemName(String itemName);

    ItemInfo selectItemInfoBySizeAndColor(ItemInfo itemInfo);

    List<Item> selectAll();

    Integer addItemModel(ItemModel itemModel);

    Integer addItem(Item item);

    Integer addItemInfo(ItemInfo itemInfo);

    List<ItemInfo> selectItemInfosByItemId(Integer itemId);

    List<String> selectItemColorsByItemId(Integer itemId);

    ItemInfo selectItemInfoByItemInfoId(Integer itemInfoId);
}

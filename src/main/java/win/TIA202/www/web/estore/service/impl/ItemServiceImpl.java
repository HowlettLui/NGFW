package win.TIA202.www.web.estore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.TIA202.www.web.estore.dao.ItemDao;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.entity.ItemModel;
import win.TIA202.www.web.estore.service.ItemService;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDao dao;

    @Override
    public Item pickById(Integer id) {
        return dao.selectItemById(id);
    }

    @Override
    public List<Item> showAllOnShop() {
        return dao.selectAllOnShop();
    }

    @Override
    public String addNewItem(ItemModel itemModel, Item item, ItemInfo itemInfo) {
        boolean itemModelExist = dao.selectItemModelByModelName(itemModel.getItemModel()) != null;
        boolean itemExist = dao.selectItemByItemName(item.getItemName()) != null;
        boolean itemInfoExist = dao.selectItemInfoBySizeAndColor(itemInfo) != null;

        try {
            if (itemModelExist && itemExist && itemInfoExist) {
                return "該商品型號規格已存在，新增商品規格失敗";
            }

            itemModel.setStaffId(1);
            Integer itemModelId = dao.addItemModel(itemModel);

            item.setStaffId(1);
            item.setItemModelId(itemModelId);
            Integer itemId = dao.addItem(item);

            itemInfo.setItemId(itemId);
            itemInfo.setItemStatus("未上架");
            itemInfo.setStaffId(1);
            dao.addItemInfo(itemInfo);

            return "新增商品成功";
        } catch (Exception e) {
            return "新增商品失敗: " + e.getMessage();
        }
    }

    @Override
    public List<ItemInfo> findInfoByItemId(Integer itemId) {
        try {
            return dao.selectItemInfoByItemId(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

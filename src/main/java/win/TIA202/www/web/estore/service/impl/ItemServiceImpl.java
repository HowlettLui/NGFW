package win.TIA202.www.web.estore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.TIA202.www.web.estore.dao.ItemDao;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.entity.ItemModel;
import win.TIA202.www.web.estore.service.ItemService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

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
    public List<Item> showAll() {
        return dao.selectAll();
    }

    @Override
    public List<Item> showAllWithModelName() {
        List<Item> itemList = dao.selectAll();
        List<ItemModel> itemModelList = dao.selectAllItemModel();
        HashMap<Integer, String> itemModelHashMap = new HashMap<>();

        for (ItemModel itemModel : itemModelList) {
            itemModelHashMap.put(itemModel.getItemModelId(), itemModel.getItemModel());
        }

        for (Item item : itemList) {
            item.setItemModel(itemModelHashMap.get(item.getItemModelId()));
        }

        return itemList;
    }

    @Override
    public ItemInfo addNewItem(ItemModel itemModel, Item item, ItemInfo itemInfo) {
//      除了前端確認資料外，後端也再確認一次
        Map<? extends Supplier<?>, String> map = Map.of(
            itemModel::getItemModel, "未輸入商品型號",
            item::getItemName, "未輸入商品名稱",
            item::getItemContent, "未輸入商品簡介",
            item::getItemType, "未輸入商品類型",
            item::getItemPrice, "未輸入商品價格",
            item::getItemDetails, "未輸入商品詳細內容",
            item::getItemPhoto, "未上傳商品照片",
            itemInfo::getItemSize, "未輸入商品大小",
            itemInfo::getItemColor, "未輸入商品顏色",
            itemInfo::getItemStock, "未輸入商品數量"
        );

        for (Map.Entry<? extends Supplier<?>, String> entry : map.entrySet()) {
            if (entry.getKey().get() == null) {
                itemInfo.setMessage(entry.getValue());
                itemInfo.setResult(false);
                return itemInfo;
            }
        }

        boolean itemModelExist = dao.selectItemModelByModelName(itemModel.getItemModel()) != null;
        boolean itemExist = dao.selectItemByItemName(item.getItemName()) != null;
        boolean itemInfoExist = dao.selectItemInfoBySizeAndColor(itemInfo) != null;
        System.out.println(itemModelExist + " \n" + itemExist + " \n" + itemInfoExist);

        if (itemModelExist && itemExist && itemInfoExist) {
            itemInfo.setMessage("該商品型號規格已存在，新增商品規格失敗");
            itemInfo.setResult(false);
            return itemInfo;
        } else {
            try {
                itemModel.setStaffId(1);
                Integer itemModelId = dao.addItemModel(itemModel);

                item.setStaffId(1);
                item.setItemModelId(itemModelId);
                Integer itemId = dao.addItem(item);

                itemInfo.setItemId(itemId);
                itemInfo.setItemStatus(0);
                itemInfo.setStaffId(1);
                dao.addItemInfo(itemInfo);

                itemInfo.setMessage("新增商品成功");
                itemInfo.setResult(true);
                return itemInfo;
            } catch (Exception e) {
                itemInfo.setMessage("新增商品失敗: " + e.getMessage());
                itemInfo.setResult(false);
                return itemInfo;
            }
        }
    }

    //    取得單一商品的所有大小、顏色規格配對
    @Override
    public List<ItemInfo> findItemInfosByItemId(Integer itemId) {
        try {
            return dao.selectItemInfosByItemId(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<String> findColorsByItemId(Integer itemId) {
        try {
            return dao.selectItemColorsByItemId(itemId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

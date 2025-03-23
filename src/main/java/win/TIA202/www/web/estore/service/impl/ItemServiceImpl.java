package win.TIA202.www.web.estore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.TIA202.www.web.estore.dao.ItemDao;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemFromAdminEdit;
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
    public Integer pickPriceById(Integer id) {
        return dao.selectItemPriceById(id);
    }

    @Cacheable(value = "shop", cacheManager = "cacheManager")
    @Override
    public List<Item> showAll() {
        return dao.selectAll();
    }

    @Override
    public List<Item> showAllWithModelName() {
        List<Item> itemList = dao.selectAll();
        List<ItemModel> itemModelList = dao.selectAllItemModel();
        HashMap<Integer, ItemModel> itemModelHashMap = new HashMap<>();

        for (ItemModel itemModel : itemModelList) {
            itemModelHashMap.put(itemModel.getItemModelId(), itemModel);
        }

        for (Item item : itemList) {
            item.setItemModel(itemModelHashMap.get(item.getItemModelId()));
        }

        return itemList;
    }

    @Override
    public ItemInfo addNewItem(ItemModel itemModel, Item item, ItemInfo itemInfo) {
        ItemModel itemModelFromDB = dao.selectItemModelByModelName(itemModel.getItemModel());
        Item itemFromDB = dao.selectItemByItemName(item.getItemName());

        // 除了前端確認資料外，後端也再確認一次
        Map<? extends Supplier<?>, String> mapGen = Map.of(
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

        for (Map.Entry<? extends Supplier<?>, String> entry : mapGen.entrySet()) {
            if ((entry.getKey().get() == null) && itemFromDB == null) {
                itemInfo.setMessage(entry.getValue());
                itemInfo.setResult(false);
                return itemInfo;
            }
        }

        Integer itemModelId;
        Integer itemId;

        try {
            if (itemModelFromDB == null) {
                itemModelId = dao.addItemModel(itemModel);
                item.setItemModelId(itemModelId);
                itemId = dao.addItem(item);
            } else if (itemFromDB == null) {
                itemModelId = itemModelFromDB.getItemModelId();
                item.setItemModelId(itemModelId);
                itemId = dao.addItem(item);
            } else {
                itemId = itemFromDB.getItemId();
                itemInfo.setItemId(itemId);
                if (dao.selectItemInfoByItemInfoIdAndSizeAndColor(itemInfo) != null) {
                    itemInfo.setMessage("該商品型號規格已存在，新增商品規格失敗");
                    itemInfo.setResult(false);
                    return itemInfo;
                }
            }
            itemInfo.setItemId(itemId);
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

    @Override
    public ItemInfo findItemInfoByIdForEdit(Integer itemInfoId) {
        ItemInfo itemInfo = dao.selectItemInfoByItemInfoId(itemInfoId);
        if (itemInfo == null) {
            ItemInfo itemInfoFailed = new ItemInfo();
            itemInfoFailed.setResult(false);
            itemInfoFailed.setMessage("取得商品資料失敗");
            return itemInfoFailed;
        } else {
            itemInfo.setResult(true);
            itemInfo.setMessage("成功取得商品資料");
            return itemInfo;
        }
    }

    @CacheEvict(value = "iteminfo", key = "#newItemInfo.itemInfoId", cacheManager = "cacheManager")
    @Override
    public ItemInfo editItemInfo(ItemInfo newItemInfo) {

        // 除了前端確認資料外，後端也再確認一次
        Map<? extends Supplier<?>, String> map = Map.of(
            newItemInfo::getItemSize, "未輸入商品大小",
            newItemInfo::getItemColor, "未輸入商品顏色",
            newItemInfo::getItemStock, "未輸入商品數量"
        );

        for (Map.Entry<? extends Supplier<?>, String> entry : map.entrySet()) {
            if (entry.getKey().get() == null) {
                newItemInfo.setMessage(entry.getValue());
                newItemInfo.setResult(false);
                return newItemInfo;
            }
        }

        ItemInfo oldItemInfo = dao.selectItemInfoByItemInfoId(newItemInfo.getItemInfoId());
        if (oldItemInfo.isResult()) {
            newItemInfo.setItemId(oldItemInfo.getItemId());
            newItemInfo.setCreateTime(oldItemInfo.getCreateTime());
            ItemInfo updated = dao.editItemInfo(newItemInfo);
            if (updated.getItemInfoId() != oldItemInfo.getItemInfoId()) {
                newItemInfo.setResult(false);
                newItemInfo.setMessage("更新商品資料失敗");
                return newItemInfo;
            } else {
                updated.setMessage("更新成功");
                return updated;
            }
        } else {
            oldItemInfo.setResult(false);
            oldItemInfo.setMessage("取得原商品資料失敗");
            return oldItemInfo;
        }
    }

    @Override
    public Item findItemByIdForEdit(Integer itemId) {
        List<Object[]> list = dao.selectItemAndModelByIdForEdit(itemId);
        Item item = null;
        for (Object[] row : list) {
            item = (Item) row[0];
            item.setItemModel((ItemModel) row[1]);
        }
        if (item == null) {
            Item itemFailed = new Item();
            itemFailed.setResult(false);
            itemFailed.setMessage("取得商品資料失敗");
            return itemFailed;
        } else {
            item.setResult(true);
            item.setMessage("成功取得商品資料");
            return item;
        }
    }

    @Override
    public Item editItemAndModel(ItemFromAdminEdit itemFromAdminEdit) {
        ItemModel newItemModel = itemFromAdminEdit.getItemModel();
        Item newItem = itemFromAdminEdit.getItem();
        // 除了前端確認資料外，後端也再確認一次
        Map<? extends Supplier<?>, String> map = Map.of(
            newItemModel::getItemModel, "未輸入商品型號",
            newItem::getItemName, "未輸入商品名稱",
            newItem::getItemContent, "未輸入商品簡介",
            newItem::getItemType, "未輸入商品類型",
            newItem::getItemPrice, "未輸入商品價格",
            newItem::getItemDetails, "未輸入商品詳細內容"
//            newItem::getItemPhoto, "未上傳商品照片" // todo: Uppy可以取得原本的資料可編輯之後放回來
        );

        for (Map.Entry<? extends Supplier<?>, String> entry : map.entrySet()) {
            if (entry.getKey().get() == null) {
                newItem.setMessage(entry.getValue());
                newItem.setResult(false);
                return newItem;
            }
        }

        Item oldItem = dao.selectItemById(newItem.getItemId());
        ItemModel oldItemModel = dao.selectItemModelById(newItem.getItemModelId());

        if (oldItemModel.isResult() && oldItem.isResult()) {
            newItemModel.setCreateDate(oldItemModel.getCreateDate());
            newItem.setCreateTime(oldItem.getCreateTime());
            newItem.setItemPhoto(oldItem.getItemPhoto()); // todo: Uppy可以取得原本的資料可編輯之後拿掉
            ItemModel updatedModel = dao.editItemModel(newItemModel);
            Item updatedItem = dao.editItem(newItem);
            if ((updatedModel.getItemModelId() != oldItemModel.getItemModelId()) || (updatedItem.getItemId() != oldItem.getItemId())) {
                newItem.setResult(false);
                newItem.setMessage("更新商品資料失敗");
                return newItem;
            } else {
                updatedItem.setMessage("更新成功");
                return updatedItem;
            }
        } else {
            oldItem.setResult(false);
            oldItem.setMessage("取得原商品資料失敗");
            return oldItem;
        }
    }

    @CacheEvict(value = "iteminfo", key = "#itemInfoId", cacheManager = "cacheManager")
    @Override
    public ItemInfo updateListItemInfo(Integer itemStatus, Integer itemInfoId) {
        Integer result = dao.updateListItemInfo(itemStatus, itemInfoId);
        if (result == 0) {
            ItemInfo itemInfoFailed = new ItemInfo();
            itemInfoFailed.setResult(false);
            itemInfoFailed.setMessage("上下架狀態更新失敗");
            return itemInfoFailed;
        } else {
            ItemInfo itemInfo = new ItemInfo();
            itemInfo.setResult(true);
            itemInfo.setMessage("更新上下架狀態成功");
            return itemInfo;
        }
    }

    @Override
    public Boolean checkItemExist(String itemName, String itemModelName) {
        Item item = dao.selectItemByItemName(itemName);
        ItemModel itemModel = dao.selectItemModelByModelName(itemModelName);
        return (item != null) && (itemModel != null);
    }
}

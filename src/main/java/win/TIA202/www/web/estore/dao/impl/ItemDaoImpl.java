package win.TIA202.www.web.estore.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import win.TIA202.www.web.estore.dao.ItemDao;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.entity.ItemModel;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemDaoImpl implements ItemDao {

    @PersistenceContext
    private Session session;

    @Override
    public Item selectItemById(Integer itemId) {
        Item item = session.get(Item.class, itemId);

        if (item == null) {
            Item itemFailed = new Item();
            itemFailed.setResult(false);
            itemFailed.setMessage("取得商品資訊失敗，請聯絡管理人員");
            return itemFailed;
        } else {
            item.setResult(true);
            item.setMessage("成功取得商品資訊");
            return item;
        }
    }

    @Override
    public Integer selectItemPriceById(Integer id) {
        String hql = "select itemPrice from Item where itemId = :itemId";
        Query<Integer> query = session.createQuery(hql, Integer.class);
        return query.setParameter("itemId", id).uniqueResult();
    }

    @Override
    public ItemModel selectItemModelByModelName(String modelName) {
        String hql = "from ItemModel where itemModel = :modelName";
        Query<ItemModel> query = session.createQuery(hql, ItemModel.class);
        query.setParameter("modelName", modelName);

        return query.uniqueResult();
    }

    @Override
    public List<ItemModel> selectAllItemModel() {
        String hql = "from ItemModel";
        Query<ItemModel> query = session.createQuery(hql, ItemModel.class);
        return query.getResultList();
    }

    @Override
    public Item selectItemByItemName(String itemName) {
        String hql = "from Item where itemName = :itemName";
        Query<Item> query = session.createQuery(hql, Item.class);
        query.setParameter("itemName", itemName);

        return query.uniqueResult();
    }

    @Override
    public ItemInfo selectItemInfoByItemInfoIdAndSizeAndColor(ItemInfo itemInfo) {
        String hql = "from ItemInfo where itemId = :itemId and itemSize = :itemSize and itemColor = :color";
        Query<ItemInfo> query = session.createQuery(hql, ItemInfo.class);
        query.setParameter("itemId", itemInfo.getItemId());
        query.setParameter("itemSize", itemInfo.getItemSize());
        query.setParameter("color", itemInfo.getItemColor());

        return query.uniqueResult();
    }

    @Override
    public List<Item> selectAll() {
//        String hql = "SELECT itemId, itemName, itemType, itemPhoto, itemModelId, itemPrice FROM Item";
//        String hql = "SELECT new win.TIA202.www.web.estore.entity.Item(itemId, itemName, itemType, itemPhoto, itemModelId, itemPrice) FROM Item";
        String hql = "FROM Item";
        Query<Item> query = session.createQuery(hql, Item.class);
        return query.getResultList();
    }

    @Override
    public Integer addItemModel(ItemModel itemModel) {
        session.persist(itemModel);
        return itemModel.getItemModelId();
    }

    @Override
    public Integer addItem(Item item) {
        session.persist(item);
        return item.getItemId();
    }

    @Override
    public Integer addItemInfo(ItemInfo itemInfo) {
        session.persist(itemInfo);
        return itemInfo.getItemInfoId();
    }

    @Override
    public List<ItemInfo> selectItemInfosByItemId(Integer itemId) {
        String hql = "from ItemInfo where itemId = :itemId";
        Query<ItemInfo> query = session.createQuery(hql, ItemInfo.class);
        query.setParameter("itemId", itemId);
        return query.getResultList();
    }

    @Override
    public List<String> selectItemColorsByItemId(Integer itemId) {
        String hql = "select distinct itemColor from ItemInfo where itemId = :itemId";
        Query<String> query = session.createQuery(hql, String.class);
        query.setParameter("itemId", itemId);
        return query.getResultList();
    }

    @Override
    public ItemInfo selectItemInfoByItemInfoId(Integer itemInfoId) {
        ItemInfo itemInfo = session.get(ItemInfo.class, itemInfoId);

        if (itemInfo == null) {
            ItemInfo itemInfoFailed = new ItemInfo();
            itemInfoFailed.setResult(false);
            itemInfoFailed.setMessage("取得商品資訊失敗，請聯絡管理人員");
            return itemInfoFailed;
        } else {
            itemInfo.setResult(true);
            itemInfo.setMessage("成功取得商品資料");
            return itemInfo;
        }
    }

    @Override
    public ItemModel selectItemModelById(Integer itemModelId) {
        ItemModel itemModel = session.get(ItemModel.class, itemModelId);

        if (itemModel == null) {
            ItemModel itemModelFailed = new ItemModel();
            itemModelFailed.setResult(false);
            itemModelFailed.setMessage("取得商品資訊失敗，請聯絡管理人員");
            return itemModelFailed;
        } else {
            itemModel.setResult(true);
            itemModel.setMessage("成功取得商品資料");
            return itemModel;
        }
    }

    @Override
    public ItemInfo editItemInfo(ItemInfo itemInfo) {
        return (ItemInfo) session.merge(itemInfo);
    }

    @Override
    public Item editItem(Item item) {
        return (Item) session.merge(item);
    }

    @Override
    public ItemModel editItemModel(ItemModel itemModel) {
        return (ItemModel) session.merge(itemModel);
    }

    @Override
    public List<Object[]> selectItemAndModelByIdForEdit(Integer itemId) {
        String hql = "SELECT i, m FROM Item i JOIN ItemModel m ON i.itemModelId = m.itemModelId WHERE i.itemId = :itemId";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("itemId", itemId);
        return query.getResultList();
    }

    @Override
    public Integer updateListItemInfo(Integer itemStatus, Integer itemInfoId) {
        String hql = "UPDATE ItemInfo SET itemStatus = :itemStatus where itemInfoId = :itemInfoId";
        return session.createQuery(hql)
            .setParameter("itemStatus", itemStatus)
            .setParameter("itemInfoId", itemInfoId)
            .executeUpdate();
    }
}

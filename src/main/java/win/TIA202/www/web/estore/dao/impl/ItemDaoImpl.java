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
        String hql = "FROM Item";
        Query<Item> query = session.createQuery(hql, Item.class);
        return query.getResultList();
    }

    @Override
    public List<Item> selectForOnePage(Integer page, Integer pageSize) {
        return session.createQuery("FROM Item", Item.class)
            .setFirstResult(page * pageSize)
            .setMaxResults(pageSize)
            .getResultList();
    }

    @Override
    public Long countItems() {
        return session.createQuery("SELECT COUNT(i) FROM Item i", Long.class).uniqueResult();
    }

    @Override
    public List<Item> selectByKeyword(String keyword) {
        String[] keywords = keyword.split("");
        String hql = "FROM Item WHERE ";
        StringBuilder conditionBuilder = new StringBuilder();

        for (int i = 0; i < keywords.length; i++) {
            if (i > 0) {
                conditionBuilder.append(" AND "); // 條件之間使用 AND
            }
            conditionBuilder.append(" (itemName LIKE :keyword").append(i).append(" OR itemContent LIKE :keyword").append(i).append(" OR itemType LIKE :keyword").append(i).append(" OR itemDetails LIKE :keyword").append(i).append(")");
        }

        hql += conditionBuilder.toString();
        Query<Item> query = session.createQuery(hql, Item.class);

        for (int i = 0; i < keywords.length; i++) {
            query.setParameter("keyword" + i, "%" + keywords[i] + "%");
        }
        return query.getResultList();
    }

    @Override
    public List<Item> selectRecommendByType(String itemType) {
        String sql = "SELECT * FROM item WHERE item_type = :itemType ORDER BY RAND() LIMIT 3";
        Query<Item> nativeQuery = session.createNativeQuery(sql, Item.class);
        nativeQuery.setParameter("itemType", itemType);
        return nativeQuery.getResultList();
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

    @Override
    public Integer selectItemCountByItemInfoId(Integer itemInfoId) {
        String hql = "SELECT itemStock FROM ItemInfo WHERE itemInfoId = :itemInfoId";
        Query<Integer> query = session.createQuery(hql, Integer.class);
        query.setParameter("itemInfoId", itemInfoId);
        return query.uniqueResult();
    }

    @Override
    public Integer editItemCountByItemInfoId(Integer itemTotalCount, Integer itemInfoId) {
        String hql = "UPDATE ItemInfo SET itemStock = :itemTotalCount where itemInfoId = :itemInfoId";
        return session.createQuery(hql)
            .setParameter("itemTotalCount", itemTotalCount)
            .setParameter("itemInfoId", itemInfoId)
            .executeUpdate();
    }

    @Override
    public Integer editItemStockBackByItemInfoId(Integer itemCountInReceipt, Integer itemInfoId) {
        String hql = "UPDATE ItemInfo SET itemStock = itemStock + :itemCountInReceipt where itemInfoId = :itemInfoId";
        return session.createQuery(hql)
            .setParameter("itemCountInReceipt", itemCountInReceipt)
            .setParameter("itemInfoId", itemInfoId)
            .executeUpdate();
    }


}

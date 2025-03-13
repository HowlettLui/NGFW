package win.TIA202.www.web.estore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.TIA202.www.web.estore.dao.CartDao;
import win.TIA202.www.web.estore.dao.ItemDao;
import win.TIA202.www.web.estore.entity.Cart;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.service.CartService;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private ItemDao itemDao;

//    @Autowired
//    private ValueOperations<String, Object> valueOperations;

    @Cacheable(value = "cart", key = "#userId", cacheManager = "cacheManager")
    @Override
    public List<Cart> getCartListByUserId(Integer userId) {
        return cartDao.selectByUserId(userId);
    }

    @Cacheable(value = "iteminfo", key = "#itemInfoId", cacheManager = "cacheManager")
    @Override
    public ItemInfo getItemInfoByItemInfoId(Integer itemInfoId) {
        return itemDao.selectItemInfoByItemInfoId(itemInfoId);
    }

    @Cacheable(value = "item", key = "#itemId", cacheManager = "cacheManager")
    @Override
    public Item getItemByItemId(Integer itemId) {
        return itemDao.selectItemById(itemId);
    }

}

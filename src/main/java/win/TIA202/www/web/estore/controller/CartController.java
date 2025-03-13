package win.TIA202.www.web.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import win.TIA202.www.web.estore.entity.Cart;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.service.CartService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("estore/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("{userId}")
    public List<ItemInfo> getItemInfosInCart(@PathVariable Integer userId) {
        List<Cart> cartList = service.getCartListByUserId(userId);

        List<ItemInfo> itemInfos = new ArrayList<>();
        for (Cart cart : cartList) {
            Integer itemInfoId = cart.getItemInfoId();
            Integer itemCountsInCart = cart.getDetailItemcount();

            ItemInfo itemInfo = service.getItemInfoByItemInfoId(itemInfoId);
            Item item = service.getItemByItemId(itemInfo.getItemId());

            itemInfo.setItemName(item.getItemName());
            itemInfo.setItemCountsInCart(itemCountsInCart);
            itemInfo.setItemPriceInCart(item.getItemPrice());
            itemInfo.setItemPhotoInCart(item.getItemPhoto());

            itemInfos.add(itemInfo);
        }

        return itemInfos;
    }

    //    todo: 點選新增至購物車之後的動作 (API)
    @PostMapping("addToCart/{userId}/{itemInfoId}")
    public boolean addToCart(@PathVariable Integer userId, @PathVariable Integer itemInfoId) {
        return true;
    }

    //    todo: 更新購物車之後的動作 (API)
    @PutMapping("editCart/{userId}/{itemInfoId}")
    public boolean editCart(@PathVariable Integer userId, @PathVariable Integer itemInfoId) {
        return true;
    }

    //    todo: 刪除購物車商品之後的動作
    @DeleteMapping("delete/{userId}/{itemInfoId}")
    public boolean deleteCart(@PathVariable Integer userId, @PathVariable Integer itemInfoId) {
        return true;
    }
}

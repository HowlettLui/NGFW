package win.TIA202.www.web.estore.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import win.TIA202.www.web.estore.entity.Cart;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.service.CartService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("estore/cart")
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("{userId}")
    public List<? extends Serializable> getItemInfosInCart(@PathVariable Integer userId) {
        List<Cart> cartList = service.getCartListByUserId(userId);

        if (cartList.isEmpty()) {
            return new ArrayList<ObjectNode>();
        } else {
            List<ItemInfo> itemInfos = new ArrayList<>();
            for (Cart cart : cartList) {
                Integer itemInfoId = cart.getItemInfoId();
                Integer itemCountsInCart = cart.getDetailItemCount();

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
    }

    // 更新購物車之後的動作
    @PutMapping("editCart/{userId}/{itemInfoId}")
    public Cart editCart(@RequestBody Cart newCart, @PathVariable Integer userId, @PathVariable Integer itemInfoId) {
        return service.updateCartDetail(newCart, userId, itemInfoId);
    }

    // 刪除購物車商品之後的動作
    @DeleteMapping("delete/{userId}/{itemInfoId}")
    public Integer deleteCart(@PathVariable Integer userId, @PathVariable Integer itemInfoId) {
        return service.deleteCartByUserIdAndItemInfoId(userId, itemInfoId);
    }

    // 點選新增至購物車之後的動作
    @PostMapping("addToCart")
    public Cart addToCart(@RequestBody Cart newCart) {
        return service.addItemInfoToCart(newCart);
    }
}

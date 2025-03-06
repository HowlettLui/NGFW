package win.TIA202.www.web.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("estore/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping("{id}")
    public Item getItem(@PathVariable Integer id) {

        Item item = service.pickById(id);

        if (item == null) {
            Item itemFailed = new Item();
            return itemFailed;
        }

//        取得單一商品所有顏色
        List<String> colorsList = service.findColorsByItemId(id);

//        取得單一商品的所有大小、顏色規格配對
        List<ItemInfo> itemInfosList = service.findItemInfosByItemId(id);

        item.setItemInfos(itemInfosList);
        item.setItemColors(colorsList);

        return item;
    }
}

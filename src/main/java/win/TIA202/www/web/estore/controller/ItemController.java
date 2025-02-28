package win.TIA202.www.web.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.service.ItemService;

@RestController
@RequestMapping("estore/item")
public class ItemController {

    @Autowired
    private ItemService service;

    @GetMapping("{id}")
    public Item getItem(@PathVariable Integer id) {
        Item item = service.pickById(id);

//        取得各個商品的所有大小、顏色
//        List<ItemInfo> itemInfoList = service.findInfoByItemId(id);

        return item;
    }
}

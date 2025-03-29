package win.TIA202.www.web.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.PageResponse;
import win.TIA202.www.web.estore.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("estore/shop")
public class ItemsController {

    @Autowired
    private ItemService service;

    @GetMapping
    public List<Item> getItems() {
        return service.showAll();
    }

    @GetMapping("items")
    public PageResponse<Item> getItemsByPage(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "6") Integer pageSize) {
        return service.showOnOnePage(page, pageSize);
    }

    @GetMapping("recommend/{itemType}")
    public List<Item> getRecommendItems(@PathVariable String itemType) {
        return service.showRecommend(itemType);
    }

}

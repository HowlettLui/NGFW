package win.TIA202.www.web.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.service.ItemService;

import java.util.List;

@RestController
@RequestMapping("estore/shop")
public class ItemsController {

    @Autowired
    private ItemService service;

    @GetMapping
    public List<Item> getItems() {
        return service.showAllOnShop();
    }
}

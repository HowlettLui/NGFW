package win.TIA202.www.web.estore.controller;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import win.TIA202.www.web.estore.entity.Item;
import win.TIA202.www.web.estore.entity.ItemFromAddReq;
import win.TIA202.www.web.estore.entity.ItemInfo;
import win.TIA202.www.web.estore.entity.ItemModel;
import win.TIA202.www.web.estore.service.ItemService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("estoreadmin")
public class AdminItemController {

    @Value("#{systemProperties['catalina.home']}\\files\\")
    private String fileRootPath;

    @Autowired
    private ItemService service;

    @PostMapping("add")
    public ItemInfo addItem(@RequestBody ItemFromAddReq itemFromAddReq) {
        ItemModel itemModel = itemFromAddReq.getItemModel();
        Item item = itemFromAddReq.getItem();
        ItemInfo itemInfo = itemFromAddReq.getItemInfo();

        return service.addNewItem(itemModel, item, itemInfo);
    }

    @PostMapping("upload")
    public ObjectNode uploadPhotos(@RequestParam("item_photo") MultipartFile[] files) throws IOException {

        JsonNodeFactory factory = JsonNodeFactory.instance;
        ObjectNode objectNode = factory.objectNode();
        ArrayNode photoLocations = objectNode.putArray("photos");
        for (MultipartFile file : files) {
            String fileName = UUID.randomUUID().toString() + "." + file.getContentType().split("/")[1];
            String photoSaveLocation = fileRootPath + fileName;
            String photoLocation = "/files/" + fileName;
            photoLocations.add(photoLocation);
            file.transferTo(Paths.get(photoSaveLocation));
        }

        return objectNode;
    }

    @GetMapping("items")
    public List<Item> getItems() {
        return service.showAllWithModelName();
    }

    @GetMapping("items/{id}")
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

//    todo: 商品編輯行為的API
//    @PutMapping("items/{id}")
//    public Item updateItem(@PathVariable Integer id, @RequestBody ItemFromAddReq itemFromAddReq) {}

}

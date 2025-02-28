package win.TIA202.www.web.estore.controller;

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
import java.util.UUID;

@RestController
@RequestMapping("estoreadmin")
public class AddItemController {

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

        for (MultipartFile file : files) {
            String photoLocation = fileRootPath + UUID.randomUUID().toString() + "." + file.getContentType().split("/")[1];
            objectNode.put(file.getOriginalFilename(), photoLocation);
            file.transferTo(Paths.get(photoLocation));
        }
        return objectNode;
    }
}

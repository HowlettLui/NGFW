package win.TIA202.www.web.estore.controller;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import win.TIA202.www.web.estore.entity.*;
import win.TIA202.www.web.estore.service.ItemService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("estoreadmin")
public class AdminItemController {

    @Value("#{systemProperties['catalina.home']}/files/") // todo: 雲端的路徑改為單斜線
//  @Value("#{systemProperties['catalina.home']}\\files\\") // 本地
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
        String fileName = UUID.randomUUID().toString() + "." + files[0].getContentType().split("/")[1]; // todo: 時間戳
        String photoSaveLocation = fileRootPath + fileName; // todo: 需要再確認這樣是否可以存到雲端的指定資料夾位置
        files[0].transferTo(Paths.get(photoSaveLocation));
        String photoLocation = "https://www.tia202g1.win/files/" + fileName; // todo: 確認前端可以叫出該位置檔案的路徑名稱 ("https://www.tia202g1.win/files/123.jpg")
        objectNode.put("photoLocation", photoLocation);

        return objectNode;

//        ArrayNode photoLocations = objectNode.putArray("photos");
//        for (MultipartFile file : files) {
//            String fileName = UUID.randomUUID().toString() + "." + file.getContentType().split("/")[1];
//            String photoSaveLocation = fileRootPath + fileName;
//            String photoLocation = "/files/" + fileName;
//            photoLocations.add(photoLocation);
//            file.transferTo(Paths.get(photoSaveLocation));
//        }
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

//        取得單一商品的所有大小、顏色規格配對
        List<ItemInfo> itemInfosList = service.findItemInfosByItemId(id);

        item.setItemInfos(itemInfosList);

        return item;
    }

    //  管理員點選編輯按鈕後的行為，取得該itemInfo的資料
    @GetMapping("item/getiteminfo/{itemInfoId}")
    public ItemInfo getItemInfoForEdit(@PathVariable Integer itemInfoId) {
        return service.findItemInfoByIdForEdit(itemInfoId);
    }

    // 編輯itemInfo
    @PutMapping("item/edititeminfo/{itemInfoId}")
    public ItemInfo editItemInfo(@RequestBody ItemInfo itemInfo) {
        return service.editItemInfo(itemInfo);
    }

    // 管理員點選編輯按鈕後的行為，取得該item的資料
    @GetMapping("item/getitem/{itemId}")
    public Item getItemForEdit(@PathVariable Integer itemId) {
        return service.findItemByIdForEdit(itemId);
    }

    // 編輯item
    @PutMapping("item/edititem/{itemId}")
    public Item editItemAndModel(@RequestBody ItemFromAdminEdit itemFromAdminEdit) {
        return service.editItemAndModel(itemFromAdminEdit);
    }

    // itemInfo上下架
    @PutMapping("iteminfo/list/{itemInfoId}/{itemStatus}")
    public ItemInfo listItemInfo(@PathVariable String itemStatus, @PathVariable String itemInfoId) {
        return service.updateListItemInfo(Integer.valueOf(itemStatus), Integer.valueOf(itemInfoId));
    }

    @GetMapping("item/checkExist/{itemModel}/{itemName}")
    public boolean checkExist(@PathVariable String itemName, @PathVariable String itemModel) {
        return service.checkItemExist(itemName, itemModel);
    }

}
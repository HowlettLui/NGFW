package win.TIA202.www.web.estore.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


public class ItemIWOFramework implements Serializable {

    private static final long serialVersionUID = 4869794888532867324L;

    private Integer itemId;
    private String itemName;
    private Integer staffId;
    private String itemContent;
    private String itemType;
    private String itemPhoto;
    private Integer itemModelId;
    private Integer itemPrice;
    private String itemDetails;
    private Timestamp createTime;
    private List<ItemInfo> itemInfo;
    private ItemModel itemModel;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getStaffId() {
        return staffId;
    }

    public void setStaffId(Integer staffId) {
        this.staffId = staffId;
    }

    public String getItemContent() {
        return itemContent;
    }

    public void setItemContent(String itemContent) {
        this.itemContent = itemContent;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemPhoto() {
        return itemPhoto;
    }

    public void setItemPhoto(String itemPhoto) {
        this.itemPhoto = itemPhoto;
    }

    public Integer getItemModelId() {
        return itemModelId;
    }

    public void setItemModelId(Integer itemModelId) {
        this.itemModelId = itemModelId;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(String itemDetails) {
        this.itemDetails = itemDetails;
    }
}

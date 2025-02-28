package win.TIA202.www.web.estore.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import win.TIA202.www.core.pojo.ItemCore;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item extends ItemCore {

    private static final long serialVersionUID = -2810428982156732237L;

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemId;
    @Column(name = "item_name")
    private String itemName;
    @Column(name = "staff_id")
    private Integer staffId;
    @Column(name = "item_content")
    private String itemContent;
    @Column(name = "item_type")
    private String itemType;
    @Column(name = "item_photo")
    private String itemPhoto;
    @Column(name = "item_model_id")
    private Integer itemModelId;
    @Column(name = "item_price")
    private Integer itemPrice;
    @Column(name = "item_details")
    private String itemDetails;
    @Column(name = "create_time", insertable = false)
    private Timestamp createTime;

    public Item(Integer itemId, String itemName, String itemType, String itemPhoto, Integer itemModelId, Integer itemPrice) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPhoto = itemPhoto;
        this.itemModelId = itemModelId;
        this.itemPrice = itemPrice;
    }
}

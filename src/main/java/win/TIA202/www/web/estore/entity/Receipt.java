package win.TIA202.www.web.estore.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Receipt implements Serializable {
    private static final long serialVersionUID = -2394605728232692890L;

    @Id
    @Column(name = "receipt_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer receiptId;
    @Column(name = "order_id")
    private Integer orderId;
    @Column(name = "item_info_id")
    private Integer itemInfoId;
    @Column(name = "receipt_itemcount")
    private Integer receiptItemCount;
    @Column(name = "receipt_itemprice")
    private Integer receiptItemPrice;
    @Column(name = "create_time", insertable = false)
    private Timestamp createTime;

    @Transient
    private boolean result;
    @Transient
    private String message;
    @Transient
    private String itemName;
    @Transient
    private String itemInfoSize;
    @Transient
    private String itemInfoColor;
}

package win.TIA202.www.web.estore.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 3586540288852586005L;

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "order_status")
    private Integer orderStatus;
    @Column(name = "order_payment")
    private Integer orderPayment;
    @Column(name = "order_shipment")
    private String orderShipment;
    @Column(name = "order_address")
    private String orderAddress;
    @Column(name = "order_totalprice")
    private Integer orderTotalPrice;
    @Column(name = "order_description")
    private String orderDescription;
    @Column(name = "create_time", insertable = false)
    private Timestamp createTime;

    @Transient
    private boolean result;
    @Transient
    private String message;
    @Transient
    private List<Receipt> receipts;
}

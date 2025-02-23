package win.TIA202.www.web.estore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "item_model")
public class ItemModel implements Serializable {

    private static final long serialVersionUID = -4834102748720026557L;

    @Id
    @Column(name = "item_model_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer itemModelId;
    @Column(name = "item_model")
    private String itemModel;
    @Column(name = "staff_id")
    private Integer staffId;
    @Column(name = "create_time", insertable = false)
    private Timestamp createDate;

}

package win.TIA202.www.web.estore.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import win.TIA202.www.core.pojo.CartCore;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cart_detail")
public class Cart extends CartCore {

    private static final long serialVersionUID = 4184641399155050546L;

    @Id
    @Column(name = "cart_detail_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartDetailId;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "item_info_id")
    private Integer itemInfoId;
    @Column(name = "detail_itemcount")
    private Integer detailItemcount;
    @Column(name = "create_time", insertable = false)
    private Time createTime;
}

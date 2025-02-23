package win.TIA202.www.web.estore.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ItemFromAddReq implements Serializable {

    private static final long serialVersionUID = -8843219137711133003L;

    private ItemModel itemModel;
    private Item item;
    private ItemInfo itemInfo;

}

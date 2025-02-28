package win.TIA202.www.web.estore.entity;

import lombok.Getter;
import lombok.Setter;
import win.TIA202.www.core.pojo.ItemCore;

@Getter
@Setter
public class ItemFromAddReq extends ItemCore {

    private static final long serialVersionUID = -8843219137711133003L;

    private ItemModel itemModel;
    private Item item;
    private ItemInfo itemInfo;

}

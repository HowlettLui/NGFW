package win.TIA202.www.web.estore.entity;

import lombok.Getter;
import lombok.Setter;
import win.TIA202.www.core.pojo.ItemCore;

@Getter
@Setter
public class ItemFromAdminEdit extends ItemCore {

    private static final long serialVersionUID = -4808571005449877546L;

    private ItemModel itemModel;
    private Item item;

}

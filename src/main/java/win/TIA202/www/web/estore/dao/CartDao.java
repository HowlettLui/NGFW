package win.TIA202.www.web.estore.dao;

import win.TIA202.www.web.estore.entity.Cart;

import java.util.List;

public interface CartDao {
    List<Cart> selectByUserId(Integer userId);
}

package win.TIA202.www.web.estore.dao;

import win.TIA202.www.web.estore.entity.Order;

import java.util.List;

public interface OrderDao {

    void insertOrder(Order order);

    List<Order> selectOrdersByUserId(Integer userId);

    Integer updateOrderStatus(Integer orderId, Integer orderStatus);

    List<Order> selectAllOrders();
}

package win.TIA202.www.web.estore.service;

import win.TIA202.www.web.estore.entity.Order;
import win.TIA202.www.web.estore.entity.Receipt;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface OrderService {

    Order ecpay(Order order) throws UnsupportedEncodingException;

    List<Order> getOrdersByUserId(Integer userId);

    List<Receipt> getReceiptsByOrderId(Integer orderId);

    String ecpayCheck(Integer orderId) throws UnsupportedEncodingException;

    Order updateOrderStatus(Integer orderId, Integer orderStatus);

    Integer editItemInfoStock(Integer orderId);

    List<Order> getAllOrders();
}

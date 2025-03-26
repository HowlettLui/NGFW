package win.TIA202.www.web.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import win.TIA202.www.web.estore.entity.Order;
import win.TIA202.www.web.estore.entity.Receipt;
import win.TIA202.www.web.estore.service.OrderService;

import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping("estore")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("orders")
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("order/pay/{userId}")
    public Order pay(@RequestBody Order order) {
        try {
            return orderService.ecpay(order);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("orders/{userId}")
    public List<Order> getOrdersByUserId(@PathVariable Integer userId) {
        return orderService.getOrdersByUserId(userId);
    }

    @GetMapping("orders/receipts/{orderId}")
    public List<Receipt> getReceiptsByOrderId(@PathVariable Integer orderId) {
        return orderService.getReceiptsByOrderId(orderId);
    }

    @PostMapping("orders/ecpaystatus/{orderId}")
    public String ecpayStatus(@PathVariable Integer orderId) {
        try {
            return orderService.ecpayCheck(orderId);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("order/edit/{orderId}/{orderStatus}")
    public Order editOrderStatus(@PathVariable Integer orderId, @PathVariable Integer orderStatus) {
        return orderService.updateOrderStatus(orderId, orderStatus);
    }

    @PutMapping("order/editItemInfo/{orderId}")
    public Integer editItemInfoStock(@PathVariable Integer orderId) {
        return orderService.editItemInfoStock(orderId);
    }
}

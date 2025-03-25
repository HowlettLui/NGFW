package win.TIA202.www.web.estore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.TIA202.www.web.estore.entity.Order;
import win.TIA202.www.web.estore.service.OrderForPayService;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("estore/order")
public class OrderController {

    @Autowired
    private OrderForPayService orderForPayService;

    @PostMapping("pay/{userId}")
    public Order pay(@RequestBody Order order) {
        try {
            return orderForPayService.ecpay(order);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}

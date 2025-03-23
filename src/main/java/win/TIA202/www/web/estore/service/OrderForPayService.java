package win.TIA202.www.web.estore.service;

import win.TIA202.www.web.estore.entity.Order;

import java.io.UnsupportedEncodingException;

public interface OrderForPayService {

    Order ecpay(Order order) throws UnsupportedEncodingException;
}

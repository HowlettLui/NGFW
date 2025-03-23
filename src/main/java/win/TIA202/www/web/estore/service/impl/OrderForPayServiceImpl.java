package win.TIA202.www.web.estore.service.impl;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.TIA202.www.web.estore.entity.Order;
import win.TIA202.www.web.estore.service.OrderForPayService;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class OrderForPayServiceImpl implements OrderForPayService {
    @Override
    public Order ecpay(Order order) throws UnsupportedEncodingException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String orderDate = sdf.format(new Date());

        AllInOne allInOne = new AllInOne("");

        AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
        aioCheckOutALL.setMerchantTradeNo("Order" + "test05");
        aioCheckOutALL.setMerchantTradeDate(orderDate);
        aioCheckOutALL.setTotalAmount(order.getOrderTotalPrice().toString());
        aioCheckOutALL.setTradeDesc("NGFW商城支付");
        aioCheckOutALL.setItemName(order.getUserId() + "購買共 " + order.getOrderTotalPrice() + " 元");
        aioCheckOutALL.setClientBackURL("http://localhost:8080/NGFW/estore/orders.html");
        aioCheckOutALL.setReturnURL("http://localhost:8080/NGFW/estore/cart.html");
        aioCheckOutALL.setNeedExtraPaidInfo("N");

        try {
            String form = allInOne.aioCheckOut(aioCheckOutALL, null);
            order.setResult(true);
            order.setMessage(form);
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            order.setResult(false);
            order.setMessage("處理失敗，請稍後再試");
            return order;
        }
    }
}

package win.TIA202.www.web.estore.service.impl;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import ecpay.payment.integration.domain.QueryTradeInfoObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import win.TIA202.www.web.estore.dao.CartDao;
import win.TIA202.www.web.estore.dao.ItemDao;
import win.TIA202.www.web.estore.dao.OrderDao;
import win.TIA202.www.web.estore.dao.ReceiptDao;
import win.TIA202.www.web.estore.entity.Cart;
import win.TIA202.www.web.estore.entity.Order;
import win.TIA202.www.web.estore.entity.Receipt;
import win.TIA202.www.web.estore.service.OrderService;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ReceiptDao receiptDao;

    @Autowired
    private ItemDao itemDao;

    @Autowired
    private CartDao cartDao;

    @Override
    public Order ecpay(Order order) throws UnsupportedEncodingException {
        List<Receipt> receipts = order.getReceipts();
        Integer userId = order.getUserId();
        String targetUrl = "https://www.tia202g1.win/estore/ordersecpay.html?userId=" + userId.toString();
//        String targetUrl = "http://localhost:8080/NGFW/estore/ordersecpay.html?userId=" + userId.toString();
        orderDao.insertOrder(order);
        if (order.getOrderId() != null) {
            // 新增訂單以及訂單明細資訊
            for (Receipt receipt : receipts) {
                receipt.setOrderId(order.getOrderId());
                receiptDao.insertReceipt(receipt);
                Integer itemTotalCount = itemDao.selectItemCountByItemInfoId(receipt.getItemInfoId());
                if (itemTotalCount == null || itemTotalCount == 0) {
                    order.setOrderStatus(-1);
                    order.setResult(false);
                    order.setMessage("無法取得商品數量，請重新選購");
                    return order;
                } else if (itemTotalCount < receipt.getReceiptItemCount()) {
                    order.setOrderStatus(-1);
                    order.setResult(false);
                    order.setMessage("商品數量不足，請重新選購");
                    return order;
                } else {
                    itemTotalCount -= receipt.getReceiptItemCount();
                    itemDao.editItemCountByItemInfoId(itemTotalCount, receipt.getItemInfoId());
                }
            }
            // 選擇線上付款、貨到付款或者錯誤
            if (order.getOrderPayment() == 0) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                String orderDate = sdf.format(new Date());

                AllInOne allInOne = new AllInOne("");

                AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
                aioCheckOutALL.setMerchantTradeNo("orderId" + order.getOrderId().toString());  // todo: 確認OK之後改成這個
                aioCheckOutALL.setMerchantTradeDate(orderDate);
                aioCheckOutALL.setTotalAmount(order.getOrderTotalPrice().toString());
                aioCheckOutALL.setTradeDesc("NGFW商城支付");
                aioCheckOutALL.setItemName(order.getOrderDescription());
                aioCheckOutALL.setClientBackURL(targetUrl);
                aioCheckOutALL.setReturnURL(targetUrl);
                aioCheckOutALL.setNeedExtraPaidInfo("N");

                try {
                    String form = allInOne.aioCheckOut(aioCheckOutALL, null);
                    // 更新購物車內容
                    for (Receipt receipt : receipts) {
                        Cart cart = new Cart();
                        cart.setUserId(order.getUserId());
                        cart.setItemInfoId(receipt.getItemInfoId());
                        cartDao.deleteCartByUserIdAndItemInfoId(cart);
                    }
                    order.setResult(true);
                    order.setMessage(form);
                    return order;
                } catch (Exception e) {
                    e.printStackTrace();
                    order.setResult(false);
                    order.setMessage("處理失敗，請稍後再試");
                    return order;
                }
            } else if (order.getOrderPayment() == 1) {
                // 更新購物車內容
                for (Receipt receipt : receipts) {
                    Cart cart = new Cart();
                    cart.setUserId(order.getUserId());
                    cart.setItemInfoId(receipt.getItemInfoId());
                    cartDao.deleteCartByUserIdAndItemInfoId(cart);
                }
                order.setResult(true);
                order.setMessage("訂單已完成，處理中");
                return order;
            } else {
                order.setResult(false);
                order.setMessage("訂單發生錯誤，請聯絡管理員");
                return order;
            }
        } else {
            order.setResult(false);
            order.setMessage("訂單發生錯誤，請聯絡管理員");
            return order;
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Integer userId) {
        return orderDao.selectOrdersByUserId(userId);
    }

    @Override
    public List<Receipt> getReceiptsByOrderId(Integer orderId) {
        List<Object[]> list = receiptDao.selectReceiptsByOrderId(orderId);
        List<Receipt> receipts = new ArrayList<Receipt>();
        for (Object[] row : list) {
            Receipt receipt = (Receipt) row[0];
            receipt.setItemInfoSize((String) row[1]);
            receipt.setItemInfoColor((String) row[2]);
            receipt.setItemName((String) row[3]);
            receipts.add(receipt);
        }
        return receipts;
    }

    @Override
    public String ecpayCheck(Integer orderId) throws UnsupportedEncodingException {
        AllInOne allInOne = new AllInOne("");
        QueryTradeInfoObj obj = new QueryTradeInfoObj();
        obj.setMerchantTradeNo("orderId" + orderId.toString());
        return allInOne.queryTradeInfo(obj);
    }

    @Override
    public Order updateOrderStatus(Integer orderId, Integer orderStatus) {
        Integer result = orderDao.updateOrderStatus(orderId, orderStatus);
        Order order = new Order();
        if (result == 1) {
            order.setResult(true);
            order.setMessage("更新成功");
        } else {
            order.setResult(false);
            order.setMessage("更新失敗");
        }
        return order;
    }

    @Override
    public Integer editItemInfoStock(Integer orderId) {
        List<Object[]> objects = receiptDao.selectReceiptsByOrderId(orderId);
        List<Receipt> receipts = new ArrayList<Receipt>();
        for (Object[] row : objects) {
            Receipt receipt = (Receipt) row[0];
            receipts.add(receipt);
        }

        for (Receipt receipt : receipts) {
            Integer itemInfoId = receipt.getItemInfoId();
            Integer itemCountInReceipt = receipt.getReceiptItemCount();
            if (itemDao.editItemStockBackByItemInfoId(itemCountInReceipt, itemInfoId) != 1) {
                return receipt.getReceiptId();
            }
        }

        return 0;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.selectAllOrders();
    }
}

package win.TIA202.www.web.estore.dao;

import win.TIA202.www.web.estore.entity.Receipt;

import java.util.List;

public interface ReceiptDao {

    void insertReceipt(Receipt receipt);

    List<Object[]> selectReceiptsByOrderId(Integer orderId);
}

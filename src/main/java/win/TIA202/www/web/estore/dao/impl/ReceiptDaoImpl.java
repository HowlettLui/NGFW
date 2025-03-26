package win.TIA202.www.web.estore.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import win.TIA202.www.web.estore.dao.ReceiptDao;
import win.TIA202.www.web.estore.entity.Receipt;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ReceiptDaoImpl implements ReceiptDao {

    @PersistenceContext
    private Session session;

    @Override
    public void insertReceipt(Receipt receipt) {
        session.persist(receipt);
    }

    @Override
    public List<Object[]> selectReceiptsByOrderId(Integer orderId) {
        String hql = "SELECT r, if.itemSize, if.itemColor, i.itemName FROM Receipt r JOIN ItemInfo if ON r.itemInfoId = if.itemInfoId JOIN Item i ON if.itemId = i.itemId where r.orderId = :orderId";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("orderId", orderId);
        return query.getResultList();
    }
}

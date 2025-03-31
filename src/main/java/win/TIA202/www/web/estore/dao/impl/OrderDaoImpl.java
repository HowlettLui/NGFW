package win.TIA202.www.web.estore.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import win.TIA202.www.web.estore.dao.OrderDao;
import win.TIA202.www.web.estore.entity.Order;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private Session session;

    @Override
    public void insertOrder(Order order) {
        session.persist(order);
    }

    @Override
    public List<Order> selectOrdersByUserId(Integer userId) {
        String hql = "from Order where userId = :userId";
        Query<Order> query = session.createQuery(hql, Order.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public String selectOrderUserIdByOrderId(Integer orderId) {
        String hql = "SELECT o.userId FROM Order o WHERE o.orderId = :orderId";
        Query<Integer> query = session.createQuery(hql, Integer.class);
        query.setParameter("orderId", orderId);
        return query.uniqueResult().toString();
    }

    @Override
    public Integer updateOrderStatus(Integer orderId, Integer orderStatus) {
        String hql = "UPDATE Order SET orderStatus = :orderStatus where orderId = :orderId";
        return session.createQuery(hql)
            .setParameter("orderStatus", orderStatus)
            .setParameter("orderId", orderId)
            .executeUpdate();
    }

    @Override
    public List<Order> selectAllOrders() {
        String hql = "FROM Order order";
        Query<Order> query = session.createQuery(hql, Order.class);
        return query.getResultList();
    }
}

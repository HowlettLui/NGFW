package win.TIA202.www.web.estore.dao.impl;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import win.TIA202.www.web.estore.dao.CartDao;
import win.TIA202.www.web.estore.entity.Cart;

import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @PersistenceContext
    private Session session;

    @Override
    public List<Cart> selectByUserId(Integer userId) {
        String hql = "FROM Cart WHERE userId = :userId";
        Query<Cart> query = session.createQuery(hql, Cart.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}

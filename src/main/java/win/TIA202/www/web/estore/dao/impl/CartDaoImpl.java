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

    @Override
    public List<Object[]> selectByUserIdAndItemInfoId(Integer userId, Integer itemInfoId) {
        String hql = "SELECT c, ii.itemStock, ii.itemStatus, i.itemName FROM Cart c JOIN ItemInfo ii ON c.itemInfoId = ii.itemInfoId JOIN Item i ON ii.itemId = i.itemId WHERE c.userId = :userId AND c.itemInfoId = :itemInfoId";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("userId", userId);
        query.setParameter("itemInfoId", itemInfoId);
        return query.getResultList();
    }

    @Override
    public Cart editCart(Cart newCart) {
        return (Cart) session.merge(newCart);
    }

    @Override
    public Integer deleteCartByUserIdAndItemInfoId(Cart cart) {
        String hql = "DELETE FROM Cart WHERE userId = :userId AND itemInfoId = :itemInfoId";
        Query query = session.createQuery(hql);
        query.setParameter("userId", cart.getUserId());
        query.setParameter("itemInfoId", cart.getItemInfoId());
        return query.executeUpdate();
    }

    @Override
    public Cart addItemInfoToCart(Cart cart) {
        session.persist(cart);
        return cart;
    }
}

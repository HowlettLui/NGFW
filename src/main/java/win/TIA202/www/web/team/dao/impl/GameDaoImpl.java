package win.TIA202.www.web.team.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import win.TIA202.www.web.team.dao.GameDao;
import win.TIA202.www.web.team.entity.Game;

@Repository
@Transactional
public class GameDaoImpl implements GameDao {
    @PersistenceContext
    private Session session;

    @Override
    public List<Game> selectAll() {
        final String hql = "FROM Game";
        return session.createQuery(hql, Game.class).getResultList();
    }

    @Override
    public Game selectById(int gameNo) {
        final String hql = "FROM Game g WHERE g.gameNo = :gameNo";
        return session.createQuery(hql, Game.class)
                .setParameter("gameNo", gameNo)
                .uniqueResult();
    }

}

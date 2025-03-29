package win.TIA202.www.web.team.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import win.TIA202.www.web.team.dao.GameEventDao;
import win.TIA202.www.web.team.entity.GameEvent;

@Repository
@Transactional
public class GameEventDaoImpl implements GameEventDao {
    @PersistenceContext
    private Session session;

    @Override
    public List<GameEvent> findByGameId(Integer gameId) {
        String hql = "FROM GameEvent WHERE gameId = :gameId";
        return session.createQuery(hql, GameEvent.class)
                .setParameter("gameId", gameId)
                .getResultList();
    }

}

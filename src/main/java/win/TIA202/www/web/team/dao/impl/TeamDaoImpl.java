package win.TIA202.www.web.team.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import win.TIA202.www.web.team.dao.TeamDao;
import win.TIA202.www.web.team.entity.Team;

@Repository
@Transactional
public class TeamDaoImpl implements TeamDao {
    @PersistenceContext
    private Session session;

    @Override
    public List<Team> selectAll() {
        final String hql = "FROM Team";
        return session.createQuery(hql, Team.class).getResultList();
    }
}
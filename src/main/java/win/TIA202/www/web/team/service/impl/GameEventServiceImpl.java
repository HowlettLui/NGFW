package win.TIA202.www.web.team.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import win.TIA202.www.web.team.dao.GameEventDao;
import win.TIA202.www.web.team.entity.GameEvent;
import win.TIA202.www.web.team.service.GameEventService;

@Service
public class GameEventServiceImpl implements GameEventService {
    @Autowired
    private GameEventDao dao;

    @Override
    public List<GameEvent> getGameEventListByGameId(Integer gameId) {
        return dao.findByGameId(gameId);
    }

}

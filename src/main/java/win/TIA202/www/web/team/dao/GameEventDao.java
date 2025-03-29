package win.TIA202.www.web.team.dao;

import java.util.List;

import win.TIA202.www.web.team.entity.GameEvent;

public interface GameEventDao {
    public List<GameEvent> findByGameId(Integer gameId);
}

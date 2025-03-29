package win.TIA202.www.web.team.service;

import java.util.List;

import win.TIA202.www.web.team.entity.GameEvent;

public interface GameEventService {

    public List<GameEvent> getGameEventListByGameId(Integer gameId);
}

package win.TIA202.www.web.team.dao;

import java.util.List;

import win.TIA202.www.web.team.entity.Game;

public interface GameDao {
    public List<Game> selectAll();
}

package win.TIA202.www.web.team.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import win.TIA202.www.web.team.dao.GameDao;
import win.TIA202.www.web.team.entity.Game;
import win.TIA202.www.web.team.service.GameService;

@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private GameDao dao;

    @Override
    public List<Game> getGameList() {
        return dao.selectAll();
    }
}

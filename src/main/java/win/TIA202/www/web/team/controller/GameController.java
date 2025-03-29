package win.TIA202.www.web.team.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import win.TIA202.www.web.team.service.GameEventService;
import win.TIA202.www.web.team.service.GameService;

@RestController
@RequestMapping("game")
public class GameController {
    @Autowired
    private GameService svc;

    @Autowired
    private GameEventService gameEventService;

    @GetMapping("list")
    public List<?> gameList(){
        return svc.getGameList();
    }

    @GetMapping("events")
    public List<?> events(){
        return svc.getEventList();
    }

    @GetMapping("byId")
    public Object gameById(@RequestParam("id") int id){
        return svc.getGameById(id);
    }

    @GetMapping("eventsByGameId")
    public List<?> eventsByGameId(@RequestParam("id") int gameId){
        return gameEventService.getGameEventListByGameId(gameId);
    }
}

package win.TIA202.www.web.team.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import win.TIA202.www.web.team.service.GameService;

@RestController
@RequestMapping("game")
public class GameController {
    @Autowired
    private GameService svc;

    @GetMapping("list")
    public List<?> gameList(){
        return svc.getGameList();
    }
}

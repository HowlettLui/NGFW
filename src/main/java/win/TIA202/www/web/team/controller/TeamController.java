package win.TIA202.www.web.team.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import win.TIA202.www.web.team.service.TeamService;

@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    private TeamService svc;

    @GetMapping("/test")
    public List<String> test() {
        return List.of("A", "B", "C");
    }
    
    @GetMapping("list")
    public List<?> getTeams(Model model) {
        return svc.getList();
    }
    
}

package win.TIA202.www.web.team.dao;

import java.util.List;

import win.TIA202.www.web.team.entity.Team;

public interface TeamDao {
    public List<Team> selectAll();
}

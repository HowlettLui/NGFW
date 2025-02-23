package win.TIA202.www.web.dao;

import win.TIA202.www.web.entity.Role;

import java.util.List;

public interface RoleDao {
//    int update();

    Role selectByRoleType(String roleType);

    Role selectByRoleId(int roleId);

    List<Role> selectAll();
}

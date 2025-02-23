package win.TIA202.www.web.service;

import win.TIA202.www.web.entity.Role;

import java.util.List;

public interface RoleService {

    boolean selectByRoleType(String roleType);

    boolean selectByRoleId(int roleId);

    List<Role> findAll();
}

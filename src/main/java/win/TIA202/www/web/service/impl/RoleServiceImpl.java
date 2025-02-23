package win.TIA202.www.web.service.impl;

import org.springframework.stereotype.Service;
import win.TIA202.www.web.dao.RoleDao;
import win.TIA202.www.web.dao.UserDao;
import win.TIA202.www.web.dao.impl.RoleDaoImpl;
import win.TIA202.www.web.dao.impl.UserDaoImpl;
import win.TIA202.www.web.entity.Role;
import win.TIA202.www.web.service.RoleService;

import javax.naming.NamingException;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;

    public RoleServiceImpl() throws NamingException {
        roleDao = new RoleDaoImpl();
    }

    @Override
    public boolean selectByRoleType(String roleType) {
        Role role = roleDao.selectByRoleType(roleType);
        return  role != null;
    }

    @Override
    public boolean selectByRoleId(int roleId) {
        Role role = roleDao.selectByRoleId(roleId);
        return  role != null;
    }

    @Override
    public List<Role> findAll() {
        return roleDao.selectAll();
    }
}

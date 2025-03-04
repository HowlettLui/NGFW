package win.TIA202.www.web.service.impl;

import org.springframework.stereotype.Service;
import win.TIA202.www.web.dao.RolePermissionDao;
import win.TIA202.www.web.dao.impl.RolePermissionDaoImp;
import win.TIA202.www.web.entity.RolePermission;
import win.TIA202.www.web.service.RolePermissionService;

import javax.naming.NamingException;
@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    private final RolePermissionDao rolePermissionDao;

    public RolePermissionServiceImpl() throws NamingException {
        rolePermissionDao = new RolePermissionDaoImp();
    }

    @Override
    public int updateRolePermission(RolePermission rolePermission) {
        return rolePermissionDao.updateRolePermission(rolePermission);
    }
}

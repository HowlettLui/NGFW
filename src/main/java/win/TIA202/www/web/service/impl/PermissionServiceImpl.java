package win.TIA202.www.web.service.impl;

import org.springframework.stereotype.Service;
import win.TIA202.www.web.dao.PermissionDao;
import win.TIA202.www.web.dao.impl.PermissionDaoImpl;
import win.TIA202.www.web.entity.Permission;
import win.TIA202.www.web.service.PermissionService;

import javax.naming.NamingException;
import java.util.List;
@Service
public class PermissionServiceImpl implements PermissionService {
    private final PermissionDao permissionDao;

    public PermissionServiceImpl() throws NamingException {
        permissionDao = new PermissionDaoImpl();
    }

    @Override
    public boolean selectByPermissionId(int permissionId) {
        Permission permission = permissionDao.selectByPermissionId(permissionId);
        return permission != null;
    }

    @Override
    public boolean selectByPermissionType(String permissionType) {
        Permission permission = permissionDao.selectByPermissionType(permissionType);
        return permission != null;
    }

    @Override
    public List<Permission> findAll() {
        return permissionDao.selectAll();
    }
}

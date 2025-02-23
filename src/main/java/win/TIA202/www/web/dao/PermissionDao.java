package win.TIA202.www.web.dao;

import win.TIA202.www.web.entity.Permission;

import java.util.List;

public interface PermissionDao {
    List<Permission> selectAll();

    Permission selectByPermissionId(int permissionId);

    Permission selectByPermissionType(String permissionTypr);
}

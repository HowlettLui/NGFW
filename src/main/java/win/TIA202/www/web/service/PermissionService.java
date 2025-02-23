package win.TIA202.www.web.service;

import win.TIA202.www.web.entity.Permission;

import java.util.List;

public interface PermissionService {
    boolean selectByPermissionId(int permissionId);

    boolean selectByPermissionType(String permissionType);

    List<Permission> findAll();
}

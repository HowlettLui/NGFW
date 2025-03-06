//package win.TIA202.www.web.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import win.TIA202.www.web.entity.Permission;
//import win.TIA202.www.web.service.PermissionService;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("per")
//public class PermissionController {
//    @Autowired
//    private PermissionService service;
//
//    @GetMapping("perall")
//    public List<Permission> getAllPer() {
//        return service.findAll();
//    }
//
//    @GetMapping("per_id")
//    public boolean selectByPermissionId(int permissionId) {
//        return service.selectByPermissionId(permissionId);
//    }
//
//    @GetMapping("per_type")
//    public boolean selectByPermissionType(String permissionType) {
//        return service.selectByPermissionType(permissionType);
//    }
//
//}

//package win.TIA202.www.web.dao.impl;
//
//import win.TIA202.www.web.dao.RolePermissionDao;
//import win.TIA202.www.web.entity.RolePermission;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//
//public class RolePermissionDaoImp implements RolePermissionDao {
//    private DataSource ds;
//
//    public RolePermissionDaoImp() throws NamingException {
////        ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/NGFW");
//        ds = (DataSource) new InitialContext().lookup("java:comp/env/NGFW");
//    }
//
//    @Override
//    public int addRolePermission(RolePermission rolePermission) {
//        final String sql = "insert into role_permission(role_id, permission_id) values(?, ?)";
//        try (
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//        ) {
//            pstmt.setInt(1, rolePermission.getRoleId());
//            pstmt.setInt(2, rolePermission.getPermissionId());
//            return pstmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    @Override
//    public int deleteRolePermission(int roleId) {
//        final String sql = "delete from role_permission where roleId = ?";
//        try (
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//        ) {
//            pstmt.setInt(1, roleId);
//            return pstmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//    }
//
//    @Override
//    public int updateRolePermission(RolePermission rolePermission) {
//        final String sql = "update role_permission set permission_id = ? where role_id = ?";
//        try (
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql)
//        ) {
//            pstmt.setInt(1, rolePermission.getPermissionId());
//            pstmt.setInt(2, rolePermission.getRoleId());
//            //TODO: 可能要再加上其他判斷
//            return pstmt.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return -1;
//
//    }
//
//    @Override
//    public RolePermission findRolePermissionByRoleId(int roleId) {
//        final String sql = "select * from role_permission where role_id = ?";
//        try (
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql)
//        ) {
//            pstmt.setInt(1, roleId);
//            try (
//                    ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    RolePermission rp = new RolePermission();
//                    rp.setPermissionId(rs.getInt("permission_id"));
//                    rp.setRoleId(rs.getInt("role_id"));
//                    rp.setCreateTime(rs.getTimestamp("create_time"));
//                    return rp;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public List<RolePermission> findAllRolePermissions() {
//        final String sql = "select * from role_permission order by role_id";
//        try (
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//                ResultSet rs = pstmt.executeQuery()
//        ) {
//            List<RolePermission> list = new ArrayList<>();
//            while (rs.next()) {
//                RolePermission rp = new RolePermission();
//                rp.setPermissionId(rs.getInt("permission_id"));
//                rp.setRoleId(rs.getInt("role_id"));
//                rp.setCreateTime(rs.getTimestamp("create_time"));
//                list.add(rp);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}

//package win.TIA202.www.web.dao.impl;
//
//import win.TIA202.www.web.dao.PermissionDao;
//import win.TIA202.www.web.entity.Permission;
//import win.TIA202.www.web.entity.Role;
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
//public class PermissionDaoImpl implements PermissionDao {
//    private DataSource ds;
//
//    public PermissionDaoImpl() throws NamingException {
////        ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/NGFW");
//        ds = (DataSource) new InitialContext().lookup("java:comp/env/NGFW");
//    }
//
//
//    @Override
//    public List<Permission> selectAll() {
//        final String sql = "select * from permission order by permission_id";
//        try (
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql);
//                ResultSet rs = pstmt.executeQuery()
//        ) {
//            List<Permission> list = new ArrayList<>();
//            while (rs.next()) {
//                Permission per = new Permission();
//                per.setPermissionId(rs.getInt("permission_id"));
//                per.setPermissionType(rs.getString("permission_type"));
//                per.setCreateTime(rs.getTimestamp("create_time"));
//                list.add(per);
//            }
//            return list;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    @Override
//    public Permission selectByPermissionId(int permissionId) {
//        final String sql = "select * from permission where permission_id = ?";
//        try (
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql)
//        ) {
//            pstmt.setInt(1, permissionId);
//            try (
//                    ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    Permission per = new Permission();
//                    per.setPermissionId(rs.getInt("permission_id"));
//                    per.setPermissionType(rs.getString("permission_type"));
//                    per.setCreateTime(rs.getTimestamp("create_time"));
//                    return per;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    @Override
//    public Permission selectByPermissionType(String permissionTypr) {
//        final String sql = "select * from permission where permission_type = ?";
//        try (
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql)
//        ) {
//            pstmt.setString(1, permissionTypr);
//            try (
//                    ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    Permission per = new Permission();
//                    per.setPermissionId(rs.getInt("permission_id"));
//                    per.setPermissionType(rs.getString("permission_type"));
//                    per.setCreateTime(rs.getTimestamp("create_time"));
//                    return per;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//}

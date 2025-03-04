package win.TIA202.www.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.stereotype.Repository;
import win.TIA202.www.web.dao.RoleDao;
import win.TIA202.www.web.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {
    private DataSource ds;

    public RoleDaoImpl() throws NamingException {
//        ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/NGFW");
        ds = (DataSource) new InitialContext().lookup("java:comp/env/NGFW");
    }

//    @Override
//    public int update() {
//        return 0;
//    }

    @Override
    public Role selectByRoleType(String roleType) {
        final String sql = "select * from ROLE where ROLE_TYPE = ?";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, roleType);
            try (
                    ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role();
                    role.setRoleId(rs.getInt("ROLE_ID"));
                    role.setRoleType(rs.getString("ROLE_TYPE"));
//                    role.setCreateTime(rs.getTimestamp("CREATE_TIME"));
                    return role;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Role selectByRoleId(int roleId) {
        final String sql = "select * from ROLE where ROLE_ID = ?";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, roleId);
            try (
                    ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Role role = new Role();
                    role.setRoleId(rs.getInt("ROLE_ID"));
                    role.setRoleType(rs.getString("ROLE_TYPE"));
//                    role.setCreateTime(rs.getTimestamp("CREATE_TIME"));
                    return role;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Role> selectAll() {
        final String sql = "select * from ROLE order by ROLE_ID";
        try (
                Connection conn = ds.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                ResultSet rs = pstmt.executeQuery()
        ) {
            List<Role> list = new ArrayList<>();
            while (rs.next()) {
                Role role = new Role();
                role.setRoleId(rs.getInt("ROLE_ID"));
                role.setRoleType(rs.getString("ROLE_TYPE"));
                list.add(role);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

package win.TIA202.www.web.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import win.TIA202.www.web.dao.StaffInitDao;
import win.TIA202.www.web.entity.Staff;

@Repository
public class StaffInitDaoImpl implements StaffInitDao {
	@PersistenceContext
	private Session session;
	
//	private DataSource ds;
//
//	public StaffInitDaoImpl() throws NamingException {
//		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/NGFW");
//	}

	@Override
	public int staffInitInsert(Staff staff) {
//		String sql = "insert into STAFF(STAFF_ACCOUNT, STAFF_PASSWORD, STAFF_NAME, STAFF_EMAIL, STAFF_PHONE, STAFF_STATUS, STAFF_ROLE_ID, SSO) "
//				+ "values ('SYSTEM' ,? ,? ,? ,? ,0 ,12 ,'SYSTEM' )"; 																																					// !!
//		try (
//			Connection conn = ds.getConnection(); 
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//		){
//			pstmt.setString(1, staff.getStaffPassword());
//			pstmt.setString(2, staff.getStaffName());
//			pstmt.setString(3, staff.getStaffEmail());
//			pstmt.setString(4, staff.getStaffPhone());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
		session.persist(staff);
		return 1;
	}

	@Override
	public Staff staffSelectForLogin(String staffEmail, String staffPassword) {
//		String sql = "select * from STAFF where STAFF_EMAIL = ? and STAFF_PASSWORD = ?";	
//		try (
//			Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//		){
//			pstmt.setString(1, staffEmail);
//			pstmt.setString(2, staffPassword);
//			try(ResultSet rs = pstmt.executeQuery()){
//				if (rs.next()) {
//					Staff staff = new Staff();
//					staff.setStaffId(rs.getInt("STAFF_ID"));
//					staff.setStaffAccount(rs.getString("STAFF_ACCOUNT"));
//					staff.setStaffPassword(rs.getString("STAFF_PASSWORD"));
//					staff.setStaffName(rs.getString("STAFF_NAME"));
//					staff.setStaffEmail(rs.getString("STAFF_EMAIL"));
//					staff.setStaffPhone(rs.getString("STAFF_PHONE"));
//					staff.setStaffStatus(rs.getBoolean("STAFF_STATUS"));
//					staff.setStaffRoleId(rs.getInt("STAFF_ROLE_ID"));
//					staff.setSso(rs.getString("SSO"));
//					staff.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//					return staff;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
		
		final String sql = "select * from STAFF where STAFF_EMAIL = :staff_email and STAFF_PASSWORD = :staff_password";
		return session
				.createNativeQuery(sql, Staff.class)
				.setParameter("staff_email", staffEmail)
				.setParameter("staff_password", staffPassword)
				.uniqueResult();
	}

	public Staff selectByStaffEmail(String staffEmail) {
//		String sql = "select * from STAFF where STAFF_EMAIL = ?";	
//		try (
//			Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//		){
//			pstmt.setString(1, staffEmail);
//			try(ResultSet rs = pstmt.executeQuery()){
//				if (rs.next()) {
//					Staff staff = new Staff();
//					staff.setStaffId(rs.getInt("STAFF_ID"));
//					staff.setStaffAccount(rs.getString("STAFF_ACCOUNT"));
//					staff.setStaffPassword(rs.getString("STAFF_PASSWORD"));
//					staff.setStaffName(rs.getString("STAFF_NAME"));
//					staff.setStaffEmail(rs.getString("STAFF_EMAIL"));
//					staff.setStaffPhone(rs.getString("STAFF_PHONE"));
//					staff.setStaffStatus(rs.getBoolean("STAFF_STATUS"));
//					staff.setStaffRoleId(rs.getInt("STAFF_ROLE_ID"));
//					staff.setSso(rs.getString("SSO"));
//					staff.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//					return staff;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
		
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Staff> cQuery = cBuilder.createQuery(Staff.class);
		Root<Staff> root = cQuery.from(Staff.class);
		cQuery.where(cBuilder.equal(root.get("staffEmail"), staffEmail));
		return session
				.createQuery(cQuery)
				.uniqueResult();
	}

	@Override
	public List<Staff> selectAllUser() {
		final String hql = "FROM Staff ORDER BY staff_id";
		return session
				.createQuery(hql, Staff.class)
				.getResultList();
	}
}

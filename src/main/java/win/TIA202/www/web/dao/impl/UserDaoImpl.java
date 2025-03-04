package win.TIA202.www.web.dao.impl;

import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import win.TIA202.www.web.dao.UserDao;
import win.TIA202.www.web.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	@PersistenceContext
	private Session session;
//	private DataSource ds;
//
//	public UserDaoImpl() throws NamingException {
//		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/NGFW");
//	}
	
	@Override
	public int insert(User user) {
//		String sql = "insert into `USER`(ACCOUNT, EMAIL, NAME, PASSWORD, PHONE, STATUS, ROLE_ID, OAUTH) values (? ,? ,'SYSTEM' ,? ,'SYSTEM' ,0 ,12 ,? )"; // !!測試暫時使用oauth欄位裝 confirmPassword																																						// !!
//		try (
//			Connection conn = ds.getConnection(); 
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//		){
//			pstmt.setString(1, user.getAccount());
//			pstmt.setString(2, user.getEmail());
//			pstmt.setString(3, user.getPassword());
//			pstmt.setString(4, user.getConfirmPassword());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
		
		session.persist(user);
		return -1;
	}

	@Override
	public User selectForLogin(String email, String password) {
//		String sql = "select * from `USER` where EMAIL = ? and PASSWORD = ?";	
//		try (
//			Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//		){
//			pstmt.setString(1, email);
//			pstmt.setString(2, password);
//			try(ResultSet rs = pstmt.executeQuery()){
//				if (rs.next()) {
//					User user = new User();
//					user.setUserId(rs.getInt("USER_ID"));
//					user.setAccount(rs.getString("ACCOUNT"));
//					user.setPassword(rs.getString("PASSWORD"));
//					user.setName(rs.getString("NAME"));
//					user.setEmail(rs.getString("EMAIL"));
//					user.setPhone(rs.getString("PHONE"));
//					user.setStatus(rs.getBoolean("STATUS"));
//					user.setRoleId(rs.getInt("ROLE_ID"));
//					user.setOauth(rs.getString("OAUTH"));
//					user.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//					return user;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
		
		final String sql = "select * from `USER` where EMAIL = :email and PASSWORD = :password";
		return session
				.createNativeQuery(sql, User.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.uniqueResult();
	}

	@Override
	public User selectByAccount(String account) {
//		String sql = "select * from `USER` where ACCOUNT = ?";	
//		try (
//			Connection conn = ds.getConnection();
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//		){
//			pstmt.setString(1, account);
//			try(ResultSet rs = pstmt.executeQuery()){
//				if (rs.next()) {
//					User user = new User();
//					user.setUserId(rs.getInt("USER_ID"));
//					user.setPassword(rs.getString("PASSWORD"));
//					user.setName(rs.getString("NAME"));
//					user.setEmail(rs.getString("EMAIL"));
//					user.setPhone(rs.getString("PHONE"));
//					user.setStatus(rs.getBoolean("STATUS"));
//					user.setRoleId(rs.getInt("ROLE_ID"));
//					user.setOauth(rs.getString("OAUTH"));
//					user.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//					return user;
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
		
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> cQuery = cBuilder.createQuery(User.class);
		Root<User> root = cQuery.from(User.class);
		cQuery.where(cBuilder.equal(root.get("account"), account));
		return session
				.createQuery(cQuery)
				.uniqueResult();
	}
	
	@Override
	public User selectByEmail(String email) {		
		CriteriaBuilder cBuilder = session.getCriteriaBuilder();
		CriteriaQuery<User> cQuery = cBuilder.createQuery(User.class);
		Root<User> root = cQuery.from(User.class);
		cQuery.where(cBuilder.equal(root.get("email"), email));
		return session
				.createQuery(cQuery)
				.uniqueResult();
	}
}

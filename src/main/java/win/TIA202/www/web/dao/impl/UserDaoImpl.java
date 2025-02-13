package win.TIA202.www.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import win.TIA202.www.web.dao.UserDao;
import win.TIA202.www.web.entity.User;

public class UserDaoImpl implements UserDao {
	private DataSource ds;

	public UserDaoImpl() throws NamingException {
		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/NGFW");
	}

	@Override
	public int insert(User user) {
		// selectIdByEmail
//		String checkEmailSql = "SELECT user_id from `USER` where EMAIL = ? ";
//		try (
//			Connection conn = ds.getConnection(); 
//			PreparedStatement pstmt = conn.prepareStatement(checkEmailSql);
//		){
//			pstmt.setString(1, user.getEmail());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
		
//		if (condition) {
//
//		}		// 此段在檢查email是否已被註冊

		String sql = "insert into `USER`(ACCOUNT, EMAIL, NAME, PASSWORD, PHONE, STATUS, ROLE_ID, OAUTH) values (? ,? ,'SYSTEM' ,? ,'SYSTEM' ,0 ,12 ,? )"; // !!測試暫時使用oauth欄位裝 confirmPassword																																						// !!
		try (
			Connection conn = ds.getConnection(); 
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setString(1, user.getAccount());
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getConfirmPassword());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delectById(Integer user_id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User selectByAccountAndPassword(User user) {
		// TODO for login
		return null;
	}

	@Override
	public List<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}
}

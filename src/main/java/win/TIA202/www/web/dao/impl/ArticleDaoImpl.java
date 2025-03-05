package win.TIA202.www.web.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import win.TIA202.www.web.dao.ArticleDao;
//import win.TIA202.www.web.dao.UserDao;
import win.TIA202.www.web.entity.Article;

public class ArticleDaoImpl implements ArticleDao {
	private DataSource ds;

//	public ArticleDaoImpl() throws NamingException {
//		ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/NGFW");
//	}

	@Override
	public int add(Article article) {

		String sql = "insert into article (user_id,staff_id,maintitle,subtitle,content,publish_date,status,article_type) value(?,?,?,?,?,?,?,?)";																																					// !!
		try (
//			Connection conn = ds.getConnection();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NGFW_DB","root","123456");
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, article.getUserId());
			pstmt.setInt(2, article.getStaffId());
			pstmt.setString(3, article.getMainTitle());
			pstmt.setString(4, article.getSubTitle());
			pstmt.setString(5, article.getContent());
//			pstmt.setTimestamp(6,Timestamp.valueOf(article.getPublishDate()) );
			pstmt.setTimestamp(6, article.getPublishDate());
			pstmt.setBoolean(7, article.getStatus());
			pstmt.setString(8, article.getArticleType());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

//	@Override
//	public int delectById(Integer user_id) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

//	@Override
//	public int update(User user) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public User selectByAccount(String account) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public User selectByAccountAndPassword(User user) {
//		// TODO for login
//		return null;
//	}
//
//	@Override
//	public List<User> selectAll() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}

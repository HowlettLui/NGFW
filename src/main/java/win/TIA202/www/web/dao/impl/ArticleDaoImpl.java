package win.TIA202.www.web.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
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

		String sql = "insert into article (user_id,staff_id,news_photo,maintitle,subtitle,content,publish_date,status,article_type) value(?,?,?,?,?,?,?,?,?)";
		try (
//			Connection conn = ds.getConnection();
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NGFW_DB","root","123456");
			PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, article.getUserId());
			pstmt.setInt(2, article.getStaffId());
			pstmt.setString(3, article.getNewsPhoto());
			pstmt.setString(4, article.getMainTitle());
			pstmt.setString(5, article.getSubTitle());
			pstmt.setString(6, article.getContent());
			pstmt.setTimestamp(7, article.getPublishDate());
			pstmt.setBoolean(8, article.getStatus());
			pstmt.setString(9, article.getArticleType());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Article> selectAll() {
		String sql = "SELECT * FROM article WHERE status = 1;";
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NGFW_DB","root","123456");
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery(); //查詢
			)
		
		{
			List<Article> list = new ArrayList<>();
			while (rs.next()) {
				Article article = new Article();
				article.setArticleId(rs.getInt("article_id"));
				article.setUserId(rs.getInt("user_id"));
				article.setStaffId(rs.getInt("staff_id"));
				article.setNewsPhoto(rs.getString("news_photo"));
				article.setMainTitle(rs.getString("maintitle"));
				article.setSubTitle(rs.getString("subtitle"));
				article.setContent(rs.getString("content"));
				article.setPublishDate(rs.getTimestamp("publish_date"));
				article.setStatus(rs.getBoolean("status"));
				article.setArticleType(rs.getString("article_type"));
				article.setCreatedTime(rs.getTimestamp("create_time"));
				
				list.add(article);
				
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

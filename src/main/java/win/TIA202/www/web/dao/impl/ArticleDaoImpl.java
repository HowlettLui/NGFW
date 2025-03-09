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
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import win.TIA202.www.web.dao.ArticleDao;
import win.TIA202.www.web.entity.Article;

@Repository
@Transactional
public class ArticleDaoImpl implements ArticleDao {

	@PersistenceContext
	private Session session;

	@Override
	public int add(Article article) {
		
		String hqlString = "INSERT INTO Article(userId, staffId, newsPhoto, mainTitle, subTitle, content, publishDate, status, articleType) " +
				"SELECT :userId, :staffId, :newsPhoto, :mainTitle, :subTitle, :content, :publishDate, :status, :articleType " +
				"FROM Article WHERE articleId = 23";

		Query query = session.createQuery(hqlString);
		query.setParameter("userId", article.getUserId());
		query.setParameter("staffId", article.getStaffId());
		query.setParameter("newsPhoto", article.getNewsPhoto());
		query.setParameter("mainTitle", article.getMainTitle());
		query.setParameter("subTitle", article.getSubTitle());
		query.setParameter("content", article.getContent());
		query.setParameter("publishDate", article.getPublishDate());
		query.setParameter("status", article.getStatus());
		query.setParameter("articleType", article.getArticleType());
		return query.executeUpdate();
		
//		session.persist(article);
//		return article.getArticleId();
		
//		String sql = "insert into article (user_id,staff_id,news_photo,maintitle,subtitle,content,publish_date,status,article_type) value(?,?,?,?,?,?,?,?,?)";
//		try (
////			Connection conn = ds.getConnection();
//			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/NGFW_DB","root","123456");
//			PreparedStatement pstmt = conn.prepareStatement(sql);
//		){
//			pstmt.setInt(1, article.getUserId());
//			pstmt.setInt(2, article.getStaffId());
//			pstmt.setString(3, article.getNewsPhoto());
//			pstmt.setString(4, article.getMainTitle());
//			pstmt.setString(5, article.getSubTitle());
//			pstmt.setString(6, article.getContent());
//			pstmt.setTimestamp(7, article.getPublishDate());
//			pstmt.setBoolean(8, article.getStatus());
//			pstmt.setString(9, article.getArticleType());
//			return pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
	}

	@Override
	public List<Article> selectAll() {
		
		String hql = "from Article where status = 1";
//		String hql = "from Article where status = :status;
		Query<Article> query = session.createQuery(hql, Article.class);
//		query.setParameter("status", 1);
//		List<Article> list =  query.getResultList();
		return query.getResultList();
	}

	
	@Override
	public List<Article> categoryArticle(String articleType) {
		// TODO Auto-generated method stub
		String hql = "from Article where status = 1 AND articleType = :articleType";
		Query<Article> query = session.createQuery(hql, Article.class);
		query.setParameter("articleType", articleType);
		return query.getResultList();
	}
	
	
	@Override
	public Article ViewArticle(Integer articleId) {
		
		String hql = "from Article where articleId = :articleId";
		Query<Article> query = session.createQuery(hql, Article.class);
		query.setParameter("articleId", articleId);

		return query.getSingleResult();
	}



}

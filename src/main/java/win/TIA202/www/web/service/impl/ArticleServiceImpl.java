package win.TIA202.www.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.result.Row;

import win.TIA202.www.web.dao.ArticleDao;
import win.TIA202.www.web.dao.UserDao;
import win.TIA202.www.web.dao.impl.ArticleDaoImpl;
import win.TIA202.www.web.dao.impl.UserDaoImpl;
import win.TIA202.www.web.entity.Article;
import win.TIA202.www.web.service.ArticleService;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
	
	@Autowired
	private ArticleDao articleDao;
	
	@Override
	public List<Article> findAll() {
		return articleDao.selectAll();
	}
	
	@Override
	public List<Article> categoryIndex(String articleType) {
		return articleDao.categoryArticle(articleType);
	}

	@Override
	public Article findIndex(Integer articleId) {
		return articleDao.ViewArticle(articleId);
	}

	@Override
	public List<Article> userMgrList(Integer userId) {
		return articleDao.userMgrArticle(userId);
	}
	
	@Override
	public String add(Article article) {

		int resultConut = articleDao.add(article);

		return resultConut > 0 ? null : "發生錯誤，請聯絡客服";
	}

	@Override
	public String update(Article article) {
		int resultConut = articleDao.update(article);
		return resultConut > 0 ? null : "發生錯誤，請聯絡客服";
	}
	
	@Override
	public Integer userMgrDel(Integer userId, Integer articleId) {
		return articleDao.userDel(userId, articleId);
	}

	@Override
	public List<Article> findAllReview() {
		
		List<Object[]> list = articleDao.selectAllReview();
		
		List<Article> listForReturnAritcles = new ArrayList<>();
		for (Object[] row : list) {
			Article article = (Article)row[0];
			article.setName((String)row[1]);
			listForReturnAritcles.add(article);
		}
		
		return listForReturnAritcles;
	}

	@Override
	public String updateReview(Article article) {
		int resultConut = articleDao.updateReview(article);
		return resultConut > 0 ? null : "發生錯誤，請聯絡客服";
	}

	@Override
	public List<Article> categoryReview(String status) {
//		return articleDao.categoryReview(status);
		
		List<Object[]> list = articleDao.categoryReview(status);
		
		List<Article> listForReturnAritcles = new ArrayList<>();
		for (Object[] row : list) {
			Article article = (Article)row[0];
			article.setName((String)row[1]);
			listForReturnAritcles.add(article);
		}
		
		return listForReturnAritcles;
	}

	

}

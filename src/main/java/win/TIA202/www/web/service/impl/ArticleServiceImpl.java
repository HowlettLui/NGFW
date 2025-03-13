package win.TIA202.www.web.service.impl;

import java.util.List;
import java.util.Objects;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	public String add(Article article) {

		int resultConut = articleDao.add(article);

		return resultConut > 0 ? null : "發生錯誤，請聯絡客服";
	}

	@Override
	public List<Article> findAll() {
		// TODO Auto-generated method stub
		return articleDao.selectAll();
	}
	
	@Override
	public List<Article> categoryIndex(String articleType) {
		// TODO Auto-generated method stub
		return articleDao.categoryArticle(articleType);
	}

	@Override
	public Article findIndex(Integer articleId) {
		// TODO Auto-generated method stub
		return articleDao.ViewArticle(articleId);
	}

	

}

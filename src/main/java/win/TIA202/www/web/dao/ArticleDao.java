package win.TIA202.www.web.dao;

import java.util.List;

import win.TIA202.www.web.entity.Article;

public interface ArticleDao {
	
	int add(Article article);
	
	List<Article> selectAll();
	
	List<Article> categoryArticle(String articleType);
	
	Article ViewArticle(Integer articleId);
	
	
	
//	int delectById(Integer user_id);
	
//	int update(User user);
	
//	User selectByAccount(String account);
	
//	User selectByAccountAndPassword(User user); 
	
//	List<User> selectAll();
	
}

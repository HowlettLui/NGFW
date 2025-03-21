package win.TIA202.www.web.service;

import java.util.List;

import win.TIA202.www.web.entity.Article;
//import win.TIA202.www.web.entity.User;

public interface ArticleService {
	
	String add(Article article);
	
	List<Article> findAll();
	
	List<Article> categoryIndex(String articleType);
	
	Article findIndex(Integer articleId);
	
	List<Article> userMgrList(Integer userId);
	
	String update(Article article);
	
	
	
//	User edit(User user);
//	
//	User login(User user);
//	
//	List<User> findAll();
//	
//	boolean removeById(Integer user_id);
//	
//	boolean selectByEmail(User user);
}

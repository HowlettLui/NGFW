package win.TIA202.www.web.service;

import java.util.List;

import win.TIA202.www.web.entity.Article;
//import win.TIA202.www.web.entity.User;

public interface ArticleService {
	
	List<Article> findAll(); // 顯示所有可呈現文章
	
	List<Article> categoryIndex(String articleType); // 分類新聞/文章
	
	Article findIndex(Integer articleId); // 查文章使呈現
	
	List<Article> userMgrList(Integer userId); // 使用者 查該user文章使呈現
	
	String add(Article article); // 使用者 新增文章
	
	String update(Article article); // 使用者 更新文章
	
	Integer userMgrDel(Integer userId, Integer articleId); // 使用者 刪除文章
	
	List<Article> findAllReview(); // 管理者 呈現文章審核
	
	List<Article> categoryReview(String status); // 管理者 分類文章審核狀態
	
	String updateReview(Article article); // 管理者 文章審核

}

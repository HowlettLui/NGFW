package win.TIA202.www.web.dao;

import java.util.List;

import win.TIA202.www.web.entity.Article;

public interface ArticleDao {

	List<Article> selectAll();  // 顯示所有可呈現文章
	
	List<Article> categoryArticle(String articleType);  // 分類新聞/文章
	
	Article ViewArticle(Integer articleId); // 查文章使呈現
	
	List<Article> userMgrArticle(Integer userId); // 使用者 查該user文章使呈現
	
	int add(Article article); // 使用者 新增文章
	
	int update(Article article); // 使用者 更新文章
	
	int userDel(Integer userId, Integer articleId); // 使用者 刪除文章
	
	List<Object[]> selectAllReview(); // 管理者 呈現文章審核
	
	List<Object[]> categoryReview(String status);  // 管理者 分類文章審核狀態
	
	int updateReview(Article article); // 管理者 文章審核
	
	
	
}

package win.TIA202.www.web.dao;

import java.util.List;

import win.TIA202.www.web.entity.User;

public interface UserDao {
	
	int insert(User user);
	
	int delectById(Integer user_id);
	
	int update(User user);
	
	User selectByAccount(String account);
	
	User selectByAccountAndPassword(User user); 
	
	List<User> selectAll();
	
}

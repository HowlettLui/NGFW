package win.TIA202.www.web.dao;

import win.TIA202.www.web.entity.User;

public interface UserDao {
	
	int insert(User user);
	
	User selectForLogin(String email, String password);

	User selectByAccount(String account); 
	
}

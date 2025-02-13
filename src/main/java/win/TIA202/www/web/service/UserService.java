package win.TIA202.www.web.service;

import java.util.List;

import win.TIA202.www.web.entity.User;

public interface UserService {
	
	String register(User user);
	
	User edit(User user);
	
	User login(User user);
	
	List<User> findAll();
	
	boolean removeById(Integer user_id);
	
	boolean selectByEmail(User user);
}

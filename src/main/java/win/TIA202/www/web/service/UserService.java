package win.TIA202.www.web.service;

import win.TIA202.www.web.entity.User;

public interface UserService {
	
	String register(User user);
	
	User login(User user);

}

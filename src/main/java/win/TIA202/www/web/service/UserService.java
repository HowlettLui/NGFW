package win.TIA202.www.web.service;

import win.TIA202.www.web.entity.User;

public interface UserService {
	
	User register(User user);
	
	User login(User user);

	User edit(User user);

}

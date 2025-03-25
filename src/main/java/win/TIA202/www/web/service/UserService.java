package win.TIA202.www.web.service;

import java.util.List;

import win.TIA202.www.web.entity.User;

public interface UserService {
	
	User register(User user);
	
	User login(User user);

	User edit(User user);

	User editPassword(User user);

	List<User> findAllUser();

	User editUserSR(User user);

}

package win.TIA202.www.web.dao;

import java.util.List;

import win.TIA202.www.web.entity.User;

public interface UserDao {
	
	int insert(User user);

	int update(User user);
	
	User selectForLogin(String email, String password);
	
	User selectById(Integer id);

	User selectByAccount(String account);

	User selectByEmail(String email);

	int updatePassword(User user);

	List<User> selectAllUser();

	User updateUserSR(User user); 
	
}

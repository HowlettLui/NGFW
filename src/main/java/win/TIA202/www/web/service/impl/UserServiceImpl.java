package win.TIA202.www.web.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import win.TIA202.www.web.dao.UserDao;
import win.TIA202.www.web.entity.User;
import win.TIA202.www.web.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

//	public UserServiceImpl() throws NamingException {
//		userDao = new UserDaoImpl();
//	}

	@Override
	public User register(User user) {

		// 資料檢核 or 資料審查
		String account = user.getAccount();
		String password = user.getPassword();
		
		if (userDao.selectByAccount(account) != null) {
			user.setMessage("此使用者名稱已被註冊");
			user.setSuccessfully(false);
			return user;
		}

		if (account == null || account.length() < 5 || account.length() > 30) {
			user.setMessage("使用者名稱長度請介於 5 ~ 30 之間");
			user.setSuccessfully(false);
			return user;
		}

		if (password == null || password.length() < 6 || password.length() > 12) {
			user.setMessage("密碼長度需介於 6 ~ 12 之間");
			user.setSuccessfully(false);
			return user;
		}

		if (!Objects.equals(password, user.getPassword())) {
			user.setMessage("密碼與確認密碼不符");
			user.setSuccessfully(false);
			return user;
		}

		if (userDao.selectByEmail(user.getEmail()) != null) {
			user.setMessage("此電子信箱已被註冊");
			user.setSuccessfully(false);
			return user;
		}
		
		user.setRoleId(8);	// 改roleId
		user.setStatus(0);
		int resultCount = userDao.insert(user);
		if(resultCount < 0) {
			user.setMessage("註冊失敗");
			user.setSuccessfully(false);
			return user;
			
		}else {
			user.setMessage("註冊成功");
			user.setSuccessfully(true);
//			user.setPassword("");
			return user;
		}
	}

	@Override
	public User login(User user) {
		final String email = user.getEmail();
		final String password = user.getPassword();
		
		if (email == null || email.isEmpty()) {
			user.setSuccessfully(false);
			user.setMessage("請輸入電子信箱");
			return user;
		}

		if (password == null || password.isEmpty()) {
			user.setSuccessfully(false);
			user.setMessage("請輸入密碼");
			return user;
		}
		user = userDao.selectForLogin(email, password);
		if (user == null) {
			user = new User();
			user.setMessage("電子信箱或密碼錯誤");
			user.setSuccessfully(false);
			return user;
		}
		user.setMessage("登入成功");
		user.setSuccessfully(true);
//		user.setPassword("");
		return user;
	}

	@Override
	public User edit(User user) {
		final User oUser = userDao.selectById(user.getUserId());
		user.setStatus(oUser.getStatus());
		user.setRoleId(oUser.getRoleId());
		final int resultCount = userDao.update(user);
		user.setSuccessfully(resultCount > 0);
		user.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
//		user.setPassword("");
		return user;
	}

	@Override
	public User editPassword(User user) {
		final User oUser = userDao.selectById(user.getUserId());
		user.setName(oUser.getName());
		user.setPhone(oUser.getPhone());
		user.setEmail(oUser.getEmail());
		user.setStatus(oUser.getStatus());
		user.setRoleId(oUser.getRoleId());
		final int resultCount = userDao.updatePassword(user);
		user.setSuccessfully(resultCount > 0);
		user.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
//		user.setPassword("");
		return user;
	}

	@Override
	public List<User> findAllUser() {
		return userDao.selectAllUser();
	}

	@Override
	public User editUserSR(User user) {
		User upUser = userDao.updateUserSR(user);
		if(user.getUserId() == upUser.getUserId()) {
			upUser.setSuccessfully(true);
			upUser.setMessage("修改成功");
			return upUser;
		} else {
			user.setSuccessfully(false);
			user.setMessage("修改失敗");
			return user;
		}
	}

	@Override
	public User selectById(Integer id) {
		return userDao.selectUserById(id);
	}
}

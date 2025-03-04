package win.TIA202.www.web.service.impl;

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
	public String register(User user) {
		// 資料審查
//		try {
//			if (checkEmailDao.checkUserEmail(user).getUserId() > 0) {
//				return "此帳號已被註冊";
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// 資料檢核 or 資料審查
		String account = user.getAccount();
		String password = user.getPassword();
		
//		if (userDao.selectByAccount(account) != null) {
//			return "此使用者名稱已被註冊";
//		}

//		if (userDao.selectByEmail(user.getEmail()) != null) {
//			user.setMessage("此電子信箱已被註冊");
//			user.setSuccessfully(false);
//			return "此電子信箱已被註冊";
//		}
		
		if (account == null || account.length() < 5 || account.length() > 30) {
			return "使用者名稱長度請介於 5 ~ 30 之間";
		}

		if (password == null || password.length() < 6 || password.length() > 12) {
			return "密碼長度需介於 6 ~ 12 之間";
		}

		if (!Objects.equals(password, user.getPassword())) {
			return "密碼與確認密碼不符";
		}

		int resultConut = userDao.insert(user);

		return resultConut > 0 ? null : "發生錯誤，請聯絡客服";
// -----------------------------------------------------------------------------------------------		
//		if (user.getName() == null) {
//			user.setMessage("使用者名稱未輸入");
//			user.setSuccessfully(false);
//			return user;
//		}
//
//		if (user.getPassword() == null) {
//			user.setMessage("密碼未輸入");
//			user.setSuccessfully(false);
//			return user;
//		}
//
//		if (user.getEmail() == null) {
//			user.setMessage("電子信箱未輸入");
//			user.setSuccessfully(false);
//			return user;
//		}
//
//		try {
//			if (userDao.selectByEmail(user.getEmail()) != null) {
//				user.setMessage("此電子信箱已被註冊");
//				user.setSuccessfully(false);
//				return user;
//			}
//
//			user.setRoleId(2);
//			final int resultCount = userDao.insert(user);
//			if (resultCount < 1) {
//				user.setMessage("註冊錯誤，請聯絡管理員!");
//				user.setSuccessfully(false);
//				return user;
//			}
//
//			user.setMessage("註冊成功");
//			user.setSuccessfully(true);
//			return user;
//		} catch (Exception e) {
//			e.printStackTrace();
//			user.setMessage("註冊錯誤，請聯絡客服");
//			user.setSuccessfully(false);
//			return user;
//		}
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
		return user;
	}

	@Override
	public User edit(User user) {
		final User oUser = userDao.selectByAccount(user.getAccount());
		user.setName(oUser.getName());
		user.setEmail(oUser.getEmail());
		user.setPhone(oUser.getPhone());
		final int resultCount = userDao.update(user);
		user.setSuccessfully(resultCount > 0);
		user.setMessage(resultCount > 0 ? "修改成功" : "修改失敗");
		return user;
	}
}

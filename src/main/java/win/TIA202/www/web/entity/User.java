package win.TIA202.www.web.entity;

import java.sql.Timestamp;

import win.TIA202.www.core.pojo.CoreMsg;

public class User extends CoreMsg {
	private Integer userId; // Corresponds to USER_ID (auto-incremented)
	private String account; // Corresponds to ACCOUNT，use on register & login
	private String password; // Corresponds to PASSWORD
	private String confirmPassword;	// Corresponds to CONFIRMPASSWORD
	private String name; // Corresponds to NAME，use on Estore
	private String email; // Corresponds to EMAIL
	private String phone; // Corresponds to PHONE
	private Boolean status; // Corresponds to STATUS (Account active status)
	private Integer roleId;
	private String oauth;
	private Timestamp createdTime; // Corresponds to CREATED_DATE (Timestamp of creation)

//	預設建構子
	public User() {
		this.name = "SYSTEM";	// !! 暫時插入SYSTEM ，規避DB NO NULL限制 !!
		this.status = false;
		this.roleId = 12;
//		this.oauth = null; // !! 暫時測試使用oauth欄位裝 confirmPassword !!
		this.createdTime = new Timestamp(System.currentTimeMillis());
	}

	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getOauth() {
		return oauth;
	}

	public void setOauth(String oauth) {
		this.oauth = oauth;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

}

package win.TIA202.www.web.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import win.TIA202.www.core.pojo.CoreMsg;

@Entity
public class Staff extends CoreMsg {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STAFF_ID")
	private Integer staffId;
	@Column(name = "STAFF_ACCOUNT")
	private String staffAccount;
	@Column(name = "STAFF_PASSWORD")
	private String staffPassword;
	@Column(name = "STAFF_NAME")
	private String staffName;
	@Column(name = "STAFF_EMAIL")
	private String staffEmail;
	@Column(name = "STAFF_PHONE")
	private String staffPhone;
	@Column(name = "STAFF_STATUS", insertable = false)
	private Boolean staffStatus;
//	@ManyToOne
//	@JoinColumn(name = "STAFF_ROLE_ID", insertable = false, updatable = false)
	@Column(name = "STAFF_ROLE_ID")
	private Integer staffRoleId;
	private String sso;
	@Column(name = "CREATE_TIME", insertable = false)
	private Timestamp createTime;

	public Staff() {
	}

//	public Staff(String staffAccount, Boolean staffStatus, Integer staffRoleId) {
//		this.staffAccount = "SYSTEM";	// !! 暫時插入SYSTEM ，規避DB NO NULL限制 !!
//		this.staffStatus = false;
//		this.staffRoleId = 12;	// 12:default_staff 
//	}

	public Integer getStaffId() {
		return staffId;
	}

	public void setStaffId(Integer staffId) {
		this.staffId = staffId;
	}

	public String getStaffAccount() {
		return staffAccount;
	}

	public void setStaffAccount(String staffAccount) {
		this.staffAccount = staffAccount;
	}

	public String getStaffPassword() {
		return staffPassword;
	}

	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public String getStaffEmail() {
		return staffEmail;
	}

	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}

	public String getStaffPhone() {
		return staffPhone;
	}

	public void setStaffPhone(String staffPhone) {
		this.staffPhone = staffPhone;
	}

	public Boolean getStaffStatus() {
		return staffStatus;
	}

	public void setStaffStatus(Boolean staffStatus) {
		this.staffStatus = staffStatus;
	}

	public Integer getStaffRoleId() {
		return staffRoleId;
	}

	public void setStaffRoleId(Integer staffRoleId) {
		this.staffRoleId = staffRoleId;
	}

	public String getSso() {
		return sso;
	}

	public void setSso(String sso) {
		this.sso = sso;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}

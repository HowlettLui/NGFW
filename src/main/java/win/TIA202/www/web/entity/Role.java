package win.TIA202.www.web.entity;

import java.sql.Timestamp;

import win.TIA202.www.core.pojo.CoreMsg;

//@Entity
//@Table(name = "ROLE")
public class Role extends CoreMsg {
	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@ManyToOne
//	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	private Integer roleId;
//	@Column(name = "ROLE_TYPE")
	private String roleType;
//	@Column(name = "CREATE_TIME", insertable = false)
	private Timestamp createTime;
	
	public Role() {
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}	
}

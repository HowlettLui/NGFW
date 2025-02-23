package win.TIA202.www.web.entity;

import win.TIA202.www.core.pojo.CoreMsg;

import java.sql.Timestamp;

//@Entity
//@Table(name = "ROLE_PERMISSION")
public class RolePermission extends CoreMsg {
	private static final long serialVersionUID = 1L;
//	@Id
//	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	private Integer roleId;
//	@Id
//	@JoinColumn(name = "PERMISSION_ID", referencedColumnName = "PERMISSION_ID")
	private Integer permissionId;

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	private Timestamp createTime;

	public RolePermission() {
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}
}

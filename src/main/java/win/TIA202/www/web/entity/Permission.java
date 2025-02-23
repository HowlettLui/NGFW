package win.TIA202.www.web.entity;

import java.sql.Timestamp;

import win.TIA202.www.core.pojo.CoreMsg;

//@Entity
//@Table(name = "PERMISSION")
public class Permission extends CoreMsg {
	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@ManyToOne
//	@JoinColumn(name = "PERMISSION_ID", referencedColumnName = "PERMISSION_ID")
	private Integer permissionId;
//	@Column(name = "PERMISSION_TYPE")
	private String permissionType;
//	@Column(name = "CREATE_TIME", insertable = false)
	private Timestamp createTime;

	public Permission() {
	}

	public Integer getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Integer permissionId) {
		this.permissionId = permissionId;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
}

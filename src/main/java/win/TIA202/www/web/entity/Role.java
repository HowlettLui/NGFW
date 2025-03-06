package win.TIA202.www.web.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import win.TIA202.www.core.pojo.CoreMsg;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends CoreMsg {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@ManyToOne
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	private Integer roleId;
	@Column(name = "ROLE_TYPE")
	private String roleType;
	@OneToMany
	@Column(name = "PERMISSION_ID")
	@JoinColumn(name = "PERMISSION_ID", referencedColumnName = "PERMISSION_ID")
	private Integer permissionId;
	private List<Permission> permission; 
	@Column(name = "CREATE_TIME", insertable = false)
	private Timestamp createTime;
	
//	public Role() {
//	}
//
//	public Integer getRoleId() {
//		return roleId;
//	}
//
//	public void setRoleId(Integer roleId) {
//		this.roleId = roleId;
//	}
//
//	public String getRoleType() {
//		return roleType;
//	}
//
//	public void setRoleType(String roleType) {
//		this.roleType = roleType;
//	}
//
//	public Timestamp getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Timestamp createTime) {
//		this.createTime = createTime;
//	}
}

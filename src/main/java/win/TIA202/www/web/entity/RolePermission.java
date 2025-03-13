package win.TIA202.www.web.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;

//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class RolePermission {
	
	@Column(name = "ROLE_ID")
	private Integer roleId;
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private List<Role> role;
	
	@Column(name = "PERMISSION")
	private Integer permissionId;
	@JoinColumn(name = "PERMISSON_ID", insertable = false, updatable = false)
	private List<Permission> permission;
}

package win.TIA202.www.web.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RolePermission {
	
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	private Integer roleId;
	@JoinColumn(name = "PERMISSON_ID", referencedColumnName = "PERMISSON_ID")
	private Integer permissionId;
}

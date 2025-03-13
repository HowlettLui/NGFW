package win.TIA202.www.web.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import win.TIA202.www.core.pojo.CoreMsg;


//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
public class Permission extends CoreMsg {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERMISSION_ID")
	private Integer permissionId;
	@Column(name = "PERMISSION_TYPE")
	private String permissionType;
	@Column(name = "CREATE_TIME", insertable = false)
	private Timestamp createTime;
}

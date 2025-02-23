package win.TIA202.www.core.pojo;

import java.io.Serializable;

public class CoreMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	private boolean successfully;
	private String message;
	
	public CoreMsg() {
	}
	
	public CoreMsg(boolean successfully, String message) {
		this.successfully = successfully;
		this.message = message;
	}

	public boolean isSuccessfully() {
		return successfully;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setSuccessfully(boolean successfully) {
		this.successfully = successfully;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}

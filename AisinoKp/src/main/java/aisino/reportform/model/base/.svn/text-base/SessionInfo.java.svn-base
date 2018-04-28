package aisino.reportform.model.base;

/**
 * sessionInfo模型，只要登录成功，就需要设置到session里面，便于系统使用
 * 
 * @author 
 * 
 */
public class SessionInfo implements java.io.Serializable {

	private Syuser user;
	
	private String org_id;
	
	

	public String getOrg_id() {
		return org_id;
	}

	public void setOrg_id(String orgId) {
		org_id = orgId;
	}

	public Syuser getUser() {
		return user;
	}

	public void setUser(Syuser user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return user.getLoginname();
	}

}

package ru.bifutsal.dao.befutsal.bo;

/**
 * Created by itimofeev on 25.11.2017.
 */
public class LoginRequestBo extends AbstractBo {

	private String email;

	private String password;

	private String uid;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}

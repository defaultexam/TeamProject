package com.spring.client.member.vo;

public class MemberSecurity {
	private String userId;
	private String salt;

	public MemberSecurity() {

	}

	public MemberSecurity(String userid, String salt) {
		super();
		this.userId = userid;
		this.salt = salt;
	}

	public String getUserid() {
		return userId;
	}

	public void setUserid(String userid) {
		this.userId = userid;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "MemberSecurity [userid=" + userId + ", salt=" + salt + "]";
	}

}

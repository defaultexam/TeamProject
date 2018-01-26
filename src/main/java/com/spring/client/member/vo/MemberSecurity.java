package com.spring.client.member.vo;

public class MemberSecurity {
	private String userid;
	private String salt;

	public MemberSecurity() {

	}

	public MemberSecurity(String userid, String salt) {
		super();
		this.userid = userid;
		this.salt = salt;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	public String toString() {
		return "MemberSecurity [userid=" + userid + ", salt=" + salt + "]";
	}

}

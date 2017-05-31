package com.po;

import java.sql.Date;

public class User {
	private int userid;
	private String username;
	private String userpasswd;
	private char sex;
	private byte age;
	private String career;
	private String email;
	private Date register_time;
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpasswd() {
		return userpasswd;
	}
	public void setUserpasswd(String userpasswd) {
		this.userpasswd = userpasswd;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public byte getAge() {
		return age;
	}
	public void setAge(byte age) {
		this.age = age;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getRegister_time() {
		return register_time;
	}
	public void setRegister_time(Date register_time) {
		this.register_time = register_time;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username
				+ ", userpasswd=" + userpasswd + ", sex=" + sex + ", age="
				+ age + ", career=" + career + ", email=" + email
				+ ", register_time=" + register_time + "]";
	}
	
}

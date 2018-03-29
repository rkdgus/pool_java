package kr.or.dgit.pool_java.dto;

import java.util.Date;

public class Member {
	private int mno;
	private String name;
	private int age;
	private String tell;
	private Date date;
	private String email;
	private String gender;
	private String pw;
	
	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getTell() {
		return tell;
	}

	public void setTell(String tell) {
		this.tell = tell;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	@Override
	public String toString() {
		return String.format("Member [mno=%s, name=%s, age=%s, tell=%s, date=%s, email=%s, gender=%s, pw=%s]", mno,
				name, age, tell, date, email, gender, pw);
	}


}

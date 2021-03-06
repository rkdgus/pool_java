package kr.or.dgit.pool_java.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import kr.or.dgit.pool_java.service.MemberService;
import kr.or.dgit.pool_java.service.TeacherService;

public class Member {
	private int mno;
	private String name;
	private int age;
	private String tell;
	private Date date;
	private String email;
	private String gender;
	private String pw;
	private String id;
	private boolean isleave;
	

	public boolean isIsleave() {
		return isleave;
	}


	public void setIsleave(boolean isleave) {
		this.isleave = isleave;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


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
		return String.format(
				"Member [mno=%s, name=%s, age=%s, tell=%s, date=%s, email=%s, gender=%s, pw=%s, id=%s, isleave=%s]",
				mno, name, age, tell, date, email, gender, pw, id, isleave);
	}


	public Object[] toArray() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String d = sdf.format(date);
		return new Object[] {mno,name,age,tell,email,gender,d};
	
	}


	public Member(String name, int age, String tell, String email, String gender,String pw) {
		super();
		this.name = name;
		this.age = age;
		this.tell = tell;
		this.email = email;
		this.gender = gender;
		this.pw = pw;
	}
	
	

	


	public Member(int mno, String name, int age, String tell, String email, String gender, String pw) {
		super();
		this.mno = mno;
		this.name = name;
		this.age = age;
		this.tell = tell;
		this.email = email;
		this.gender = gender;
		this.pw = pw;
	}


	public Member(int mno, String name, int age, String tell, String email, String gender) {
		super();
		this.mno = mno;
		this.name = name;
		this.age = age;
		this.tell = tell;
		this.email = email;
		this.gender = gender;
	}


	public Member() {}
	
	
	
	


}

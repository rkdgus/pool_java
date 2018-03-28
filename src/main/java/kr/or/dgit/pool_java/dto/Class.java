package kr.or.dgit.pool_java.dto;

import java.util.Date;

public class Class {
	private int cno;
	private int classmate;
	private String time;
	private String level;
	private int pay;
	private int tno;
	private Date s_day;
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getClassmate() {
		return classmate;
	}
	public void setClassmate(int classmate) {
		this.classmate = classmate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public int getPay() {
		return pay;
	}
	public void setPay(int pay) {
		this.pay = pay;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public Date getS_day() {
		return s_day;
	}
	public void setS_day(Date s_day) {
		this.s_day = s_day;
	}
	@Override
	public String toString() {
		return "Class [cno=" + cno + ", classmate=" + classmate + ", time=" + time + ", level=" + level + ", pay=" + pay
				+ ", tno=" + tno + ", s_day=" + s_day + "]";
	}
	public Class() {}
	
}

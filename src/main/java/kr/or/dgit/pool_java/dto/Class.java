package kr.or.dgit.pool_java.dto;

import java.util.Date;

import kr.or.dgit.pool_java.service.RegisterService;
import kr.or.dgit.pool_java.service.TeacherService;

public class Class {
	private int cno;
	private int classmate;
	private String time;
	private String level;
	private int pay;
	private int tno;
	private Date s_day;
	private boolean reclass;
	
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
	
	public boolean isReclass() {
		return reclass;
	}
	public void setReclass(boolean reclass) {
		this.reclass = reclass;
	}
	
	
	
	@Override
	public String toString() {
		return  cno + "/" + time.replace("00분", "") + ", " + level ;
	}
	public Class() {}
	
	public Object[] toArray() {
		Teacher t = TeacherService.getInstance().selectByNo(tno);
		int thisclassmate = RegisterService.getInstance().selectByCountCno(cno);
		return new Object[] {cno,time,t.getName(),level,thisclassmate+"/"+classmate};
	}
	
}

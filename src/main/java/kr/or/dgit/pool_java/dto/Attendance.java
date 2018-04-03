package kr.or.dgit.pool_java.dto;

import java.util.Date;

import kr.or.dgit.pool_java.dao.MemberDao;
import kr.or.dgit.pool_java.service.MemberService;

public class Attendance {
	public Date date;
	public int mno;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	@Override
	public String toString() {
		return String.format("Attendance [date=%s, mno=%s]", date, mno);
	}

	public Attendance() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attendance(Date date, int mno) {
		this.date = date;
		this.mno = mno;
	}

}

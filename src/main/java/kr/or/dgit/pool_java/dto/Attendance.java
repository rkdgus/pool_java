package kr.or.dgit.pool_java.dto;

import java.util.Date;

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

}

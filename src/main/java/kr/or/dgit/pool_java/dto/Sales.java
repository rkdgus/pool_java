package kr.or.dgit.pool_java.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.logging.log4j.message.SimpleMessage;

public class Sales {
	private String sno;
	private int day;
	private int pay;

	public Sales() {
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getPay() {
		return pay;
	}

	public void setPay(int pay) {
		this.pay = pay;
	}

	@Override
	public String toString() {
		return String.format("Sale [day=%s, pay=%s]", day, pay);
	}

	public Object[] toArray() {
		String day1 = String.valueOf(day).substring(0, 4);
		String day2 = String.valueOf(day).substring(4, 6);
		String day3 = String.valueOf(day).substring(6);
		
		return new Object[] { day1+"-"+day2+"-"+day3, pay };
	}

}

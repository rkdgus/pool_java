package kr.or.dgit.pool_java.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Sales {
	private int sno;
	private Date day;
	private int pay;

	public Sales() {
	}

	public int getSno() {
		return sno;
	}

	public void setSno(int sno) {
		this.sno = sno;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
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
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:dd:ss");
		
		return new Object[] { sno,sd.format(day), pay };
	}

}

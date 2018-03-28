package kr.or.dgit.pool_java.dto;

import java.util.Date;

public class Sales {
	private Date day;
	private int pay;
	
	public Sales() {}

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
	

}

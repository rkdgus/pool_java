package kr.or.dgit.pool_java.dto;

public class Locker {
	private int lno;
	private boolean able;
	private String gender;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Locker() {
	}

	public int getLno() {
		return lno;
	}

	public void setLno(int lno) {
		this.lno = lno;
	}

	public boolean isAble() {
		return able;
	}

	public void setAble(boolean able) {
		this.able = able;
	}

	@Override
	public String toString() {
		return String.format("Locker [lno=%s, able=%s, gender=%s]", lno, able, gender);
	}

}

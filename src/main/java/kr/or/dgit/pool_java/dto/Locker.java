package kr.or.dgit.pool_java.dto;

public class Locker {
	private int lno;
	private boolean able;
	
	public Locker() {}

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
		return String.format("LockerRoom [lno=%s, able=%s]", lno, able);
	}
	
}

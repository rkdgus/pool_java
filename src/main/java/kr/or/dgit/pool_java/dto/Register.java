package kr.or.dgit.pool_java.dto;

public class Register {
	private int mno;
	private int cno;
	private boolean reentrance;
	
	public Register() {}

	public int getMno() {
		return mno;
	}

	public void setMno(int mno) {
		this.mno = mno;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public boolean isReentrance() {
		return reentrance;
	}

	public void setReentrance(boolean reentrance) {
		this.reentrance = reentrance;
	}

	@Override
	public String toString() {
		return String.format("Register [mno=%s, cno=%s, reentrance=%s]", mno, cno, reentrance);
	}
	
	
	
}

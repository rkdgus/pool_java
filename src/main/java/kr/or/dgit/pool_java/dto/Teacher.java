package kr.or.dgit.pool_java.dto;

public class Teacher {
	private int tno;
	private String name;
	private String tell;
	private String title;
	private String pw;
	
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTell() {
		return tell;
	}
	public void setTell(String tell) {
		this.tell = tell;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
	@Override
	public String toString() {
		return String.format("Teacher [tno=%s, name=%s, tell=%s, title=%s, pw=%s]", tno, name, tell, title, pw);
	}
	public Teacher(int tno, String name, String tell, String title) {
		super();
		this.tno = tno;
		this.name = name;
		this.tell = tell;
		this.title = title;
	}
	public Teacher() {}
	
}

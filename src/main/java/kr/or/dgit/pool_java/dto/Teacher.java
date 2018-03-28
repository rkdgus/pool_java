package kr.or.dgit.pool_java.dto;

public class Teacher {
	private int tno;
	private String name;
	private String tell;
	private String title;
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
	@Override
	public String toString() {
		return "Teacher [tno=" + tno + ", name=" + name + ", tell=" + tell + ", title=" + title + "]";
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

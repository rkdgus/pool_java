package kr.or.dgit.pool_java.dto;

import java.text.SimpleDateFormat;

public class Teacher {
	private int tno;
	private String name;
	private String tell;
	private String title;
	private String pw;
	private String id;
	private boolean quit;
	
	
	public boolean isQuit() {
		return quit;
	}
	public void setQuit(boolean quit) {
		this.quit = quit;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
		return String.format("Teacher [tno=%s, name=%s, tell=%s, title=%s, pw=%s, id=%s]", tno, name, tell, title, pw,
				id);
	}
	public Teacher(int tno, String name, String tell, String title) {
		super();
		this.tno = tno;
		this.name = name;
		this.tell = tell;
		this.title = title;
	}
	
	
	public Teacher(String name, String tell, String title, String pw) {
		super();
		
		this.name = name;
		this.tell = tell;
		this.title = title;
		this.pw = pw;
	}
	public Teacher() {}
	
	public Object[] toArray() {
		return new Object[] {tno,name,tell,title};
	
	}
	
}

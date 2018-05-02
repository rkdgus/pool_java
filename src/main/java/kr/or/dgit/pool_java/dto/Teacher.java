package kr.or.dgit.pool_java.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import kr.or.dgit.pool_java.service.ClassService;
import kr.or.dgit.pool_java.service.RegisterService;

public class Teacher {
	private int tno;
	private String name;
	private String tell;
	private String title;
	private String pw;
	private String id;

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
	
	

	public Teacher(String name, String tell, String title, String pw, String id) {
		super();
		this.name = name;
		this.tell = tell;
		this.title = title;
		this.pw = pw;
		this.id = id;
	}

	public Teacher(int tno, String name, String tell, String title, String pw) {
		super();
		this.tno = tno;
		this.name = name;
		this.tell = tell;
		this.title = title;
		this.pw = pw;
	}

	public Teacher(String name, String tell, String title, String pw) {
		super();

		this.name = name;
		this.tell = tell;
		this.title = title;
		this.pw = pw;
	}
	
	

	public Teacher(int tno, String name, String tell, String title, String pw, String id) {
		super();
		this.tno = tno;
		this.name = name;
		this.tell = tell;
		this.title = title;
		this.pw = pw;
		this.id = id;
	}

	public Teacher() {
	}

	public Object[] toArray() {

		Date d = new Date();
		d.setMonth(d.getMonth() - 1);
		Calendar cal = Calendar.getInstance();
		if (cal.get(Calendar.DATE) >= 20) {
			d.setMonth(d.getMonth());
		}

		d.setDate(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Class c = new Class();
		c.setTno(tno);
		c.setLevel(sdf.format(d));
		int totalCount = RegisterService.getInstance().selectByTnoCount(c);
		d.setMonth(d.getMonth() + 1);
		c.setLevel(sdf.format(d));
		int re = RegisterService.getInstance().reenter(c);

		System.out.println(name + " = " + totalCount + " : " + re);
		double percent = 0.00;
		if (totalCount == 0 || re == 0) {
			percent = 0;
		} else {
			if (re / totalCount == 1) {
				percent = 100;
			} else {
				percent = ((double) re / (double) totalCount) * 100;
			}

		}

		return new Object[] { tno, name, tell, title, String.format("%1$,.1f", percent) + "%" };

	}

}

package kr.or.dgit.pool_java.service;

import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import kr.or.dgit.pool_java.dto.Class;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCarDataService2 {
	private static ClassService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = service.getInstance();
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}
	/*@Test
	public void test1InsertMemberData() {
		List<Class> lists = service.selectByreclass(true);
		for(Class c : lists) {
			Date date = new Date();
			date.setMonth(date.getMonth()+1);
			date.setDate(1);
			c.setS_day(date);
			service.updateReclass(c);
		}
	}*/
}

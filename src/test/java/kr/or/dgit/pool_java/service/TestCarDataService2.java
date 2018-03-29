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

	@Test
	public void test1InsertMemberData() {
		
		Class cls =service.selectByNo(1);
		System.out.println(cls);
		
	}
}
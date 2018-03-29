package kr.or.dgit.pool_java.service;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.pool_java.dto.Teacher;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestCarDataService2 {
	private static TeacherService service;
	
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
		Teacher teacher = new Teacher();
		teacher.setName("테");
		
		List<Teacher> list = service.selectByNameOrtitle(teacher);
		
		for(Teacher t : list) {
			System.out.println(t.toString());
		}
	}
}

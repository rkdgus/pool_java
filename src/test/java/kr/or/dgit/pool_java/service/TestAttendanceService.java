package kr.or.dgit.pool_java.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.pool_java.dto.Member;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAttendanceService {
	private static MemberService service;
	
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
		Member member = new Member();
		member.setName("강현");
		member.setAge(12);
		member.setGender("남");
		member.setTell("010-9287-3004");
		System.out.println(member);
		service.insertMember(member);
	}
	
	/*@Test
	public void test1InsertCarData() {
		CarData carData = new CarData();
		carData.setCarCode("aaaaa");
		carData.setCarModelCode(new CarModel("RUV", "스포티지"));
		carData.setManufacturerCode(new Manufacturer("K", "KIA"));
		carData.setFuelCode(new Fuel("G", "가솔린"));
		carDataService.insertCarData(carData);
	}
	
	@Test
	public void test2FindSelectCarDataByCarDataCode() {
		CarData carData = new CarData("CC-K-G-001");
		CarData findCarData = carDataService.selectCarDataByCarDataCode(carData);
		
	}*/
	
/*	@Test
	public void test3FindSelectCarDataByAll() {
		List<CarData> list = carDataService.selectCarDataByAll();
		Assert.assertNotNull(list);
	
		for(CarData cd : list) {
			System.out.println(cd.getFuelCode().getFuelCode());
		}
	}*/
	
	/*@Test
	public void test4UpdeteCarDataByCarDataCode() {
		CarData carData = new CarData("CC-H-G-006","aa");
		carDataService.updateCarData(carData);
	}
	
	@Test
	public void test5DeletCarDataByCarDataCode() {
		CarData carData = new CarData();
		carData.setCarCode("aaaaa");
		carDataService.deleteCarData(carData);
	}*/
	
	/*@Test
	public void test1SelectCarDataByWhereAll() {
		CarData carData = new CarData();
		List<CarData> list = carDataService.selectCarDataByCarValue(null);
		Assert.assertNotNull(list);
		JOptionPane.showMessageDialog(null, "Asdfasdf");
		for(CarData cd : list) {
			JOptionPane.showMessageDialog(null, cd);
			
		}
	}*/

}

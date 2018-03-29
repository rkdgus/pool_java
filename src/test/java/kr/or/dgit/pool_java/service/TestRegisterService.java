package kr.or.dgit.pool_java.service;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.pool_java.dto.Register;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRegisterService {
	private static RegisterService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = service.getInstance();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		service = null;
	}

	//@Test 
	public void test1InsertLockerData() {
		
	}
	
	@Test
	public void test1InsertSalesData() {
		Register register = new Register();
		register.setCno(1);
		register.setMno(1);
		register.setReentrance(true);
		service.insertRegister(register);
	}
	
	/*@Test
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

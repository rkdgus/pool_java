package kr.or.dgit.pool_java.service;

import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import kr.or.dgit.pool_java.dto.Locker;
import kr.or.dgit.pool_java.dto.Sales;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSalsesService {
	private static SalesService service;
	
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
		System.out.println(service.selectAll());
	}
	
/*	@Test
	public void test1InsertSalesData() {
		Sales sales = new Sales();
		sales.setDay(new Date());
		sales.setPay(10000);
		service.insertSales(sales);
	}
	*/
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

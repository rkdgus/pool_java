package kr.or.dgit.pool_java.dao;

import java.util.List;

import kr.or.dgit.pool_java.dto.Sales;

public interface SalesDao {
	public List<Sales> selectAll();
	public int insertSales(Sales sales);
	public int updateSales(Sales sales);
	public int deleteSales(Sales sales);
}

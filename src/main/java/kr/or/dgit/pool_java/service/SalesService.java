package kr.or.dgit.pool_java.service;

import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.pool_java.dao.SalesDao;
import kr.or.dgit.pool_java.dto.Sales;
import kr.or.dgit.pool_java.util.MyBatisSqlSessionFactory;

public class SalesService implements SalesDao {
	private static final SalesService INSTANCE = new SalesService();
	private SalesDao dao;
	
	public static SalesService getInstance() {
		return INSTANCE;
	}
	
	private SalesService() {}

	@Override
	public List<Sales> selectAll() {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(SalesDao.class);
			return dao.selectAll();
		}
	}

	@Override
	public int insertSales(Sales sales) {
		int res  = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(SalesDao.class);
		
			dao.insertSales(sales);
			sqlsession.commit();
			
			JOptionPane.showMessageDialog(null, "추가되었습니다");
			res = 1;
				
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");
			
		}
		return res;
	}

	@Override
	public int updateSales(Sales sales) {
		int res  = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(SalesDao.class);
		
			dao.updateSales(sales);
			sqlsession.commit();
			
			JOptionPane.showMessageDialog(null, "추가되었습니다");
			res = 1;
				
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");
			
		}
		return res;
	}

	@Override
	public int deleteSales(int sales) {
		int res  = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(SalesDao.class);
		
			dao.deleteSales(sales);
			sqlsession.commit();
			
			JOptionPane.showMessageDialog(null, "삭제되었습니다");
			res = 1;
				
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다");
			
		}
		return res;
	}

	@Override
	public List<Sales> selectDate(String day) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(SalesDao.class);
			return dao.selectDate("%"+day+"%");
		}
	}

	@Override
	public int selectSum(String day) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(SalesDao.class);
			return dao.selectSum("%"+day+"%");
		}
	}

}

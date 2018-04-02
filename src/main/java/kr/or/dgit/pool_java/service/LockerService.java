package kr.or.dgit.pool_java.service;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.pool_java.dao.LockerDao;
import kr.or.dgit.pool_java.dao.MemberDao;
import kr.or.dgit.pool_java.dto.Locker;
import kr.or.dgit.pool_java.util.MyBatisSqlSessionFactory;

public class LockerService implements LockerDao {

	private static final LockerService INSTANCE = new LockerService();
	private LockerDao dao;
	
	public static LockerService getInstance() {
		return INSTANCE;
	}
	private LockerService() {}

	@Override
	public List<Locker> selectAll() {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(LockerDao.class);
			return dao.selectAll();
		}
	}

	@Override
	public int insertLocker(Locker locker) {
		int res  = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(LockerDao.class);
		
			dao.insertLocker(locker);
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
	public int updateLocker(Locker locker) {
		int res = -1;
		
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(LockerDao.class);
		
			dao.updateLocker(locker);
			sqlsession.commit();
			
			JOptionPane.showMessageDialog(null, "수정되었습니다");
			res = 1;
				
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "수정를 실패하였습니다");
			
		}
		return res;
	}
	@Override
	public Locker selectGender(Locker locker) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(LockerDao.class);
			return dao.selectGender(locker);
		}
	}

}

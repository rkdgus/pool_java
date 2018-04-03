package kr.or.dgit.pool_java.service;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.pool_java.dao.AttendanceDao;
import kr.or.dgit.pool_java.dto.Attendance;
import kr.or.dgit.pool_java.util.MyBatisSqlSessionFactory;

public class AttendanceService implements AttendanceDao{
	private static final AttendanceService INSTANCE = new AttendanceService();
	private AttendanceDao dao;

	public static AttendanceService getInstance() {
		return INSTANCE;
	}

	private AttendanceService() {
	
	}

	@Override
	public List<Attendance> selectAll() {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(AttendanceDao.class);
			return dao.selectAll();
		}
	}

	@Override
	public void insertAttendance(Attendance attendance) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(AttendanceDao.class);
			dao.insertAttendance(attendance);
			sqlsession.commit();
			JOptionPane.showMessageDialog(null, "추가되었습니다");
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");
		}
		
		
	}

	@Override
	public void deleteAttendance(int mno) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(AttendanceDao.class);
			dao.deleteAttendance(mno);
			sqlsession.commit();
			JOptionPane.showMessageDialog(null, "삭제되었습니다");
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다");
		}
		
	}

	@Override 
	public List<Attendance> selectDate(HashMap<String, Object> map) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(AttendanceDao.class);
			return dao.selectDate(map);
		}
	}

	

}

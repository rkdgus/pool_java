package kr.or.dgit.pool_java.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.pool_java.dao.ClassDao;
import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.util.MyBatisSqlSessionFactory;

public class ClassService implements ClassDao {
	private static final ClassService INSTANCE = new ClassService();
	public static ClassService getInstance() {
		return INSTANCE;
	}
	private ClassDao dao;


	@Override
	public int insertClass(Class cls) {
		int res = -1;
		try(SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			dao = sqlsession.getMapper(ClassDao.class);
			dao.insertClass(cls);
			sqlsession.commit();
			res = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int deleteClass(int cno) {
		int res = -1;
		try(SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			dao = sqlsession.getMapper(ClassDao.class);
			dao.deleteClass(cno);
			sqlsession.commit();
			res = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public Class selectByNo(int cno) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(ClassDao.class);
			return dao.selectByNo(cno);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateClass(Class cls) {
		int res = -1;
		try(SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();){
			dao = sqlsession.getMapper(ClassDao.class);
			dao.updateClass(cls);
			sqlsession.commit();
			res = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Class> selectBytoMonth(String s_day) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			Date date = new Date();
			date.setDate(1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			s_day = sdf.format(date);
			System.out.println(s_day);
			dao = sqlsession.getMapper(ClassDao.class);
			return dao.selectBytoMonth(s_day);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Class> selectByNextMonth(String s_day) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			Date date = new Date();
			date.setDate(1);
			date.setMonth(date.getMonth()+1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			s_day = sdf.format(date);
			System.out.println(s_day);
			dao = sqlsession.getMapper(ClassDao.class);
			return dao.selectByNextMonth(s_day);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

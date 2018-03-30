package kr.or.dgit.pool_java.service;

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
	public List<Class> selectByAll() {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(ClassDao.class);
			return dao.selectByAll();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

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

}

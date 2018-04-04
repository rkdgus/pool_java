package kr.or.dgit.pool_java.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.pool_java.dao.MemberDao;
import kr.or.dgit.pool_java.dao.TeacherDao;
import kr.or.dgit.pool_java.dto.Teacher;
import kr.or.dgit.pool_java.util.MyBatisSqlSessionFactory;

public class TeacherService implements TeacherDao {
	private static final TeacherService INSTANCE = new TeacherService();
	public static TeacherService getInstance() {
		return INSTANCE;
	}
	private TeacherDao dao;
	@Override
	public List<Teacher> selectByAll() {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			return dao.selectByAll();
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insertTeacher(Teacher teacher) {
		int res = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			dao.insertTeacher(teacher);
			sqlsession.commit();
			res = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Teacher> selectByNameOrtitle(Teacher teacher) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			if(teacher.getName() !=null) {
				teacher.setName("%"+teacher.getName()+"%");
			}
			dao = sqlsession.getMapper(TeacherDao.class);
			return dao.selectByNameOrtitle(teacher);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Teacher selectByNo(int tno) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			return dao.selectByNo(tno);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int updateTeacher(Teacher teacher) {
		int res = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			dao.updateTeacher(teacher);
			sqlsession.commit();
			res = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	@Override
	public int deleteTeacher(int tno) {
		int res = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			dao.deleteTeacher(tno);
			sqlsession.commit();
			res = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Teacher> searchTitle(String title) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			return dao.searchTitle(title);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Teacher> searchName(String name) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			return dao.searchName(name);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int quitTeacher(Teacher teacher) {
		int res = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			dao.quitTeacher(teacher);
			sqlsession.commit();
			res = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public List<Teacher> findId(String id) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			return dao.findId(id);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Teacher login(Teacher teacher) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(TeacherDao.class);
			return dao.login(teacher);
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

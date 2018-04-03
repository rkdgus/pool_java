package kr.or.dgit.pool_java.service;

import java.util.HashMap;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.pool_java.dao.RegisterDao;
import kr.or.dgit.pool_java.dao.SalesDao;
import kr.or.dgit.pool_java.dto.Register;
import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.util.MyBatisSqlSessionFactory;

public class RegisterService implements RegisterDao {

	private static final RegisterService INSTANCE = new RegisterService();
	private RegisterDao dao;

	public static RegisterService getInstance() {
		return INSTANCE;
	}

	@Override
	public List<Register> selectAll() {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(RegisterDao.class);
			return dao.selectAll();
		}
	}

	@Override
	public int insertRegister(Register register) {
		int res = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(RegisterDao.class);

			dao.insertRegister(register);
			sqlsession.commit();

			JOptionPane.showMessageDialog(null, "추가되었습니다");
			res = 1;

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");

		}
		return res;
	}

	@Override
	public int updateRegister(Register register) {
		int res = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(RegisterDao.class);

			dao.updateRegister(register);
			sqlsession.commit();

			JOptionPane.showMessageDialog(null, "추가되었습니다");
			res = 1;

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");

		}
		return res;
	}

	@Override
	public int deleteRegister(int cno) {
		int res = -1;
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(RegisterDao.class);

			dao.deleteRegister(cno);
			sqlsession.commit();

			JOptionPane.showMessageDialog(null, "추가되었습니다");
			res = 1;

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");

		}
		return res;
	}

	@Override
	public int selectByCountCno(int cno) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(RegisterDao.class);

			return dao.selectByCountCno(cno);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");
			return -1;
		}
	}

	@Override
	public Class selectByMno(HashMap<String, Object> map) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(RegisterDao.class);
			return dao.selectByMno(map);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");
			return null;
		}
	}

	@Override
	public void updateReenter(Register register) {

		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(RegisterDao.class);

			dao.updateReenter(register);
			sqlsession.commit();

			JOptionPane.showMessageDialog(null, "추가되었습니다");

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");

		}

	}

	@Override
	public int selectByTnoCount(Class c) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(RegisterDao.class);

			return dao.selectByTnoCount(c);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;

		}
	}

	@Override
	public int reenter(Class c) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(RegisterDao.class);

			return dao.reenter(c);

		} catch (Exception e) {
			e.printStackTrace();
			return -1;

		}
	}

	@Override
	public List<Register> selectByCno(int cno) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(RegisterDao.class);
			return dao.selectByCno(cno);
		}
	}

	@Override
	public void changeClass(HashMap<String, Object> map) {
	
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(RegisterDao.class);
			
			dao.changeClass(map);
			sqlsession.commit();

			JOptionPane.showMessageDialog(null, "수정 되었습니다");
			

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "수정에 실패하였습니다");

		}
		
	}

}

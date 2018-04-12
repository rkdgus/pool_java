package kr.or.dgit.pool_java.service;

import java.util.List;

import javax.swing.JOptionPane;

import org.apache.ibatis.session.SqlSession;

import kr.or.dgit.pool_java.dao.MemberDao;
import kr.or.dgit.pool_java.dto.Member;
import kr.or.dgit.pool_java.util.MyBatisSqlSessionFactory;

public class MemberService implements MemberDao{
	private static final MemberService INSTANCE = new MemberService();
	private MemberDao dao;

	public static MemberService getInstance() {
		return INSTANCE;
	}

	private MemberService() {
	
	}
	@Override
	public List<Member> selectAll() {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(MemberDao.class);
			return dao.selectAll();
		}
	}

	@Override
	public void insertMember(Member member) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(MemberDao.class);
			dao.insertMember(member);
			sqlsession.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void deleteMember(int mno) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(MemberDao.class);
			dao.deleteMember(mno);
			sqlsession.commit();
			JOptionPane.showMessageDialog(null, "삭제되었습니다");
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "삭제에 실패하였습니다");
		}
		
	}

	@Override
	public void updateMember(Member member) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(MemberDao.class);
			dao.updateMember(member);
			sqlsession.commit();
			JOptionPane.showMessageDialog(null, "수정되었습니다");
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "수정에 실패하였습니다");
		}
		
	}

	@Override
	public Member selectMno(int mno) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(MemberDao.class);
			return dao.selectMno(mno);
		}
	}

	@Override
	public List<Member> selectSearchName(String name) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(MemberDao.class);
			return dao.selectSearchName(name);
		}
	}

	@Override
	public List<Member> selectSearchCno(int cno) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(MemberDao.class);
			return dao.selectSearchCno(cno);
		}
	}

	@Override
	public void udpateleave(int mno) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession()) {
			dao = sqlsession.getMapper(MemberDao.class);
			dao.udpateleave(mno);
			sqlsession.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<Member> noMember() {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(MemberDao.class);
			return dao.noMember();
		}
	}

	@Override
	public Member selectEmailCheck(String email) {
		try (SqlSession sqlsession = MyBatisSqlSessionFactory.getSqlSessionFactory().openSession();) {
			dao = sqlsession.getMapper(MemberDao.class);
			return dao.selectEmailCheck(email);
		}
	}


}

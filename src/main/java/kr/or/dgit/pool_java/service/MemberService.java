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
			JOptionPane.showMessageDialog(null, "추가되었습니다");
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가를 실패하였습니다");
		}
		
	}

}

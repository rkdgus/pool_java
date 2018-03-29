package kr.or.dgit.pool_java.dao;

import java.util.List;

import kr.or.dgit.pool_java.dto.Member;

public interface MemberDao {
	public List<Member> selectAll();
	public void insertMember(Member member);
	public void deleteMember(int mno);
	public void updateMember(Member member);
}
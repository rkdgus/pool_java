package kr.or.dgit.pool_java.dao;

import java.util.HashMap;
import java.util.List;

import kr.or.dgit.pool_java.dto.Class;
import kr.or.dgit.pool_java.dto.Register;

public interface RegisterDao {
	public List<Register> selectAll();
	public int insertRegister(Register register);
	public int updateRegister(Register register);
	public int deleteRegister(int cno);
	public int selectByCountCno(int cno);
	public Class selectByMno(HashMap<String, Object> map);
	public void updateReenter(Register register);
}

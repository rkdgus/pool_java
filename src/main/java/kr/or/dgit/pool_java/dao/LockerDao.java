package kr.or.dgit.pool_java.dao;

import java.util.List;

import kr.or.dgit.pool_java.dto.Locker;

public interface LockerDao {
	public List<Locker> selectAll();
	public int insertLocker(Locker locker);
	public int updateLocker(boolean able);
}

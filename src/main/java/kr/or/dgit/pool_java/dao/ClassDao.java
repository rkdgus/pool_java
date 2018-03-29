package kr.or.dgit.pool_java.dao;

import java.util.List;
import kr.or.dgit.pool_java.dto.Class;
public interface ClassDao {
	public List<Class> selectByAll();
	public int insertClass(Class cls);
	public int deleteClass(int cno);
	public Class selectByNo(int cno);
}

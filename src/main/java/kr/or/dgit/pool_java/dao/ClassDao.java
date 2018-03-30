package kr.or.dgit.pool_java.dao;

import java.util.List;
import kr.or.dgit.pool_java.dto.Class;
public interface ClassDao {
	public List<Class> selectBytoMonth(String s_day);
	public List<Class> selectByNextMonth(String s_day);
	public int insertClass(Class cls);
	public int deleteClass(int cno);
	public Class selectByNo(int cno);
	public int updateClass(Class cls);
}

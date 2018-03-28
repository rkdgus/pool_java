package kr.or.dgit.pool_java.dao;

import java.util.List;

import kr.or.dgit.pool_java.dto.Teacher;

public interface TeacherDao {
	public List<Teacher> selectByAll();
	public int insertTeacher(Teacher teacher);
	public List<Teacher> selectByNameOrtitle(Teacher teacher);
	public Teacher selectByNo(int tno);
	public int updateTeacher(Teacher teacher);
	public int deleteTeacher(int tno);
}

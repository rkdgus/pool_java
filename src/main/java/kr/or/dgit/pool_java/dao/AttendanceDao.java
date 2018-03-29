package kr.or.dgit.pool_java.dao;

import java.util.List;

import kr.or.dgit.pool_java.dto.Attendance;

public interface AttendanceDao {
	public List<Attendance> selectAll();
	public void insertAttendance(Attendance attendance);
	public void deleteAttendance(int mno);
}

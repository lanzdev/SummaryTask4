package com.lanzdev.dao.entity;

import com.lanzdev.dao.GenericDao;
import com.lanzdev.domain.entity.CourseStudents;

import java.util.List;

public interface CourseStudentsDao extends GenericDao<CourseStudents, Integer> {

    List<CourseStudents> getByCourseId(Integer courseId);

    List<CourseStudents> getByStudentId(Integer studentId);

}

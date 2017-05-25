package com.lanzdev.dao.entity;

import com.lanzdev.dao.GenericDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.User;
import com.lanzdev.util.ProgressCortege;

import java.util.List;

public interface CourseDao extends GenericDao<Course, Integer> {

    List<Course> getBySubject(int subjectId);

    List<Course> getByTeacher(int teacherId);

    List<Course> getSelectedNotStartedCourses(User student);

    List<Course> getSelectedCoursesInProcess(User student);

    List<ProgressCortege> getDoneCourses(User student);
}

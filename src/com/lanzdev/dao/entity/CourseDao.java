package com.lanzdev.dao.entity;

import com.lanzdev.dao.GenericDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Subject;
import com.lanzdev.domain.entity.User;
import com.lanzdev.util.AssignCortege;
import com.lanzdev.util.ProgressCortege;

import java.util.List;

public interface CourseDao extends GenericDao<Course, Integer> {

    /**
     * Used for getting a {@link Course} list by {@link Subject subjects} object
     * @param subject
     * @return
     */
    List<Course> getBySubject(Subject subject);

    /**
     * Used for getting a {@link Course} list by {@link User teachers}  object
     * @param teacher
     * @return
     */
    List<Course> getByTeacher(User teacher);

    /**
     * Used for getting a {@link Course} list of not subscribed {@link Course courses} by
     * {@link User student}
     * @param student
     * @return
     */
    List<Course> getNotSubscribed(User student);

    /**
     * Used for getting a {@link AssignCortege} list by {@link User teachers} object
     * @param teacher
     * @return
     */
    List<AssignCortege> getAssignedCourses(User teacher);

    /**
     * Used for getting a {@link AssignCortege} list with avg marks for each {@link Course}
     * by {@link User teacher} object
     * @param teacher
     * @return
     */
    List<AssignCortege> getAssignedWithAvgMark(User teacher);


    /**
     * Used for getting a {@link Course} list of not started {@link Course courses}
     * by {@link User student}
     * @param student
     * @return
     */
    List<Course> getSelectedNotStartedCourses(User student);

    /**
     * Used for getting a {@link Course} list of {@link Course courses} which are in process
     * by {@link User student}
     * @param student
     * @return
     */
    List<Course> getSelectedCoursesInProcess(User student);

    /**
     * Used for getting a {@link ProgressCortege} list of done courses by {@link User student}
     * @param student
     * @return
     */
    List<ProgressCortege> getDoneCourses(User student);
}

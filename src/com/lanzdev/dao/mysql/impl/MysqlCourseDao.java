package com.lanzdev.dao.mysql.impl;

import com.lanzdev.dao.ConnectionPool;
import com.lanzdev.dao.entity.CourseDao;
import com.lanzdev.dao.mysql.AbstractMysqlDao;
import com.lanzdev.dao.mysql.Query;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Journal;
import com.lanzdev.domain.entity.Subject;
import com.lanzdev.domain.entity.User;
import com.lanzdev.util.AssignCortege;
import com.lanzdev.util.ProgressCortege;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlCourseDao extends AbstractMysqlDao<Course, Integer>
        implements CourseDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlCourseDao.class);

    @Override
    protected String getInsertQuery( ) {
        return Query.INSERT_COURSE;
    }

    @Override
    protected String getSelectByIdQuery( ) {
        return Query.SELECT_COURSE_BY_ID;
    }

    @Override
    protected String getSelectAllQuery( ) {
        return Query.SELECT_ALL_COURSES;
    }

    @Override
    protected String getLastInsertId( ) {
        return Query.SELECT_LAST_INSERT_COURSE;
    }

    @Override
    protected String getUpdateQuery( ) {
        return Query.UPDATE_COURSE;
    }

    @Override
    protected String getDeleteQuery( ) {
        return Query.DELETE_COURSE;
    }

    @Override
    protected List<Course> parseResultSet(ResultSet rs) {

        List<Course> list = new ArrayList<>();

        try {
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setName(rs.getString("course_name"));
                course.setSubjectId(rs.getInt("subject_id"));
                course.setSubjectName(rs.getString("subject_name"));
                course.setTeacherId(rs.getInt("teacher_id"));
                course.setTeacherName(rs.getString("first_name") + " " + rs.getString("last_name"));
                course.setStartDate(rs.getDate("start_date"));
                course.setExpirationDate(rs.getDate("expiration_date"));
                try {
                    course.setSubscribedBy(rs.getInt("subscribed_by"));
                } catch (SQLException e) {
                    LOGGER.warn("Column 'subscribed_by' not found.");
                }
                list.add(course);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while parsing result set.\n", e);
        }

        return list;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement stmt, Course object) {

        try {
            stmt.setString(1, object.getName());
            stmt.setInt(2, object.getSubjectId());
            stmt.setInt(3, object.getTeacherId());
            stmt.setDate(4, object.getStartDate());
            stmt.setDate(5, object.getExpirationDate());
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for create.\n", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Course object) {

        try {
            stmt.setString(1, object.getName());
            stmt.setInt(2, object.getSubjectId());
            stmt.setInt(3, object.getTeacherId());
            stmt.setDate(4, object.getStartDate());
            stmt.setDate(5, object.getExpirationDate());
            stmt.setInt(6, object.getId());
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for create.\n", e);
        }
    }

    @Override
    public List<Course> getNotSubscribed(User student) {

        List<Course> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Query.SELECT_NOT_SUBSCRIBED)) {

            stmt.setInt(1, student.getId());
            list = parseResultSet(stmt.executeQuery());
        }catch (SQLException e) {
            LOGGER.error("Exception while getting not subscribed courses.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while preparing statement.\n", e);
        }

        return list;
    }

    @Override
    public List<Course> getBySubject(Subject subject) {

        List<Course> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Query.SELECT_BY_SUBJECT)) {

            stmt.setInt(1, subject.getId());
            list = parseResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            LOGGER.error("Exception while getting courses by subject.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while preparing statement.\n", e);
        }

        return list;
    }

    @Override
    public List<Course> getByTeacher(User teacher) {

        List<Course> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Query.SELECT_BY_TEACHER)) {

            stmt.setInt(1, teacher.getId());
            list = parseResultSet(stmt.executeQuery());
        }catch (SQLException e) {
            LOGGER.error("Exception while getting courses by teacher.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while preparing statement.\n", e);
        }

        return list;
    }

    @Override
    public List<Course> getSelectedNotStartedCourses(User student) {

        List<Course> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Query.SELECT_LIST_OF_SELECTED_NOT_STARTED_COURSES)) {

            stmt.setInt(1, student.getId());
            list = parseResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            LOGGER.error("Exception while getting selected not started courses.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while preparing statement.\n", e);
        }

        return list;
    }

    @Override
    public List<Course> getSelectedCoursesInProcess(User student) {

        List<Course> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Query.SELECT_LIST_OF_SELECTED_COURSES_IN_PROCESS)) {

            stmt.setInt(1, student.getId());
            list = parseResultSet(stmt.executeQuery());
        } catch (SQLException e) {
            LOGGER.error("Exception while getting selected courses in process.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while preparing statement.\n", e);
        }

        return list;
    }

    @Override
    public List<AssignCortege> getAssignedCourses(User teacher) {

        return getAssigned(teacher, Query.SELECT_ASSIGNED_COURSES);
    }

    @Override
    public List<AssignCortege> getAssignedWithAvgMark(User teacher) {

        return getAssigned(teacher, Query.SELECT_ASSIGNED_COURSES_WITH_AVG_MARKS);
    }

    private List<AssignCortege> getAssigned(User teacher, String query) {

        List<AssignCortege> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setInt(1, teacher.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setName(rs.getString("course_name"));
                course.setSubjectId(rs.getInt("subject_id"));
                course.setTeacherId(rs.getInt("teacher_id"));
                course.setStartDate(rs.getDate("start_date"));
                course.setExpirationDate(rs.getDate("expiration_date"));

                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("login"));
                user.setLogin(rs.getString("password"));
                user.setPermission(Permission.getPermission(rs.getString("permission")));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));

                Journal journal = new Journal();
                journal.setMark(rs.getDouble("mark"));

                AssignCortege assignCortege = new AssignCortege(course, user, journal);

                list.add(assignCortege);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while getting selected not started courses.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while preparing statement.\n", e);
        }

        return list;
    }

    @Override
    public List<ProgressCortege> getDoneCourses(User student) {

        List<ProgressCortege> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(Query.SELECT_DONE_COURSES)) {

            stmt.setInt(1, student.getId());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Course course = new Course();
                course.setId(rs.getInt("course_id"));
                course.setName(rs.getString("course_name"));
                course.setSubjectId(rs.getInt("subject_id"));
                course.setTeacherId(rs.getInt("teacher_id"));
                course.setStartDate(rs.getDate("start_date"));
                course.setExpirationDate(rs.getDate("expiration_date"));

                Journal journal = new Journal();
                journal.setId(rs.getInt("journal_id"));
                journal.setCourseId(rs.getInt("course_id"));
                journal.setStudentId(rs.getInt("student_id"));
                journal.setMark(rs.getDouble("mark"));

                Subject subject = new Subject();
                subject.setId(rs.getInt("subject_id"));
                subject.setName(rs.getString("subject_name"));

                ProgressCortege progressCortege = new ProgressCortege(course, journal, subject);
                list.add(progressCortege);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while getting done courses.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while preparing statement.\n", e);
        }

        return list;
    }

}

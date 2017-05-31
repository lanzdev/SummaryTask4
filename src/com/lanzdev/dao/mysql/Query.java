package com.lanzdev.dao.mysql;

/**
 * Contains all queries for work with mysql
 */
public class Query {

    private Query( ) {

    }

    /*
     * Course queries
     */
    public static final String SELECT_UNGROUPED_COURSES = "SELECT subject.subject_name, user.first_name, user.last_name, course.*, COUNT(*) AS 'subscribed_by' FROM course_students\n"
            + "RIGHT JOIN course ON course.course_id = course_students.course_id\n"
            + "INNER JOIN subject ON course.subject_id = subject.subject_id\n"
            + "LEFT JOIN user ON user.user_id = course.teacher_id\n";
    public static final String INSERT_COURSE = "INSERT INTO course (course_name, subject_id, teacher_id, start_date, expiration_date) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_COURSES = SELECT_UNGROUPED_COURSES + "GROUP BY course_students.course_id";
    public static final String SELECT_COURSE_BY_ID = SELECT_UNGROUPED_COURSES + "WHERE course.course_id = ? GROUP BY course_students.course_id";
    public static final String SELECT_BY_SUBJECT = SELECT_UNGROUPED_COURSES + "WHERE course.subject_id = ? GROUP BY course_students.course_id";
    public static final String SELECT_BY_TEACHER = SELECT_UNGROUPED_COURSES + "WHERE course.teacher_id = ? GROUP BY course_students.course_id";
    public static final String SELECT_NOT_SUBSCRIBED = "SELECT c.*, s.subject_name, t.first_name, t.last_name\n"
            + "FROM course AS c\n"
            + "INNER JOIN subject AS s ON s.subject_id = c.subject_id\n"
            + "INNER JOIN user AS t ON t.user_id = c.teacher_id \n"
            + "LEFT JOIN course_students AS cs \n"
            + "ON c.course_id = cs.course_id AND cs.student_id = ?\n"
            + "WHERE cs.student_id is null";
    public static final String SELECT_LAST_INSERT_COURSE = SELECT_UNGROUPED_COURSES + "WHERE course.course_id = LAST_INSERT_ID() GROUP BY course_students.course_id";
    public static final String SELECT_LIST_OF_SELECTED_NOT_STARTED_COURSES = "SELECT subject.subject_name, user.first_name, user.last_name, "
            + "student_id, course.* FROM course_students\n"
            + "INNER JOIN course ON course_students.course_id = course.course_id\n"
            + "INNER JOIN subject ON subject.subject_id = course.subject_id\n"
            + "INNER JOIN user ON user.user_id = course.teacher_id\n"
            + "WHERE student_id = ? AND course.start_date > CURDATE()";
    public static final String SELECT_LIST_OF_SELECTED_COURSES_IN_PROCESS = "SELECT subject.subject_name, user.first_name, user.last_name, "
            + "student_id, course.* FROM course_students\n"
            + "INNER JOIN course ON course_students.course_id = course.course_id\n"
            + "INNER JOIN subject ON subject.subject_id = course.subject_id\n"
            + "INNER JOIN user ON user.user_id = course.teacher_id\n"
            + "WHERE student_id = ? AND course.start_date < CURDATE() AND course.expiration_date > CURDATE()";
    public static final String SELECT_DONE_COURSES = "SELECT j.student_id, j.journal_id, j.mark,\n"
            + "s.subject_id, s.subject_name,\n"
            + "c.course_id, c.course_name, c.teacher_id, c.start_date, c.expiration_date \n"
            + "FROM journal AS j\n"
            + "INNER JOIN course AS c ON j.course_id = c.course_id\n"
            + "LEFT JOIN subject AS s ON c.subject_id = s.subject_id\n"
            + "WHERE student_id = ?";
    public static final String SELECT_ASSIGNED_COURSES = "SELECT s.subject_name, std.*, c.*, j.mark FROM course AS c\n"
            + "INNER JOIN user AS t ON t.user_id = c.teacher_id\n"
            + "INNER JOIN course_students AS cs ON cs.course_id = c.course_id\n"
            + "INNER JOIN subject AS s ON c.subject_id = s.subject_id\n"
            + "INNER JOIN user AS std ON std.user_id = cs.student_id\n"
            + "LEFT JOIN journal AS j ON j.course_id = c.course_id AND j.student_id = std.user_id\n"
            + "WHERE t.user_id = ?";
    public static final String SELECT_ASSIGNED_COURSES_WITH_AVG_MARKS = "SELECT s.subject_name, std.*, c.*, AVG(j.mark) AS mark FROM course AS c\n"
            + "INNER JOIN user AS t ON t.user_id = c.teacher_id\n"
            + "INNER JOIN course_students AS cs ON cs.course_id = c.course_id\n"
            + "INNER JOIN subject AS s ON c.subject_id = s.subject_id\n"
            + "INNER JOIN user AS std ON std.user_id = cs.student_id\n"
            + "LEFT JOIN journal AS j ON j.course_id = c.course_id AND j.student_id = std.user_id\n"
            + "WHERE t.user_id = ?\n"
            + "GROUP BY c.course_id";
    public static final String UPDATE_COURSE = "UPDATE course SET course_name = ?, subject_id = ?, teacher_id = ?, start_date = ?, expiration_date = ? WHERE course_id = ?";
    public static final String DELETE_COURSE = "DELETE FROM course WHERE course_id = ?";

    /*
     * Course students queries
     */
    public static final String INSERT_COURSE_STUDENTS = "INSERT INTO course_students (course_id, student_id) VALUES (?, ?)";
    public static final String SELECT_COURSE_STUDENTS_BY_ID = "SELECT * FROM course_students WHERE course_students_id = ?";
    public static final String SELECT_ALL_COURSE_STUDENTS = "SELECT * FROM course_students";
    public static final String SELECT_COURSE_STUDENTS_BY_COURSE_ID = "SELECT * FROM course_students WHERE course_id = ?";
    public static final String SELECT_COURSE_STUDENTS_BY_STUDENT_ID = "SELECT * FROM course_students WHERE student_id = ?";
    public static final String SELECT_LAST_INSERT_COURSE_STUDENTS = "SELECT * FROM course_students WHERE course_students_id = LAST_INSERT_ID()";
    public static final String UPDATE_COURSE_STUDENTS = "UPDATE course_students SET course_id = ?, student_id = ? WHERE course_students_id = ?";
    public static final String DELETE_COURSE_STUDENTS = "DELETE FROM course_students WHERE course_students_id = ?";

    /*
     * Journal queries
     */
    public static final String INSERT_JOURNAL = "INSERT INTO journal (course_id, student_id, mark) VALUES (?, ?, ?)";
    public static final String SELECT_JOURNAL_BY_ID = "SELECT * FROM journal WHERE journal_id = ?";
    public static final String SELECT_ALL_JOURNALS = "SELECT * FROM journal";
    public static final String SELECT_JOURNAL_BY_TEACHER = "SELECT t.first_name, t.last_name, c.*, j.mark, stud.login FROM journal AS j\n"
            + "INNER JOIN course AS c ON j.course_id = c.course_id\n"
            + "INNER JOIN user AS t ON c.teacher_id = t.user_id\n"
            + "INNER JOIN subject AS s ON c.subject_id = s.subject_id\n"
            + "INNER JOIN user AS stud ON j.student_id = stud.user_id\n"
            + "WHERE t.user_id = ?";
    public static final String SELECT_JOURNAL_BY_COURSE_AND_STUDENT = "SELECT * FROM journal WHERE course_id = ? AND student_id = ?";
    public static final String SELECT_LAST_INSERT_JOURNAL = "SELECT * FROM journal WHERE journal_id = LAST_INSERT_ID()";
    public static final String UPDATE_JOURNAL = "UPDATE journal SET course_id = ?, student_id = ?, mark = ? WHERE journal_id = ?";
    public static final String DELETE_JOURNAL = "DELETE FROM journal WHERE journal_id = ?";

    /*
     * Subject queries
     */
    public static final String INSERT_SUBJECT = "INSERT INTO subject (subject_name) VALUES (?)";
    public static final String SELECT_SUBJECT_BY_ID = "SELECT * FROM subject WHERE subject_id = ?";
    public static final String SELECT_ALL_SUBJECTS = "SELECT * FROM subject";
    public static final String SELECT_LAST_INSERT_SUBJECT = "SELECT * FROM subject WHERE subject_id = LAST_INSERT_ID()";
    public static final String UPDATE_SUBJECT = "UPDATE subject SET subject_name = ? WHERE subject_id = ?";
    public static final String DELETE_SUBJECT = "DELETE FROM subject WHERE subject_id = ?";

    /*
     * User queries
     */
    public static final String INSERT_USER = "INSERT INTO user (login, password, permission, first_name, last_name) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_USER_BY_ID = "SELECT * FROM user WHERE user_id = ?";
    public static final String SELECT_USER_BY_LOGIN = "SELECT * FROM user WHERE login = ?";
    public static final String SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SELECT_ALL_TEACHERS = "SELECT * FROM user WHERE permission = 'TEACHER'";
    public static final String SELECT_ALL_STUDENTS = "SELECT * FROM user WHERE permission = 'STUDENT'";
    public static final String SELECT_BLOCKED_STUDENTS = "SELECT * FROM user WHERE permission = 'STUDENT' AND blocked = 1";
    public static final String SELECT_UNBLOCKED_STUDENTS = "SELECT * FROM user WHERE permission = 'STUDENT' AND blocked = 0";
    public static final String SELECT_LAST_INSERT_USER = "SELECT * FROM user WHERE user_id = LAST_INSERT_ID()";
    public static final String UPDATE_USER = "UPDATE user SET login = ?, password = ?, permission = ?, first_name = ?, last_name = ?, blocked = ? WHERE user_id = ?";
    public static final String DELETE_USER = "DELETE FROM user WHERE user_id = ?";


}

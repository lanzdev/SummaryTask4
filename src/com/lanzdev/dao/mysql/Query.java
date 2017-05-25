package com.lanzdev.dao.mysql;

public class Query {

    private Query( ) {

    }

    /*
     * Course queries
     */
    public static final String INSERT_COURSE = "INSERT INTO course (course_name, subject_id, teacher_id, start_date, expiration_date) VALUES (?, ?, ?, ?, ?)";
    public static final String SELECT_COURSE_BY_ID = "SELECT * FROM course WHERE course_id = ?";
    public static final String SELECT_ALL_COURSES = "SELECT * FROM course";
    public static final String SELECT_BY_SUBJECT = "SELECT * FROM course WHERE course.subject_id = ?;";
    public static final String SELECT_BY_TEACHER = "SELECT * FROM course WHERE course.teacher_id = ?;";
    public static final String SELECT_LAST_INSERT_COURSE = "SELECT * FROM course WHERE course_id = last_insert_id()";
    public static final String SELECT_LIST_OF_SELECTED_NOT_STARTED_COURSES = "SELECT student_id, course.* FROM course_students\n" +
            "INNER JOIN course ON course_students.course_id = course.course_id\n" +
            "WHERE student_id = ? AND course.start_date > CURDATE()";
    public static final String SELECT_LIST_OF_SELECTED_COURSES_IN_PROCESS = "SELECT student_id, course.* FROM course_students\n" +
            "INNER JOIN course ON course_students.course_id = course.course_id\n" +
            "WHERE student_id = ? AND course.start_date < CURDATE() AND course.expiration_date > CURDATE()";
    public static final String SELECT_DONE_COURSES = "SELECT j.journal_id, j.mark,\n" +
            "s.subject_id, s.subject_name,\n" +
            "c.course_id, c.course_name, c.teacher_id, c.start_date, c.expiration_date \n" +
            "FROM journal AS j\n" +
            "INNER JOIN course AS c ON j.course_id = c.course_id\n" +
            "LEFT JOIN subject AS s ON c.subject_id = s.subject_id\n" +
            "WHERE student_id = ?";
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
    public static final String SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SELECT_LAST_INSERT_USER = "SELECT * FROM user WHERE user_id = LAST_INSERT_ID()";
    public static final String UPDATE_USER = "UPDATE user SET login = ?, password = ?, permission = ?, first_name = ?, last_name = ?, blocked = ? WHERE user_id = ?";
    public static final String DELETE_USER = "DELETE FROM user WHERE user_id = ?";


}

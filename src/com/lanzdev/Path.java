package com.lanzdev;

public class Path {

    private Path() {

    }

    public static final String WELCOME_PAGE = "login.jsp";
    public static final String ERROR_PAGE = "/WEB-INF/jsp/error_page.jsp";

    /*
     * Course
     */
    public static final String FORWARD_TO_VIEW_COURSES_LIST = "/WEB-INF/jsp/list_courses.jsp";
    public static final String FORWARD_TO_ADD_COURSE_FORM = "/WEB-INF/jsp/add_course_form.jsp";
    public static final String FORWARD_TO_EDIT_COURSE = "/WEB-INF/jsp/edit_course_form.jsp";
    public static final String FORWARD_TO_ASSIGN_TEACHER_FORM = "/WEB-INF/jsp/assign_teacher.jsp";
    public static final String FORWARD_TO_COURSES_LIST_FOR_EVALUATION = "/WEB-INF/jsp/list_courses_for_evaluation.jsp";

    public static final String REDIRECT_TO_VIEW_COURSES_LIST = "controller?command=ListCourses";
    public static final String REDIRECT_TO_ADD_COURSE_FORM = "controller?command=AddCourse";
    public static final String REDIRECT_TO_EDIT_COURSE = "controller?command=EditCourse";
    public static final String REDIRECT_TO_ASSIGN_TEACHER_FORM = "controller?command=AssignTeacherForCourse";
    public static final String REDIRECT_TO_COURSES_LIST_FOR_EVALUATION = "controller?command=ListCoursesForEvaluation";


    /*
     * Subject
     */
    public static final String FORWARD_TO_VIEW_SUBJECTS_LIST = "/WEB-INF/jsp/list_subjects.jsp";
    public static final String FORWARD_TO_ADD_SUBJECT_FORM = "/WEB-INF/jsp/add_subject_form.jsp";

    public static final String REDIRECT_TO_VIEW_SUBJECTS_LIST = "controller?command=ListSubjects";
    public static final String REDIRECT_TO_ADD_SUBJECT_FORM = "controller?command=AddSubject";

    /*
     * User
     */
    public static final String FORWARD_TO_VIEW_USERS_LIST = "/WEB-INF/jsp/list_users.jsp";
    public static final String FORWARD_TO_ADD_USER_FORM = "/WEB-INF/jsp/add_user_form.jsp";

    public static final String REDIRECT_TO_VIEW_USERS_LIST = "controller?command=ListUsers";
    public static final String REDIRECT_TO_ADD_USER_FORM = "controller?command=AddUser";

    /*
     * Students
     */
    public static final String FORWARD_TO_VIEW_STUDENTS_LIST = "/WEB-INF/jsp/list_students.jsp";

    public static final String REDIRECT_TO_VIEW_STUDENTS_LIST = "controller?command=ListStudents";

    /*
     * Journal
     */
    public static final String FORWARD_TO_VIEW_JOURNALS_LIST = "/WEB-INF/jsp/list_journals.jsp";
    public static final String FORWARD_TO_ADD_JOURNAL_FORM = "/WEB-INF/jsp/add_journal_form.jsp";

    public static final String REDIRECT_TO_VIEW_JOURNALS_LIST = "controller?command=ListJournals";
    public static final String REDIRECT_TO_ADD_JOURNAL_FORM = "controller?command=AddJournal";

    /*
     * Course students
     */
    public static final String FORWARD_TO_VIEW_COURSE_STUDENTS_LIST = "/WEB-INF/jsp/list_course_students.jsp";
    public static final String FORWARD_TO_ADD_COURSE_STUDENT_FORM = "/WEB-INF/jsp/add_course_student_form.jsp";

    public static final String REDIRECT_TO_VIEW_COURSE_STUDENTS_LIST = "controller?command=ListCourseStudents";
    public static final String REDIRECT_TO_ADD_COURSE_STUDENT_FORM = "controller?command=AddCourseStudent";

    /*
     * Progress
     */
    public static final String FORWARD_TO_VIEW_PROGRESS = "/WEB-INF/jsp/student_progress.jsp";

    public static final String REDIRECT_TO_VIEW_PROGRESS = "controller?command=ListDoneCourses";


    /*
     * Other
     */
    public static final String FORWARD_TO_EVALUATE_FORM = "/WEB-INF/jsp/evaluate_form.jsp";
    public static final String REDIRECT_TO_EVALUATE_FORM = "controller?command=Evaluate";

    public static final String FORWARD_TO_PERSONAL_AREA = "/WEB-INF/jsp/personal_area.jsp";
    public static final String REDIRECT_TO_PERSONAL_AREA = "controller?command=PersonalArea";
}

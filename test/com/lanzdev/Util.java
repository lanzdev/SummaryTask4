package com.lanzdev;

import com.lanzdev.domain.Identified;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Journal;
import com.lanzdev.domain.entity.Subject;
import com.lanzdev.domain.entity.User;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

public class Util {

    public static Course createCourse(int subjectId, int teacherId) throws ParseException {

        String stringDate = "2000-01-01";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
        Course course = new Course();
        course.setName("testCourseName");
        course.setSubjectId(subjectId);
        course.setTeacherId(teacherId);
        course.setStartDate(new Date(sdf.parse(stringDate).getTime()));
        course.setExpirationDate(new Date(sdf.parse(stringDate).getTime()));
        return course;
    }

    public static Subject createSubject( ) {

        Subject subject = new Subject();
        subject.setName("testSubjectName");
        return subject;
    }

    public static User createUser(Permission permission) {

        User user = new User();
        Random random = new Random();
        user.setLogin(String.valueOf(random.nextInt()));
        user.setPassword("testPassword");
        user.setPermission(permission);
        user.setFirstName("testFirstName");
        user.setLastName("testLastName");
        return user;
    }

    public static Journal createJournal(int courseId, int studentId) {

        Journal journal = new Journal();
        journal.setCourseId(courseId);
        journal.setStudentId(studentId);
        journal.setMark(5.0);
        return journal;
    }

    public static <T extends Identified<PK>, PK extends Comparable<PK>> T getLastInsert(List<T> list) {

        T onReturn = null;
        for (T object : list) {
            if (onReturn == null) {
                onReturn = object;
            } else if (object.getId().compareTo(onReturn.getId()) > 0) {
                onReturn = object;
            }
        }
        return onReturn;
    }
}

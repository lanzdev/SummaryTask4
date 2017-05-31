package com.lanzdev.util;

import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Journal;
import com.lanzdev.domain.entity.User;

/**
 * Cortege for assignment. Contains objects of next types:
 * {@link Course course}
 * {@link User user}
 * {@link Journal journal}
 */
public class AssignCortege {

    private Course course;
    private User student;
    private Journal journal;

    public AssignCortege(Course course, User student, Journal journal) {
        this.course = course;
        this.student = student;
        this.journal = journal;
    }

    public Course getCourse( ) {
        return course;
    }

    public User getStudent( ) {
        return student;
    }

    public Journal getJournal( ) {
        return journal;
    }
}

package com.lanzdev.util;

import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Journal;
import com.lanzdev.domain.entity.Subject;

public class ProgressCortege {

    public final Course course;
    public final Journal journal;
    public final Subject subject;

    public ProgressCortege(Course course, Journal journal, Subject subject) {
        this.course = course;
        this.journal = journal;
        this.subject = subject;
    }

    public Course getCourse( ) {
        return course;
    }

    public Journal getJournal( ) {
        return journal;
    }

    public Subject getSubject( ) {
        return subject;
    }
}

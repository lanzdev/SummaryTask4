package com.lanzdev.dao.entity;

import com.lanzdev.dao.GenericDao;
import com.lanzdev.domain.entity.Course;
import com.lanzdev.domain.entity.Journal;
import com.lanzdev.domain.entity.User;

public interface JournalDao extends GenericDao<Journal, Integer> {

    Journal getByCourseAndStudent(Course course, User student);
}

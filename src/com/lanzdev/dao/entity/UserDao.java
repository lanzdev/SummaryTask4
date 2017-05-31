package com.lanzdev.dao.entity;

import com.lanzdev.dao.GenericDao;
import com.lanzdev.domain.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Integer> {

    User getByLogin(String login);

    List<User> getStudents( );

    List<User> getBlockedStudents( );

    List<User> getUnblockedStudents( );

    List<User> getTeachers( );
}

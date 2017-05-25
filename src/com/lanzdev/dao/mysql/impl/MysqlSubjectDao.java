package com.lanzdev.dao.mysql.impl;

import com.lanzdev.dao.entity.SubjectDao;
import com.lanzdev.dao.mysql.AbstractMysqlDao;
import com.lanzdev.dao.mysql.Query;
import com.lanzdev.domain.entity.Subject;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlSubjectDao extends AbstractMysqlDao<Subject, Integer>
        implements SubjectDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlSubjectDao.class);

    @Override
    protected String getInsertQuery( ) {
        return Query.INSERT_SUBJECT;
    }

    @Override
    protected String getSelectByIdQuery( ) {
        return Query.SELECT_SUBJECT_BY_ID;
    }

    @Override
    protected String getSelectAllQuery( ) {
        return Query.SELECT_ALL_SUBJECTS;
    }

    @Override
    protected String getLastInsertId( ) {
        return Query.SELECT_LAST_INSERT_SUBJECT;
    }

    @Override
    protected String getUpdateQuery( ) {
        return Query.UPDATE_SUBJECT;
    }

    @Override
    protected String getDeleteQuery( ) {
        return Query.DELETE_SUBJECT;
    }

    @Override
    protected List<Subject> parseResultSet(ResultSet rs) {

        List<Subject> list = new ArrayList<>();

        try {
            while (rs.next()) {
                Subject subject = new Subject();
                subject.setId(rs.getInt("subject_id"));
                subject.setName(rs.getString("subject_name"));
                list.add(subject);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while parsing result set.\n", e);
        }

        return list;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement stmt, Subject object) {

        try {
            stmt.setString(1, object.getName());
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for create.\n", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Subject object) {

        try {
            stmt.setString(1, object.getName());
            stmt.setInt(2, object.getId());
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for update.\n", e);
        }
    }
}

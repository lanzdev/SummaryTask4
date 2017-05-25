package com.lanzdev.dao.mysql.impl;

import com.lanzdev.dao.entity.JournalDao;
import com.lanzdev.dao.mysql.AbstractMysqlDao;
import com.lanzdev.dao.mysql.Query;
import com.lanzdev.domain.entity.Journal;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlJournalDao extends AbstractMysqlDao<Journal, Integer>
        implements JournalDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlJournalDao.class);

    @Override
    protected String getInsertQuery( ) {
        return Query.INSERT_JOURNAL;
    }

    @Override
    protected String getSelectByIdQuery( ) {
        return Query.SELECT_JOURNAL_BY_ID;
    }

    @Override
    protected String getSelectAllQuery( ) {
        return Query.SELECT_ALL_JOURNALS;
    }

    @Override
    protected String getLastInsertId( ) {
        return Query.SELECT_LAST_INSERT_JOURNAL;
    }

    @Override
    protected String getUpdateQuery( ) {
        return Query.UPDATE_JOURNAL;
    }

    @Override
    protected String getDeleteQuery( ) {
        return Query.DELETE_JOURNAL;
    }

    @Override
    protected List<Journal> parseResultSet(ResultSet rs) {

        List<Journal> list = new ArrayList<>();

        try {
            while (rs.next()) {
                Journal journal = new Journal();
                journal.setId(rs.getInt("journal_id"));
                journal.setCourseId(rs.getInt("course_id"));
                journal.setStudentId(rs.getInt("student_id"));
                journal.setMark(rs.getInt("mark"));
                list.add(journal);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while parsing result set.\n", e);
        }

        return list;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement stmt, Journal object) {

        try {
            stmt.setInt(1, object.getCourseId());
            stmt.setInt(2, object.getStudentId());
            stmt.setInt(3, object.getMark());
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for create.\n", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, Journal object) {

        try {
            stmt.setInt(1, object.getCourseId());
            stmt.setInt(2, object.getStudentId());
            stmt.setInt(3, object.getMark());
            stmt.setInt(4, object.getId());
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for update.\n", e);
        }
    }
}

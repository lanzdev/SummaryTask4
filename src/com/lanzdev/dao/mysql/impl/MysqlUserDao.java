package com.lanzdev.dao.mysql.impl;

import com.lanzdev.dao.entity.UserDao;
import com.lanzdev.dao.mysql.AbstractMysqlDao;
import com.lanzdev.dao.mysql.Query;
import com.lanzdev.domain.Permission;
import com.lanzdev.domain.entity.User;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDao extends AbstractMysqlDao<User, Integer>
        implements UserDao {

    private static final Logger LOGGER = Logger.getLogger(MysqlUserDao.class);

    @Override
    protected String getInsertQuery( ) {
        return Query.INSERT_USER;
    }

    @Override
    protected String getSelectByIdQuery( ) {
        return Query.SELECT_USER_BY_ID;
    }

    @Override
    protected String getSelectAllQuery( ) {
        return Query.SELECT_ALL_USERS;
    }

    @Override
    protected String getLastInsertId( ) {
        return Query.SELECT_LAST_INSERT_USER;
    }

    @Override
    protected String getUpdateQuery( ) {
        return Query.UPDATE_USER;
    }

    @Override
    protected String getDeleteQuery( ) {
        return Query.DELETE_USER;
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) {

        List<User> list = new ArrayList<>();

        try {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setPermission(Permission.getPermission(rs.getString("permission")));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setBlocked(rs.getBoolean("blocked"));
                list.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while parsing result set.\n", e);
        }

        return list;
    }

    @Override
    protected void prepareStatementForCreate(PreparedStatement stmt, User object) {

        try {
            stmt.setString(1, object.getLogin());
            stmt.setString(2, object.getPassword());
            stmt.setString(3, object.getPermission().name());
            stmt.setString(4, object.getFirstName());
            stmt.setString(5, object.getLastName());

        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for create.\n", e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement stmt, User object) {

        try {
            stmt.setString(1, object.getLogin());
            stmt.setString(2, object.getPassword());
            stmt.setString(3, object.getPermission().name());
            stmt.setString(4, object.getFirstName());
            stmt.setString(5, object.getLastName());
            stmt.setBoolean(6, object.getBlocked());
            stmt.setInt(7, object.getId());
        } catch (SQLException e) {
            LOGGER.error("Exception while preparing statement for update.\n", e);
        }
    }
}

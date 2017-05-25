package com.lanzdev.dao.mysql;

import com.lanzdev.dao.ConnectionPool;
import com.lanzdev.dao.GenericDao;
import com.lanzdev.domain.Identified;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractMysqlDao<T extends Identified<PK>, PK> implements GenericDao<T, PK> {

    private static final Logger LOGGER = Logger.getLogger(AbstractMysqlDao.class);

    protected abstract String getInsertQuery( );

    protected abstract void prepareStatementForCreate(PreparedStatement stmt, T object);

    protected abstract List<T> parseResultSet(ResultSet rs);

    protected abstract String getSelectByIdQuery( );

    protected abstract String getSelectAllQuery( );

    protected abstract String getLastInsertId( );

    protected abstract String getUpdateQuery( );

    protected abstract void prepareStatementForUpdate(PreparedStatement stmt, T object);

    protected abstract String getDeleteQuery( );

    @Override
    public T create(T object) {

        T created = null;

        try (Connection connection = ConnectionPool.getConnection()) {

            if (connection != null) {
                connection.setAutoCommit(false);
            } else {
                throw new NullPointerException();
            }
            try (PreparedStatement stmt = connection.prepareStatement(getInsertQuery())) {
                prepareStatementForCreate(stmt, object);
                int count = stmt.executeUpdate();
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.error("Exception while creating object.\n", e);
            } catch (NullPointerException e) {
                connection.rollback();
                LOGGER.error("NPE exception while preparing statement.\n", e);
            }

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(getLastInsertId());
                created = parseResultSet(rs).iterator().next();
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.error("Exception while getting just created object.\n", e);
            } catch (NullPointerException e) {
                connection.rollback();
                LOGGER.error("NPE exception while preparing statement.\n", e);
            }
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("Exception while getting connection.\n", e);
        } catch (NullPointerException e) {
            LOGGER.error("NPE exception while getting connection.\n", e);
        }

        return created;
    }


    @Override
    public T get(PK key) {

        T object = null;

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(getSelectByIdQuery())) {

                connection.setAutoCommit(false);
                stmt.setObject(1, key);
                ResultSet rs = stmt.executeQuery();
                object = parseResultSet(rs).iterator().next();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.error("Exception while getting object by key.\n", e);
            } catch (NullPointerException e) {
                LOGGER.error("NPE exception while preparing statement.\n", e);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while getting connection.\n", e);
        }
        return object;
    }

    @Override
    public List<T> getAll( ) {

        List<T> list = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection()) {
            try (Statement stmt = connection.createStatement()) {

                connection.setAutoCommit(false);
                list = parseResultSet(stmt.executeQuery(getSelectAllQuery()));
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.error("Exception while getting all objects.\n", e);
            } catch (NullPointerException e) {
                LOGGER.error("NPE exception while preparing statement.\n", e);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while getting connection.\n", e);
        }

        return list;
    }

    @Override
    public void update(T object) {

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(getUpdateQuery())) {

                connection.setAutoCommit(false);
                prepareStatementForUpdate(stmt, object);
                int count = stmt.executeUpdate();
                if (count != 1) {
                    throw new DaoException("On update modify more then 1 record: " + count);
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.error("Exception while updating.\n", e);
            } catch (NullPointerException e) {
                LOGGER.error("NPE exception while preparing statement.\n", e);
            } catch (DaoException e) {
                connection.rollback();
                LOGGER.error(e.getMessage() + "\n", e);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while getting connection.\n", e);
        }
    }

    @Override
    public void delete(T object) {

        try (Connection connection = ConnectionPool.getConnection()) {
            try (PreparedStatement stmt = connection.prepareStatement(getDeleteQuery())) {

                connection.setAutoCommit(false);
                stmt.setObject(1, object.getId());
                int count = stmt.executeUpdate();
                if (count != 1) {
                    throw new DaoException("On delete modify more then 1 record: " + count);
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                LOGGER.error("Exception while deleting.\n", e);
            } catch (NullPointerException e) {
                LOGGER.error("NPE exception while preparing statement.\n", e);
            } catch (DaoException e) {
                connection.rollback();
                LOGGER.error(e.getMessage() + "\n", e);
            }
        } catch (SQLException e) {
            LOGGER.error("Exception while getting connection.\n", e);
        }
    }
}

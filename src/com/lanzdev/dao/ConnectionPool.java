package com.lanzdev.dao;

import com.lanzdev.BuildVars;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {

    private static final Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private static ConnectionPool connectionPool;
    private static ComboPooledDataSource cpds;

    private ConnectionPool() throws PropertyVetoException {

        cpds = new ComboPooledDataSource();

        cpds.setDriverClass(BuildVars.DRIVER_DB);
        cpds.setJdbcUrl(BuildVars.LINK_DB);
        cpds.setUser(BuildVars.USER_DB);
        cpds.setPassword(BuildVars.PASSWORD_DB);

        cpds.setMinPoolSize(3);
        cpds.setMaxPoolSize(20);
        cpds.setAcquireIncrement(1);
        cpds.setTestConnectionOnCheckin(true);
        cpds.setPreferredTestQuery("SELECT 1");
        cpds.setIdleConnectionTestPeriod(300);
        cpds.setMaxIdleTimeExcessConnections(240);

    }

    public static Connection getConnection() {

        if (connectionPool == null) {
            try {
                connectionPool = new ConnectionPool();
            } catch (PropertyVetoException e) {
                LOGGER.error("Cannot create connection pool!\n", e);
            }
        }

        try {
            return cpds.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Cannot get connection!\n", e);
            return null;
        }
    }
}

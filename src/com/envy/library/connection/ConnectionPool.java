package com.envy.library.connection;

import com.envy.library.exception.ConnectionDatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ConnectionPool {
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "toor";
    private static final String URL = "jdbc:mysql://localhost:3306/bookLibrary";
    private static final int POOL_SIZE = 32;
    private static ConnectionPool instance;
    private static volatile boolean instanceIsCreated;

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> surrenderedConnections;

    public static ConnectionPool getInstance() {
        if (!instanceIsCreated) {

            synchronized (ConnectionPool.class) {
                if (!instanceIsCreated) {
                    instance = new ConnectionPool();
                    instanceIsCreated = true;
                }
            }
        }

        return instance;
    }

    private ConnectionPool() {
        try {
            Class.forName(DRIVER_NAME);
            freeConnections = new LinkedBlockingDeque<>(POOL_SIZE);
            surrenderedConnections = new ArrayDeque<>(POOL_SIZE);

            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.offer(new ProxyConnection(DriverManager.getConnection(URL, LOGIN, PASSWORD)));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error while connection pool creating " + e); //there must be Logger
        }
    }

    public Connection getConnection() throws ConnectionDatabaseException {
        ProxyConnection connection;

        try {
            connection = freeConnections.take();
            surrenderedConnections.offer(connection);
        } catch (InterruptedException e) {
            throw new ConnectionDatabaseException("Error while getting connection", e);
        }

        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionDatabaseException {
        if (connection instanceof ProxyConnection) {
            surrenderedConnections.remove(connection);
            freeConnections.offer((ProxyConnection) connection);
        } else {
            throw new ConnectionDatabaseException("Invalid connection to close");
        }
    }

    public void destroyPool() throws ConnectionDatabaseException {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                freeConnections.take().reallyClose();
            }
            deregisterDrivers();
        } catch (SQLException | InterruptedException e) {
            throw new ConnectionDatabaseException("Error while close connection pool", e);
        }
    }

    private void deregisterDrivers() throws SQLException {
        while (DriverManager.getDrivers().hasMoreElements()) {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
    }
}

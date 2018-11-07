package com.dimov;

import java.sql.Connection;
import java.sql.SQLException;

public class Engine implements Runnable {
    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {

        try {
            Connection connection = getConnection();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        return null;
    }
}

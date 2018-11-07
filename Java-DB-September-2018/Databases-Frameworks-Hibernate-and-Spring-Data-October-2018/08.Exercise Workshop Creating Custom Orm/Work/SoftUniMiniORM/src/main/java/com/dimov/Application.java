package com.dimov;

import com.dimov.dataBase.EntityDbContext;
import com.dimov.dataBase.base.DbContext;
import com.dimov.entities.Department;
import com.dimov.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    private static final String CONNECTION_STRING =
            "jdbc:mysql://localhost:3306/soft_uni_simple?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        Connection connection = getConnection();

        // Check for user and password
        // Go to com.dimov.EntityDbContext

        connection.close();
    }

    private static <T> DbContext<T> getDbContext(Connection connection, Class<T> klass) throws SQLException {
        return new EntityDbContext<>(connection, klass);
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                CONNECTION_STRING,
                "root",
                "1234"
        );
    }
}

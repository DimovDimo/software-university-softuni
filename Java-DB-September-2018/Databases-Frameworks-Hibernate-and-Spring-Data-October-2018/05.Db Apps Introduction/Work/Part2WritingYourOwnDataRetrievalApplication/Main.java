package Part2WritingYourOwnDataRetrievalApplication;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
        Connection connection =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/diablo?&useSSL=false", props);

        PreparedStatement stmt =
                connection.prepareStatement(
                        "SELECT u.first_name, u.last_name, " +
                                "COUNT(u.id) AS count_games " +
                             "FROM users AS u " +
                             "JOIN users_games AS ug ON u.id = ug.user_id " +
                             "WHERE u.user_name = ? " +
                             "GROUP BY u.id;");

        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        stmt.setString(1, userName);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()){
            String fullName = rs.getString("first_name") + " " +
                    rs.getString("last_name");
            int countGames = rs.getInt("count_games");
            System.out.println("User: " + userName);
            System.out.printf("%s has played %d games", fullName, countGames);
        } else {
            System.out.println("No such user exists");
        }
        connection.close();
    }
}

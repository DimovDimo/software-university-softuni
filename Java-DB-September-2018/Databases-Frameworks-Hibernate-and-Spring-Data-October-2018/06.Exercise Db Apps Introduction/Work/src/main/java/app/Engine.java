package app;

import java.sql.*;
import java.util.Scanner;

public class Engine implements Runnable {

    private Connection connection;

    public Engine(Connection connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            //this.getVillainsNames();
            //this.getMinionNames();
            this.addMinion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 2. Get Villains’ Names
     * Write a program that prints on the
     * console all villains’ names and their
     * number of minions. Get only the villains
     * who have more than 3 minions. Order them
     * by number of minions in descending order
     */
    private void getVillainsNames() throws SQLException {
        System.out.print("Input count of minions: ");
        Scanner scanner = new Scanner(System.in);

        String query =
                "SELECT v.name, COUNT(mv.minion_id) AS count_minions " +
                "FROM villains AS v " +
                "JOIN minions_villains As mv ON mv.villain_id = v.id " +
                "GROUP BY v.id " +
                "HAVING count_minions > ? " +
                "ORDER BY count_minions DESC";
        PreparedStatement preparedStatement = this.connection
                .prepareStatement(query);
        preparedStatement.setInt(1,
                Integer.parseInt(scanner.nextLine()));

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.printf("%s %d%n",
                    resultSet.getString("name"),
                    resultSet.getInt("count_minions"));
        }

        connection.close();
    }

    /**
     * 3. Get Minion Names
     * Write a program that prints on the console all
     * minion names and their age for given villain id.
     * For the output, use the formats given in the examples.
     */
    private void getMinionNames() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input villain Id: ");
        Integer villianId = Integer.parseInt(scanner.nextLine());

        String queryGetVillian =
                "SELECT v.name " +
                "FROM villains AS v " +
                "WHERE v.id = ?";
        PreparedStatement preparedStatementGetVillian = this.connection
                .prepareStatement(queryGetVillian);
        preparedStatementGetVillian.setInt(1, villianId);

        ResultSet resultSetGetVillian = preparedStatementGetVillian.executeQuery();

        if (resultSetGetVillian.next()){
            System.out.printf("Villain: %s%n",
                    resultSetGetVillian.getString("name"));
            this.getMinionNamesOfAVillian(villianId);
        } else {
            System.out.printf("No villain with ID %d exists in the database.", villianId);
        }
    }

    private void getMinionNamesOfAVillian(Integer villianId) throws SQLException {
        String queryGetMinions =
                "SELECT DISTINCT(m.name), m.age " +
                "FROM villains AS v " +
                "JOIN minions_villains AS mv ON mv.villain_id = v.id " +
                "JOIN minions AS m ON mv.minion_id = m.id " +
                "WHERE v.id = ?";
        PreparedStatement preparedStatementGetMinions = this.connection
                .prepareStatement(queryGetMinions);
        preparedStatementGetMinions.setInt(1, villianId);

        ResultSet resultSetGetMinions = preparedStatementGetMinions.executeQuery();

        if (resultSetGetMinions.next()){
            Integer index = 1;
            do {
                String name = resultSetGetMinions.getString("name");
                Integer age = Integer.parseInt(resultSetGetMinions.getString("age"));
                System.out.printf("%d. %s %d%n",
                        index, name, age);
                index++;
            } while (resultSetGetMinions.next());
        } else {
            System.out.println("<no minions>");
        }
    }

    /**
     * 4. Add Minion
     * Write a program that reads information about a minion and its villain and adds it to the database. In case the town
     * of the minion is not in the database, insert it as well. In case the villain is not present in the database, add him too
     * with default evilness factor of “evil”. Finally, set the new minion to be servant of the villain. Print appropriate
     * messages after each operation – see the examples.
     * *Bonus task: Make sure all operations are executed successfully. In case of an error do not change the database.
     */
    private void addMinion() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        String[] minionParams = scanner.nextLine().split("\\s+");
        String[] villianParams = scanner.nextLine().split("\\s+");

        String minionName = minionParams[1];
        int minionAge = Integer.parseInt(minionParams[2]);
        String minionTown = minionParams[3];
        String villianName = villianParams[1];

        if (!this.checksIfEntityExist(minionTown, "towns")){
            this.insertTown(minionTown);
        }

        if (!this.checksIfEntityExist(villianName, "villains")){
            this.insertVillain(villianName);
        }

        int minionTownId = this.getEntityId(minionTown, "towns");
        this.insertMinion(minionName, minionAge, minionTownId);
        this.insertIntoMinionsVillains(minionName, villianName);

        System.out.printf("Successfully added %s to be minion of %s.%n",
                minionName, villianName);
    }

    private void insertIntoMinionsVillains(String minionName, String villianName) throws SQLException {
        int minionId = this.getEntityId(minionName, "minions");
        int villianId = this.getEntityId(villianName, "villains");

        String query = String.format("INSERT INTO minions_villains(minion_id, villain_id) VALUES (%d, %d)",
                minionId, villianId);
        PreparedStatement statement = this.connection
                .prepareStatement(query);

        statement.execute();
    }

    private void insertVillain(String villianName) throws SQLException {
        String query =
                "INSERT INTO villains(name, evilness_factor) " +
                "VALUES ('" + villianName + "', 'evil')";
        PreparedStatement statement = this.connection
                .prepareStatement(query);

        statement.execute();

        System.out.printf("Villain %s was added to the database.%n", villianName);
    }

    private void insertTown(String townName) throws SQLException {
        String query =
                "INSERT INTO towns(name, country) " +
                "VALUES ('" + townName + "', NULL)";
        PreparedStatement statement = this.connection
                .prepareStatement(query);

        statement.execute();

        System.out.printf("Town %s was added to the database.%n", townName);
    }

    private boolean checksIfEntityExist(String name, String tableName) throws SQLException {
        String query =
                "SELECT * " +
                "FROM " + tableName +
                " WHERE name = ?";
        PreparedStatement preparedStatement = this.connection
                .prepareStatement(query);
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()){
            return true;
        }

        return false;
    }

    private int getEntityId(String name, String tableName) throws SQLException {
        String query =
                "SELECT id " +
                "FROM " + tableName +
                " WHERE name = ?";
        PreparedStatement preparedStatement = this.connection
                .prepareStatement(query);
        preparedStatement.setString(1, name);

        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt("id");
    }

    private void insertMinion(String minionName, int minionAge, int minionTownId) throws SQLException{
        String query = String.format("INSERT INTO minions(name, age, town_id) VALUES ('%s', %d, %d)",
                minionName, minionAge, minionTownId);
        PreparedStatement statement = this.connection
                .prepareStatement(query);

        statement.execute();
    }
}

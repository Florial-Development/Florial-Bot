package databases;

import bot.FlorialBot;
import databases.models.Story;

import java.sql.*;

public class StoryDatabase {

    private static Connection connection;

    private static StoryDatabase instance;


    private final String DATABASE = FlorialBot.getDATABASE();
    private final String USERNAME = FlorialBot.getDATABASE_USER();
    private final String PASSWORD = FlorialBot.getDATABASE_PASSWORD();

    public static StoryDatabase getInstance() {
        if (instance == null) {
            instance = new StoryDatabase();
        }
        return instance;
    }


    public Connection getConnection() throws SQLException {

        if (connection != null) {
            return connection;
        }

        connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);

        System.out.println("Connected to Database.");

         createStoryDatabaseIfNotExists();



        return connection;
    }

    public void createStoryDatabaseIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS stories (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, required_level INT, name TEXT, part_1 TEXT, part_2 TEXT, part_3 TEXT, image_1 TEXT, image_2 TEXT, image_3 TEXT)";
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(sql);
        }
    }

    public void addStory(int requiredLevel, String name, String part1, String part2, String part3, String image1, String image2, String image3) throws SQLException {

        if (doesStoryExist(name)) { return; }

        String sql = "INSERT INTO stories (required_level, name, part_1, part_2, part_3, image_1, image_2, image_3) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, requiredLevel);
            statement.setString(2, name);
            statement.setString(3, part1);
            statement.setString(4, part2);
            statement.setString(5, part3);
            statement.setString(6, image1);
            statement.setString(7, image2);
            statement.setString(8, image3);

            statement.executeUpdate();
        }
    }


    public Story getStoryByLevel(int userLevel) {
        String query = "SELECT * FROM stories WHERE required_level <= ? ORDER BY required_level DESC LIMIT 1";

        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, userLevel);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                int requiredLevel = resultSet.getInt("required_level");
                String name = resultSet.getString("name");
                String part1 = resultSet.getString("part_1");
                String part2 = resultSet.getString("part_2");
                String part3 = resultSet.getString("part_3");
                String image1 = resultSet.getString("image_1");
                String image2 = resultSet.getString("image_2");
                String image3 = resultSet.getString("image_3");

                return new Story(id, requiredLevel, name, part1, part2, part3, image1, image2, image3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    private boolean doesStoryExist(String storyName) throws SQLException {
        String sql = "SELECT COUNT(*) AS count FROM stories WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, storyName);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        }
        return false;
    }



}

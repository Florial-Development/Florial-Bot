package databases;

import databases.models.DiscordProfile;

import java.sql.*;

public class BotDatabase {

    private static Connection connection;

    private static BotDatabase instance;

    private static final String DATABASE = "";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    public static BotDatabase getInstance() {
        if (instance == null) {
            instance = new BotDatabase();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {

        if (connection != null) {
            return connection;
        }

        connection = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);

        System.out.println("Connected to Database.");

        createDatabaseIfNotExists();

        return connection;
    }


    public void createDatabaseIfNotExists() throws SQLException {

        Statement statement = getConnection().createStatement();

        String sql = "CREATE TABLE IF NOT EXISTS discord_profiles(uuid varchar(36) primary key, devblogs int, tokens int, readwhen long, xp int, lvl int)";
        statement.execute(sql);

        String createIndexSQL = "CREATE INDEX idx_uuid ON discord_profiles (uuid)";
         //statement.execute(createIndexSQL);
    }

    public void editDiscordProfiles(DiscordProfile data, boolean isCreating) throws SQLException{

        PreparedStatement statement = isCreating ? getConnection().prepareStatement("INSERT INTO discord_profiles(uuid, devblogs, tokens, readwhen, xp, lvl) VALUES (?, ?, ?, ?, ?, ?)")
                : getConnection().prepareStatement("UPDATE discord_profiles SET devblogs = ?, tokens = ?, readwhen = ?, xp = ?, lvl = ? WHERE uuid = ?");

        statement.setLong(isCreating ? 1 : 6, data.getUuid());
        statement.setInt(isCreating ? 2 : 1, data.getDevBlogs());
        statement.setInt(isCreating ? 3 : 2, data.getTokens());
        statement.setLong(isCreating ? 4 : 3, data.getReadWhen());
        statement.setLong(isCreating ? 5 : 4, data.getXp());
        statement.setLong(isCreating ? 6 : 5, data.getLvl());



        statement.executeUpdate();

        statement.close();

    }

    public DiscordProfile findProfileByUUID(long u) {
        try {
            PreparedStatement statement = getConnection().prepareStatement("SELECT devblogs, tokens, readwhen, xp, lvl FROM discord_profiles WHERE uuid = ?");
            statement.setLong(1, u);

            ResultSet results = statement.executeQuery();

            if (results.next()) {
                int devBlogs = results.getInt("devblogs");
                int tokens = results.getInt("tokens");
                long readWhen = results.getLong("readwhen");
                int xp = results.getInt("xp");
                int lvl = results.getInt("lvl");
                statement.close();
                return new DiscordProfile(u, devBlogs, tokens, readWhen, xp, lvl);
            } else {
                statement.close();
                return null;
            }
        } catch (SQLException e) {
            System.out.println("PROFILE RETURNED NULL / COULD NOT CREATE FOR USER WITH ID: " + u + "  ");
            e.printStackTrace();
            return null;
        }
    }

    public void deleteProfile(long u) throws SQLException {

        PreparedStatement deleteStatement = getConnection().prepareStatement("DELETE FROM discord_profiles WHERE uuid = ?");

        deleteStatement.setLong(1, u);

        deleteStatement.executeUpdate();

        deleteStatement.close();
    }

    public void createNewDatabaseValue() {

        // example

        String addXpColumnSQL = "ALTER TABLE discord_profiles ADD xp INT";
        String updateSQL = "UPDATE discord_profiles SET xp = 0 WHERE xp IS NULL";


        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(addXpColumnSQL);

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}


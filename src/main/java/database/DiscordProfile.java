package database;

import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;

public class DiscordProfile {

    BotDatabase botDatabase = new BotDatabase();

    @Getter @Setter
    private long uuid;
    @Getter @Setter private int devBlogs;
    @Getter @Setter private int tokens;

    @Getter @Setter private long readWhen;
    @Getter @Setter private int xp;
    @Getter @Setter private int lvl;



    public DiscordProfile(long uuid, int devBlogs, int tokens, long readWhen, int xp, int lvl) {
        this.uuid = uuid;
        this.devBlogs = devBlogs;
        this.tokens = tokens;
        this.readWhen = readWhen;
        this.xp = xp;
        this.lvl = lvl;

    }

    public void delete() throws SQLException {

        botDatabase.deleteProfile(this.uuid);

    }

    public void save() throws SQLException {

        botDatabase.editDiscordProfiles(this, false);
    }
}

package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import database.BotDatabase;
import database.DiscordProfile;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.Permission;

import java.sql.SQLException;

@CommandInfo(name = "reset")
public class ResetTimerCommand extends SlashCommand {

    private static final BotDatabase botDatabase = new BotDatabase();


    public ResetTimerCommand() {
        this.name = "reset";
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        DiscordProfile profile;

        try {
            profile = botDatabase.findProfileByUUID(slashCommandEvent.getUser().getIdLong());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        profile.setReadWhen(System.currentTimeMillis() + (26 * 3600000));

        try {
            profile.save();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

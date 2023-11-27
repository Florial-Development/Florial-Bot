package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import database.BotDatabase;
import database.DiscordProfile;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.sql.SQLException;
import java.util.List;

@CommandInfo(name = "test")
public class TestCommand extends SlashCommand {

    private static final BotDatabase botDatabase = new BotDatabase();


    public TestCommand() {
        this.name = "test";
        this.options = List.of(
                new OptionData(OptionType.USER, "user", "The user to fix", true)
        );

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {


        User user = slashCommandEvent.getOption("user").getAsUser();


        try {
            botDatabase.editDiscordProfiles(new DiscordProfile(Long.parseLong(user.getId()), 0, 0, System.currentTimeMillis() + (25 * 3600000), 0, 1), true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}

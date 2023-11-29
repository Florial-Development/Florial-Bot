package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.List;

@CommandInfo(name = "profile")
public class DiscordProfileCommand extends SlashCommand {

    private static final BotDatabase botDatabase = new BotDatabase();


    public DiscordProfileCommand() {
        this.name = "profile";
        this.options = List.of(
                new OptionData(OptionType.USER, "user", "The user to get the profile of", false));

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        User user = slashCommandEvent.getOption("user") == null ? slashCommandEvent.getUser() : slashCommandEvent.getOption("user").getAsUser();

        DiscordProfile profile = botDatabase.findProfileByUUID(user.getIdLong());

        profile.fetchProfile(slashCommandEvent.getChannel(), user);


    }
}

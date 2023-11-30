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

@CommandInfo(name = "setlevel")
public class LevelSetCommand extends SlashCommand {


    public LevelSetCommand() {
        this.name = "setlevel";
        this.options = List.of(
                new OptionData(OptionType.USER, "user", "The user to set the level of", true),
                new OptionData(OptionType.INTEGER, "level", "level to set to", true)
        );

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        User user = slashCommandEvent.getOption("user").getAsUser();

        DiscordProfile profile = BotDatabase.getInstance().findProfileByUUID(user.getIdLong());

        profile.setLvl(slashCommandEvent.getOption("level").getAsInt());

        profile.gainExperience(0, user);




    }
}

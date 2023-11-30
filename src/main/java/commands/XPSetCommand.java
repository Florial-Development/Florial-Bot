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

@CommandInfo(name = "addxp")
public class XPSetCommand extends SlashCommand {


    public XPSetCommand() {
        this.name = "addxp";
        this.options = List.of(
                new OptionData(OptionType.USER, "user", "The user to give xp", true),
                new OptionData(OptionType.INTEGER, "xp", "xp to give", true)
        );

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        User user = slashCommandEvent.getOption("user").getAsUser();

        DiscordProfile profile = BotDatabase.getInstance().findProfileByUUID(user.getIdLong());

        profile.gainExperience(slashCommandEvent.getOption("xp").getAsInt(), user);




    }
}

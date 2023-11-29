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

@CommandInfo(name = "test")
public class TestCommand extends SlashCommand {


    public TestCommand() {
        this.name = "test";
        this.options = List.of(
                new OptionData(OptionType.USER, "user", "The user to fix", true),
                new OptionData(OptionType.INTEGER, "amount", "amt to give", true)
        );

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        User user = slashCommandEvent.getOption("user").getAsUser();

       // try {
         //   botDatabase.editDiscordProfiles(new DiscordProfile(Long.parseLong(user.getId()), 0, 0, System.currentTimeMillis() + (25 * 3600000), 0, 1), true);
     //   } catch (SQLException e2) {
          //  throw new RuntimeException(e2);
     //   }


        DiscordProfile profile = BotDatabase.getInstance().findProfileByUUID(user.getIdLong());

        profile.setReadWhen(System.currentTimeMillis() + (25 * 3600000));

        profile.gainExperience(slashCommandEvent.getOption("amount").getAsInt(), user);




    }
}

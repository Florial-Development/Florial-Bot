package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.sql.SQLException;
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

        try {
            BotDatabase.getInstance().editDiscordProfiles(new DiscordProfile(Long.parseLong(user.getId()), 0, 0, 0, 0, 0, 1,0, 0), true);
        } catch (SQLException e2) {
            throw new RuntimeException(e2);
        }


       // DiscordProfile profile = BotDatabase.getInstance().findProfileByUUID(user.getIdLong());

      //  profile.fetchProfile(slashCommandEvent.getChannel(), user);

     //   profile.setReadWhen(System.currentTimeMillis() + (25 * 3600000));

       // profile.gainExperience(slashCommandEvent.getOption("amount").getAsInt(), user);

      //  EmbedBuilder e = new EmbedBuilder().setTitle("<:crystalheart:1168455971958439936>        **Enjoy Your Stay!**       <:crystalheart:1168455971958439936>")
             //   .addField("", "", false)
              //  .addField("**━━━━━━━━━━━━━━━━━━━━━━━━━**", "", true)
               // .addField("<:crystalheart:1168455971958439936> " + user.getEffectiveName() + " <:crystalheart:1168455971958439936> ~", "", false)
              //  .setColor(Color.pink)
              //  .setThumbnail(user.getAvatarUrl())
              //  .setImage("https://media.discordapp.net/attachments/842010486009626625/1180090125296869376/wlcom.jpg?ex=657c2792&is=6569b292&hm=607db58434156ccb4905e695f4ca0093af9b555379232dc94efc2f7d03f19082&=&format=webp");

      //  slashCommandEvent.replyEmbeds(e.build()).queue();


    }
}

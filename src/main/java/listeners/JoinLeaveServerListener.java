package listeners;

import bot.FlorialBot;
import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JoinLeaveServerListener extends ListenerAdapter {


    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

        User user = event.getUser();

        BotDatabase db = BotDatabase.getInstance();

        try {
            db.editDiscordProfiles(new DiscordProfile(Long.parseLong(user.getId()), 0, 0, 0, 0, 0, 1, 0, 0), true);
        } catch (SQLException e) {
            throw new RuntimeException("Exception: " + e + user + " " + user.getId());
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        EmbedBuilder e = new EmbedBuilder().setTitle("<:crystalheart:1168455971958439936>        **Enjoy Your Stay!**       <:crystalheart:1168455971958439936>")
                .addField("", "", false)
                .addField("**━━━━━━━━━━━━━━━━━━━━━━━━━**", "", true)
                .addField("<:crystalheart:1168455971958439936> " + user.getEffectiveName() + " <:crystalheart:1168455971958439936> ~", "", false)
                .setColor(Color.pink)
                .setThumbnail(user.getAvatarUrl())
                .setImage("https://media.discordapp.net/attachments/842010486009626625/1180090125296869376/wlcom.jpg?ex=657c2792&is=6569b292&hm=607db58434156ccb4905e695f4ca0093af9b555379232dc94efc2f7d03f19082&=&format=webp");

        FlorialBot.getDiscordBot().getGuildById("801913598481268766").getTextChannelById("824371655073595432").sendMessageEmbeds(e.build()).queue();

        user.openPrivateChannel().queue(privateChannel -> {
            db.findProfileByUUID(Long.parseLong(user.getId())).fetchProfile(privateChannel, user);
            privateChannel.sendMessage("Welcome to Florial official! **Did you know we have an indie game in development?** ***YEP!*** Want to learn more? Click Here: https://discord.com/channels/801913598481268766/1165445508127526932/1165446270555541536 for a preview!").queue();
            privateChannel.sendMessage("**Subscribe to devblog pings?** Collect devblogs by Participating in reading them at least 24 hours after they're announced, and earn rewards overtime depending on how many you collect!").queue();

            scheduler.schedule(() -> {

                privateChannel.sendMessage("")
                        .addActionRow(
                                Button.primary("subscribe", "SUBSCRIBE").withStyle(ButtonStyle.SUCCESS))
                        .queue();

            }, 1, TimeUnit.SECONDS);
        });
    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {

        BotDatabase.getInstance().findProfileByUUID(Long.parseLong(event.getUser().getId())).delete();

    }
}

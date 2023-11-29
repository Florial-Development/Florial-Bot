package listeners;

import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.jetbrains.annotations.NotNull;

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
            db.editDiscordProfiles(new DiscordProfile(Long.parseLong(user.getId()), 0, 0, 0, 0, 1), true);
        } catch (SQLException e) {
            throw new RuntimeException("Exception: " + e + user + " " + user.getId());
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

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

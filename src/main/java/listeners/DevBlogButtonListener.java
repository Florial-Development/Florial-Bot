package listeners;

import database.BotDatabase;
import database.DiscordProfile;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

public class DevBlogButtonListener extends ListenerAdapter {

    private static final BotDatabase botDatabase = new BotDatabase();

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (event.getComponentId().equals("devblog")) {

            DiscordProfile profile;

            try {
                profile = botDatabase.findProfileByUUID(event.getUser().getIdLong());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            long readWhen = profile.getReadWhen();
            int devBlogs = profile.getDevBlogs();

            long currentTimestamp = System.currentTimeMillis();
            long twentyFourHoursMillis = TimeUnit.HOURS.toMillis(24);

            if ((readWhen - currentTimestamp >= twentyFourHoursMillis) || readWhen == 0) {

                profile.setDevBlogs(devBlogs + 1);
                profile.setReadWhen(currentTimestamp);

                event.reply("Collected! You now have read and collected a total of " + devBlogs + " devblogs!").setEphemeral(true).queue();

                try {
                    profile.save();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            } else {
                event.reply("You have already collected this devblog!").setEphemeral(true).queue();
            }
        }
    }
}
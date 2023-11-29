package listeners;

import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class DevBlogButtonListener extends ListenerAdapter {


    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (event.getComponentId().equals("devblog")) {

            DiscordProfile profile;

            profile = BotDatabase.getInstance().findProfileByUUID(event.getUser().getIdLong());

            long readWhen = profile.getReadWhen();
            int devBlogs = profile.getDevBlogs();

            long currentTimestamp = System.currentTimeMillis();
            long twentyFourHoursMillis = TimeUnit.HOURS.toMillis(24);

            if ((readWhen - currentTimestamp >= twentyFourHoursMillis) || readWhen == 0) {

                profile.setDevBlogs(devBlogs + 1);
                profile.setReadWhen(currentTimestamp);

                event.reply("Collected! You now have read and collected a total of " + profile.getDevBlogs() + " devblogs!").setEphemeral(true).queue();

                profile.save();

            } else {
                event.reply("You have already collected this devblog!").setEphemeral(true).queue();
            }
        }
    }
}
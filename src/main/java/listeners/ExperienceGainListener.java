package listeners;

import bot.FlorialBot;
import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ExperienceGainListener extends ListenerAdapter {

    private static final List<String> valuableChannels = Arrays.asList("919361125739548743", "919361125739548743", "938256684286083132", "943028902828322826");
    private static final TextChannel botDMs = FlorialBot.getBotDMs();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User user = event.getAuthor();

        if (user.isBot() || (!(event.isFromGuild())) || event.getChannel() == botDMs) return;

        if (valuableChannels.contains(event.getChannel().getId())) {
            DiscordProfile profile = BotDatabase.getInstance().findProfileByUUID(user.getIdLong());
            profile.gainExperience(profile.getLvl() * 100 / 12, user);

        } else if (new Random().nextBoolean()) {
            DiscordProfile profile = BotDatabase.getInstance().findProfileByUUID(user.getIdLong());
            profile.gainExperience(new Random().nextInt(4) + 2, user);
        }

    }

}


package listeners;

import bot.FlorialBot;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DMListener extends ListenerAdapter {

    private static final TextChannel botDMs = FlorialBot.getBotDMs();
    private static final TextChannel verifyChannel = FlorialBot.getVerifyChannel();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot() || (event.getChannel() == botDMs)) {
            return;
        }

        String messageContent = event.getMessage().getContentDisplay();

        verifyChannel.sendMessage("**" + event.getAuthor().getName() + "**: " + messageContent).queue();
    }
}

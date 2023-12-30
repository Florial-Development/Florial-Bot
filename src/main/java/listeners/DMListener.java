package listeners;

import bot.FlorialBot;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class DMListener extends ListenerAdapter {
    private static TextChannel verifyChannel;

    public static void init() {

        verifyChannel = FlorialBot.getDiscordBot().getGuildById("801913598481268766").getTextChannelById("950565475107098654");
    }


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot() || (!(event.getMessage().isFromType(ChannelType.PRIVATE)))) {
            return;
        }

        String messageContent = event.getMessage().getContentDisplay();

        verifyChannel.sendMessage("**" + event.getAuthor().getName() + "**: " + messageContent).queue();
    }
}

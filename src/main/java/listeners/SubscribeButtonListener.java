package listeners;

import bot.FlorialBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class SubscribeButtonListener extends ListenerAdapter {

    private static Role subscribedRole;
    private static Guild discordServer;

    public static void init() {

        subscribedRole = FlorialBot.getSubscribedRole();
        discordServer = FlorialBot.getDiscordServer();
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (event.getComponentId().equals("subscribe")) {

            discordServer.retrieveMemberById(event.getUser().getId()).queue(member -> {
                if (member.getRoles().contains(subscribedRole)) {
                    event.reply("Already subscribed.").setEphemeral(true).queue();
                } else {
                    discordServer.addRoleToMember(member, subscribedRole).queue();
                    event.reply("You have been subscribed to our Devblogs! Thank you!").queue();
                }
            });
        }
    }
}

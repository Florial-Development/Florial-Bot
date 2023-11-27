package listeners;

import bot.FlorialBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RoleButtonListener extends ListenerAdapter {
    private static Guild discordServer;

    public static void init() {

        discordServer = FlorialBot.getDiscordServer();
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (!(event.getComponentId().contains("role"))) {
            return;
        }

        Member member = event.getMember();

        String[] splitID = event.getComponentId().split(",");

        Role role = discordServer.getRoleById(splitID[2]);

        if (member.getRoles().contains(role)) {
            discordServer.removeRoleFromMember(member, role).queue();
            event.reply("Successfully removed you from the role!").setEphemeral(true).queue();
        } else {

            boolean canGetRole = true;

            if (splitID[3].contains("one")) {
                canGetRole = !hasRoleWithString(member, splitID[4]);
            }

            if (canGetRole) {
                discordServer.addRoleToMember(member, role).queue();
                event.reply("Successfully gave you the role!").setEphemeral(true).queue();
            } else {
                event.reply("You can only choose one of this role at a time. Remove yourself from your current role of this category to select a new one.").setEphemeral(true).queue();
            }
        }


    }


    private static boolean hasRoleWithString(Member member, String substring) {
        for (Role role : member.getRoles()) {
            if (role.getName().contains(substring)) {
                return true;
            }
        }
        return false;
    }
}
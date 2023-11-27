package listeners;

import bot.FlorialBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.RestAction;

import java.util.ArrayList;
import java.util.List;

public class AcceptDenyButtonListener extends ListenerAdapter {

    private static final List<Role> rolesToAdd = new ArrayList<>();
    private static final List<Role> rolesToRemove = new ArrayList<>();

    private static Guild discordServer;

    public static void init() {

        discordServer = FlorialBot.getDiscordServer();

        rolesToAdd.add(discordServer.getRoleById("801913598644846604"));    // tulip
        rolesToAdd.add(discordServer.getRoleById("1055271837120086016"));   // pronoundiv
        rolesToAdd.add(discordServer.getRoleById("1055246346262687837"));   // likesdiv
        rolesToAdd.add(discordServer.getRoleById("989259813601046578"));    // pingsdiv
        rolesToAdd.add(discordServer.getRoleById("1055272730196443136"));   // rolesdiv
        rolesToAdd.add(discordServer.getRoleById("1055272722671865966"));   // aboutdiv
        rolesToAdd.add(discordServer.getRoleById("1055272580153606174"));    // levelsdiv

        rolesToRemove.add(discordServer.getRoleById("1051772631407415346"));   // unverified
        rolesToRemove.add(discordServer.getRoleById("1055994303350063208"));    // awaiting
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (event.getComponentId().contains("ide")) {

            String data = event.getComponentId();

            String userid = data.replaceAll("ide,", "");

            event.getJDA().retrieveUserById(userid).queue((user) -> {

                event.reply("The member has been granted access to the server!").queue();

                for (Role role : rolesToAdd) {
                    discordServer.addRoleToMember(user, role).queue();
                }

                for (Role role : rolesToRemove) {
                    discordServer.removeRoleFromMember(user, role).queue();
                }

                user.openPrivateChannel().queue((channel2) -> channel2.sendMessage("You have been accepted into Florial Official. Have fun! Be sure to read up on us and interact regularly. :)" +
                        "\nUseful Links\n" +
                        " - Our game: https://discord.com/channels/801913598481268766/1165445508127526932/1165446270555541536" +
                        "\n - Server Introduction: https://discord.com/channels/801913598481268766/944360964755701790/1108307846040399873" +
                        "\n - Introduce Yourself: https://discord.com/channels/801913598481268766/919361125739548743/919361803329339454" +
                        "\n - Self-Roles: https://discord.com/channels/801913598481268766/1168698471948226731/1168806835311104051").queue());
                event.getMessage().delete().queue();
            });

        } else if (event.getComponentId().contains("ida")) {

            String userid = event.getComponentId();
            userid = userid.replaceAll("ida", "");

            event.getJDA().retrieveUserById(userid).queue((user) -> {
                event.reply("The member has been denied access, " + user.getName() + ". Be sure to tell them why with the /send command.").queue();
                user.openPrivateChannel().queue((channel2) -> channel2.sendMessage("You were denied entry into Florial Official. Shortly, the reason will be stated.").queue());
                event.getMessage().delete().queue();
            });
        }
    }
}

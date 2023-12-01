package commands;

import bot.FlorialBot;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.sql.SQLException;
import java.util.List;

@CommandInfo(name = "dbsetup")
public class DatabaseSetupCommand extends SlashCommand {

    private static Guild discordServer;

    public DatabaseSetupCommand() {
        this.name = "dbsetup";
        this.userPermissions = new Permission[]{Permission.BAN_MEMBERS};

    }

    public static void init() {

        discordServer = FlorialBot.getDiscordBot().getGuildById("801913598481268766");
    }

    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {


        if (discordServer != null) {

            discordServer.loadMembers().onSuccess(members -> {
                slashCommandEvent.reply("Caching members...").queue();
                for (Member member : members) {
                    long memberId = member.getIdLong();
                    try {
                        BotDatabase.getInstance().editDiscordProfiles(new DiscordProfile(memberId, 0, 0, 0, 0, 0, 1, 0, 0), true);
                    } catch (SQLException e) {
                        throw new RuntimeException("Exception: " + e + " for member: " + member + ", ID: " + memberId);
                    }
                }
            }).onError(error -> {
                System.out.println("Error retrieving members: " + error.getMessage());
            });

        } else {
            System.out.println("Is null");
        }



    }
}

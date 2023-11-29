package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.Permission;

@CommandInfo(name = "reset")
public class ResetTimerCommand extends SlashCommand {

    private static final BotDatabase botDatabase = new BotDatabase();


    public ResetTimerCommand() {
        this.name = "reset";
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        DiscordProfile profile;

        profile = botDatabase.findProfileByUUID(slashCommandEvent.getUser().getIdLong());

        profile.setReadWhen(System.currentTimeMillis() + (26 * 3600000));

        profile.save();


    }
}

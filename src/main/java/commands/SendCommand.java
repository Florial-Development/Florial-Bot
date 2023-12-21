package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.Arrays;

@CommandInfo(name = "send")
public class SendCommand extends SlashCommand {

    public SendCommand() {
        this.name = "send";
        this.options = Arrays.asList(
                new OptionData(OptionType.USER, "user", "The user to send the message to", true),
                new OptionData(OptionType.STRING, "message", "The message", true)
        );
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        slashCommandEvent.getOption("user").getAsUser().openPrivateChannel().queue(privateChannel -> {
            privateChannel.sendMessage(slashCommandEvent.getOption("message").getAsString()).queue();
        });

        slashCommandEvent.reply("The message has been sent.").queue();
    }
}

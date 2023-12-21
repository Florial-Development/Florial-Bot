package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.FileUpload;

import java.io.InputStream;
import java.util.List;

@CommandInfo(name = "announce")
public class AnnounceCommand extends SlashCommand {


    public AnnounceCommand() {
        this.name = "announce";
        this.options = List.of(
                new OptionData(OptionType.STRING, "contents", "what to announce", true),
                new OptionData(OptionType.ATTACHMENT, "image", "to send with the announcement", false)
        );
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        TextChannel channel = (TextChannel) slashCommandEvent.getChannel();

        String messageContent = slashCommandEvent.getOption("contents").getAsString().replace("\\n", "\n");

        if (slashCommandEvent.getOption("image") != null) {

            Message.Attachment evidence = slashCommandEvent.getOption("evidence").getAsAttachment();
            String extension = evidence.getFileExtension();
            InputStream fileInputStream = evidence.getProxy().download().join();
            FileUpload file = net.dv8tion.jda.api.utils.FileUpload.fromData(fileInputStream, "Attached Image:" + extension);
            channel.sendMessage(messageContent).addFiles(file).queue();


        } else {


            channel.sendMessage(messageContent).queue();

        }


    }
}

package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

@CommandInfo(name = "devblog")
public class CreateDevBlogCommand extends SlashCommand {

    public CreateDevBlogCommand() {
        this.name = "devblog";
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {


        slashCommandEvent.getChannel().sendMessage("")
                .addActionRow(
                        Button.primary("devblog", "\uD83C Click here to collect this Devblog! \uD83C").withStyle(ButtonStyle.SUCCESS)).queue();


    }
}

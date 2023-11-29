package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@CommandInfo(name = "verify7")
public class VerificationButtonSendCommand extends SlashCommand {

    public VerificationButtonSendCommand() {
        this.name = "verify7";
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};



    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {


        EmbedBuilder e = new EmbedBuilder();
        e.setTitle("\uD83C **Verification How-To and Guidelines** \uD83C");
        e.setFooter("Verifying keeps Florial safe.");
        e.addField("**HOW TO VERIFY**", "Click the button below to verify. After that, you will need to enter some information into a form. After you send the form to the moderators, it will be approved shortly. However, please answer honestly to the questions!", false);
        e.addField("**GUIDELINES**", "By verifying in our server, you accept that you are 13 years or older as per Discord TOS requirements. While in this server, you may not break TOS, this involves promoting piracy, talking about illegal drugs or illegal drinking, or the promotion of the latter, and more. For a full list of what you can and cannot do here, please see https://discord.com/guidelines", false);
        e.addField("**NOTICE**", "If you have come here to troll, please note that we will send you to The Void on-sight of any suspicious troll-like behavior. Once you are in there, you can not speak in or view any other channels except that one.", false);
        e.setColor(Color.pink);
        e.setImage("https://media.discordapp.net/attachments/803862530438332417/1053088710545121291/line.png");
        e.setTimestamp(Instant.now());
        slashCommandEvent.getJDA().getTextChannelById("1167972699553939506").sendMessageEmbeds(e.build()).queue();


        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {

            slashCommandEvent.getJDA().getTextChannelById("1167972699553939506").sendMessage("")
                    .addActionRow(
                            Button.primary("verification", "\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38 Verify \uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38\uD83C\uDF38"))
                    .queue();

        }, 1, TimeUnit.SECONDS);


    }
}
package listeners;

import bot.FlorialBot;
import lombok.NonNull;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

import java.awt.*;
import java.time.Instant;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ApplicationFormListener extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NonNull ModalInteractionEvent event) {
        if (event.getModalId().equals("application")) {

            Guild server = FlorialBot.getDiscordBot().getGuildById("801913598481268766");
            TextChannel verificationApplicationChannel = server.getTextChannelById("950565475107098654");

            User user = event.getUser();

            String where = event.getValue("where").getAsString();
            String why = event.getValue("why").getAsString();
            String did = event.getValue("did").getAsString();


            event.reply("Request submitted.").setEphemeral(true).queue();


            EmbedBuilder e = new EmbedBuilder().setTitle("**Verification Application of " + user.getName() + "**")
                    .setFooter("Press the buttons below to accept or deny entry.")
                    .addField("**How did you find Florial?**", where, false)
                    .addField("**Have you read the rules? Describe a rule.**", did, false)
                    .addField("**What do you plan to do in Florial?**", why, false)
                    .addField("**Account Created:** ", "" + user.getTimeCreated(), false)
                    .setColor(Color.pink)
                    .setThumbnail(user.getAvatarUrl())
                    .setImage("https://media.discordapp.net/attachments/842010486009626625/1055353813965475870/bow.png")
                    .setTimestamp(Instant.now());

            verificationApplicationChannel.sendMessageEmbeds(e.build()).queue();
            verificationApplicationChannel.sendMessage("**Make sure to look at their profile!:** " + user.getAsMention()).queue();

            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(() -> {
                server.getTextChannelById("950565475107098654").sendMessage("@here")
                        .addActionRow(
                                Button.primary("ide," + user.getId(), "Grant Access").withStyle(ButtonStyle.SUCCESS))
                        .addActionRow(
                                Button.primary("ida" + user.getId(), "Deny Access").withStyle(ButtonStyle.DANGER))
                        .queue();

            }, 1, TimeUnit.SECONDS);
        }
    }
}

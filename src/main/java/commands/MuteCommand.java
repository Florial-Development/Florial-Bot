package commands;

import bot.FlorialBot;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CommandInfo(name = "mute", requirements = {})
public class MuteCommand extends SlashCommand {

    private static final int MAX_DURATION = 1209600;
    private static final Role trustedRole = FlorialBot.getTrustedRole();

    public MuteCommand() {
        this.name = "mute";
        this.help = "Time a user out for a given length of time";
        List<OptionData> options = new ArrayList<>();
        options.add(new OptionData(OptionType.USER, "user", "The user you are muting").setRequired(true));
        options.add(new OptionData(OptionType.STRING, "reason", "The punishment reason").setRequired(true));
        options.add(new OptionData(OptionType.STRING, "duration", "The length of the punishment").setRequired(true));
        this.options = options;
    }

    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {
        Member member = slashCommandEvent.getMember();
        if (member == null) {
            respondWithError(slashCommandEvent, "There was an error trying to perform this command");
            return;
        }

        if (!slashCommandEvent.getMember().getRoles().contains(trustedRole)) {
            respondWithError(slashCommandEvent, "No permissions");
            return;
        }

        Member victim = slashCommandEvent.getOption("user").getAsMember();

        if (victim == null) {
            respondWithError(slashCommandEvent, "This user does not exist");
            return;
        }

        long duration = parseDuration(slashCommandEvent.getOption("duration").getAsString());
        duration = Math.min(duration, MAX_DURATION);

        victim.timeoutFor(duration, TimeUnit.SECONDS).queue();
        respondWithPunishmentDetails(slashCommandEvent, victim, duration);
    }

    private long parseDuration(String durationText) {
        long duration = 0;
        Pattern pattern = Pattern.compile("(\\d+[dwhms])");
        Matcher matcher = pattern.matcher(durationText);
        while (matcher.find()) {
            String match = matcher.group(1);
            char unit = match.charAt(match.length() - 1);
            int value = Integer.parseInt(match.substring(0, match.length() - 1));
            switch (unit) {
                case 'd' -> duration += value * 86400L;
                case 'w' -> duration += value * 604800L;
                case 'h' -> duration += value * 3600L;
                case 'm' -> duration += value * 60L;
                case 's' -> duration += value;
            }
        }
        return duration;
    }

    private void respondWithError(SlashCommandEvent slashCommandEvent, String errorMessage) {
        slashCommandEvent.reply(errorMessage).queue();
    }

    private void respondWithPunishmentDetails(SlashCommandEvent slashCommandEvent, Member victim, long duration) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle("Punishment processed")
                .addField("Victim", victim.getEffectiveName(), false)
                .addField("Staff", slashCommandEvent.getUser().getName(), false)
                .addField("Reason", slashCommandEvent.getOption("reason").getAsString(), false)
                .addField("Duration", formatDuration(duration), false)
                .addField("If the above punishment was not correct:",
                        "Discord limits us to a timeout maximum of 2 weeks, so your punishment length is capped at 2 weeks", false)
                .setColor(0xffd4ee)
                .setImage(victim.getAvatarUrl())
                .setFooter("Complaints must be made through our Ticket System.");

        slashCommandEvent.reply("").setEmbeds(embedBuilder.build()).queue();
    }

    private String formatDuration(long seconds) {
        long weeks = seconds / 604800;
        seconds %= 604800;
        long days = seconds / 86400;
        seconds %= 86400;
        long hours = seconds / 3600;
        seconds %= 3600;
        long minutes = seconds / 60;
        seconds %= 60;

        StringBuilder formattedDuration = new StringBuilder();
        if (weeks > 0) {
            formattedDuration.append(weeks).append("w ");
        }
        if (days > 0) {
            formattedDuration.append(days).append("d ");
        }
        if (hours > 0) {
            formattedDuration.append(hours).append("h ");
        }
        if (minutes > 0) {
            formattedDuration.append(minutes).append("m ");
        }
        if (seconds > 0) {
            formattedDuration.append(seconds).append("s");
        }

        return formattedDuration.toString();
    }
}




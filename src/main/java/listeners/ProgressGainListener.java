package listeners;

import bot.FlorialBot;
import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import quests.QuestType;

import java.time.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ProgressGainListener extends ListenerAdapter {

    private static final List<String> valuableChannels = Arrays.asList("919361125739548743", "919361125739548743", "938256684286083132", "943028902828322826");
    private static final TextChannel botDMs = FlorialBot.getBotDMs();

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User user = event.getAuthor();

        if (user.isBot() || (!(event.isFromGuild())) || event.getChannel() == botDMs) return;

        Member member = event.getMember();

        DiscordProfile profile = BotDatabase.getInstance().findProfileByUUID(user.getIdLong());

        if (profile.getCurrentQuestId() != 0) {

            ZoneId cstZone = ZoneId.of("America/Chicago");

            ZonedDateTime lastQuestDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(profile.getLastQuest()), cstZone);

            ZonedDateTime next12AM = ZonedDateTime.now(cstZone).with(LocalTime.of(0, 0)).plusDays(1);

            QuestType quest = QuestType.findQuestTypeById(profile.getCurrentQuestId());

            if (lastQuestDateTime.isBefore(next12AM) && profile.getQuestProgress() < quest.getAmountNeeded()) {
                long specificChannelId = quest.getSpecificChannelId();
                if (specificChannelId != 0 && event.getChannel().getIdLong() == specificChannelId) {
                    profile.progressInQuest(user);
                } else if (specificChannelId == 0) {
                    profile.progressInQuest(user);
                }
            }

        }


        if (valuableChannels.contains(event.getChannel().getId())) {
            profile.gainExperience(profile.getLvl() * 100 / (member.getTimeBoosted() == null ? 12 : 10), user);

        } else if (new Random().nextBoolean()) {
            profile.gainExperience(new Random().nextInt(member.getTimeBoosted() == null ? 3 : 7), user);
        }

    }

}


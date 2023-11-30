package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import databases.BotDatabase;
import databases.models.DiscordProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import quests.QuestType;

import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

@CommandInfo(name = "dailyquest")
public class DailyQuestCommand extends SlashCommand {


    public DailyQuestCommand() {
        this.name = "dailyquest";
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        User user = slashCommandEvent.getUser();

        DiscordProfile profile = BotDatabase.getInstance().findProfileByUUID(user.getIdLong());

        LocalDateTime lastQuestTime = LocalDateTime.ofEpochSecond(profile.getLastQuest(), 0, ZoneOffset.UTC);

        ZoneId cstZone = ZoneId.of("America/Chicago");
        ZonedDateTime lastQuestCST = lastQuestTime.atZone(cstZone);

        ZonedDateTime nowCST = ZonedDateTime.now(cstZone);

        if (nowCST.toLocalDate().isAfter(lastQuestCST.toLocalDate()) || profile.getLastQuest() == 0) {
            profile.setLastQuest(nowCST.toEpochSecond());
            profile.setCurrentQuestId(QuestType.getRandomQuest().getId());
            profile.setQuestProgress(0);
            profile.save();
            generateQuestEmbed((TextChannel) slashCommandEvent.getChannel(), profile.getCurrentQuestId(), profile);
            slashCommandEvent.reply("Quest Generated:").queue();
        } else {

            generateQuestEmbed((TextChannel) slashCommandEvent.getChannel(), profile.getCurrentQuestId(), profile);
            slashCommandEvent.reply("Quest Progress:").queue();

        }
    }



    private static void generateQuestEmbed(TextChannel channel, int questId, DiscordProfile profile) {

        QuestType quest = QuestType.findQuestTypeById(questId);

        EmbedBuilder e = new EmbedBuilder().setTitle("<:crystalheart:1168455971958439936>                           **DAILY QUEST**               <:crystalheart:1168455971958439936>")
                .addField("**Description**                     ✰ ", quest.getDescription(), true)
                .addField("                               **Progress**", "                                 " + profile.generateProgressBar((int) Math.round(((double) profile.getQuestProgress() / quest.getAmountNeeded()) * 100)), true)
                .setColor(Color.pink)
                .setImage("https://media.discordapp.net/attachments/564923688621834251/1179691284345143346/image.png?ex=657ab41f&is=65683f1f&hm=59d395f770ca7894990b65bc32258e3161cf220b6478c2bb37d8332fae59599f&=&format=webp&quality=lossless")
                .setThumbnail("https://media.discordapp.net/attachments/564923688621834251/1179724541283536957/arrowup.png?ex=657ad318&is=65685e18&hm=eeb2a0eb2e96bd0df9429e94d1f74a78b85020d67ee9827c6af3d3c09507b121&=&format=webp&quality=lossless");

        channel.sendMessageEmbeds(e.build()).queue();
    }
}

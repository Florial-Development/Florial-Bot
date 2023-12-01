package databases.models;

import databases.BotDatabase;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import quests.QuestType;

import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class DiscordProfile {

    BotDatabase botDatabase = new BotDatabase();

    @Getter @Setter
    private long uuid;
    @Getter @Setter private int devBlogs;
    @Getter @Setter private int tokens;

    @Getter @Setter private long readWhen;
    @Getter @Setter private long lastQuest;

    @Getter @Setter private int xp;
    @Getter @Setter private int lvl;
    @Getter @Setter private int questProgress;
    @Getter @Setter private int currentQuestId;


    private static final Map<Integer, String> xpBarMappings = new LinkedHashMap<>() {{
        put(100, "<:GuiLeftCorner50:1178603918553526399><:GuiMiddle50:1178603919371403344><:GuiRightCorner100:1178603920713592852>");
        put(90, "<:GuiLeftCorner50:1178603918553526399><:GuiMiddle50:1178603919371403344><:GuiRightCorner90:1178603984286658570>");
        put(80, "<:GuiLeftCorner50:1178603918553526399><:GuiMiddle50:1178603919371403344><:GuiRightCorner80:1178603982357278731>");
        put(70, "<:GuiLeftCorner50:1178603918553526399><:GuiMiddle50:1178603919371403344><:GuiRightCorner70:1178603981874929704>");
        put(65, "<:GuiLeftCorner50:1178603918553526399><:GuiMiddle50:1178603919371403344><:GuiRightCorner65:1178603980239155292>");
        put(60, "<:GuiLeftCorner50:1178603918553526399><:GuiMiddle50:1178603919371403344><:GuiRightCorner60:1178603979308011560>");
        put(50, "<:GuiLeftCorner50:1178603918553526399><:GuiMiddle50:1178603919371403344><:GuiRightCorner0:1178603950728028240>");
        put(40, "<:GuiLeftCorner40:1178603949771730994><:GuiMiddleBelow50:1178603985456857139><:GuiRightCorner0:1178603950728028240>");
        put(35, "<:GuiLeftCorner35:1178603948794462278><:GuiMiddleBelow50:1178603985456857139><:GuiRightCorner0:1178603950728028240>");
        put(20, "<:GuiLeftCorner20:1178603947863318578><:GuiMiddleBelow50:1178603985456857139><:GuiRightCorner0:1178603950728028240>");
        put(10, "<:GuiLeftCorner10:1178603946756018216><:GuiMiddleBelow50:1178603985456857139><:GuiRightCorner0:1178603950728028240>");
        put(2, "<:GuiLeftCorner5:1178603945841676318><:GuiMiddleBelow50:1178603985456857139><:GuiRightCorner0:1178603950728028240>");
        put(0, "<:GuiLeftCorner0:1178603921606983721><:GuiMiddleBelow50:1178603985456857139><:GuiRightCorner0:1178603950728028240>");
    }};

    private static final List<Integer> storyLevels = Arrays.asList(2, 3, 5, 8, 12, 15, 18, 22, 25);

    public DiscordProfile(long uuid, int devBlogs, int tokens, long readWhen, long lastQuest, int xp, int lvl, int questProgress, int currentQuestId) {
        this.uuid = uuid;
        this.devBlogs = devBlogs;
        this.tokens = tokens;
        this.readWhen = readWhen;
        this.lastQuest = lastQuest;
        this.xp = xp;
        this.lvl = lvl;
        this.questProgress = questProgress;
        this.currentQuestId = currentQuestId;


    }

    public void delete()  {

        try {
            botDatabase.deleteProfile(this.uuid);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void save() {

        try {
            botDatabase.editDiscordProfiles(this, false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void gainTokens(int i) {
        this.tokens = this.tokens + i;
        this.save();
    }
    public void progressInQuest(User user) {

        this.questProgress++;
        QuestType quest = QuestType.findQuestTypeById(this.currentQuestId);
        int amountNeeded = quest.getAmountNeeded();

        if (this.questProgress >= amountNeeded) {

            int earnedTokens = new Random().nextInt(15);
            int experienceGained = this.lvl * 100 / 12;

            EmbedBuilder e = new EmbedBuilder().setTitle("<:crystalheart:1168455971958439936>       **DAILY QUEST COMPLETED!**      <:crystalheart:1168455971958439936>")
                    .addField("**Description**", quest.getDescription(), false)
                    .addField("**Progress**", "" + generateProgressBar((int) Math.round(((double) this.questProgress / amountNeeded) * 100)), false)
                    .addField("**Tokens Earned**", "`" + earnedTokens + "`", false)
                    .addField("**XP Earned**", "`" + experienceGained + "`", false)
                    .addField("**━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━**", "Do /dailyquest in any channel again to get a new one. Quests reset everyday at 12AM CST!", false)
                    .setColor(Color.pink)
                    .setImage("https://media.discordapp.net/attachments/564923688621834251/1179691284345143346/image.png?ex=657ab41f&is=65683f1f&hm=59d395f770ca7894990b65bc32258e3161cf220b6478c2bb37d8332fae59599f&=&format=webp&quality=lossless")
                    .setThumbnail("https://media.discordapp.net/attachments/564923688621834251/1179724541283536957/arrowup.png?ex=657ad318&is=65685e18&hm=eeb2a0eb2e96bd0df9429e94d1f74a78b85020d67ee9827c6af3d3c09507b121&=&format=webp&quality=lossless");

            gainExperience(experienceGained, user);
            this.tokens = earnedTokens;

            user.openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessageEmbeds(e.build()).queue();

            });

            this.save();
        }

    }


    public void gainExperience(int i, User user) {

        int totalXP = this.xp + i;
        int levelUpThreshold = this.lvl * 100;

        if (totalXP >= levelUpThreshold) {

            this.lvl++;
            this.xp = totalXP - levelUpThreshold;
            int earnedTokens = 10 + this.lvl;

            this.gainTokens(earnedTokens);

            EmbedBuilder e = new EmbedBuilder().setTitle("<:crystalheart:1168455971958439936>                                                                                           <:crystalheart:1168455971958439936>")
                    .addField("**NEW LEVEL**                     ✰ ", "`" + lvl + "`", true)
                    .addField("                               **TOKENS EARNED**", "                                `" + earnedTokens + "`", true)
                    .addField("**━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━**", "", false)
                    .setColor(Color.pink)
                    .setImage("https://media.discordapp.net/attachments/842010486009626625/1179228062404071464/raf360x360075tfafafa_ca443f4786.png?ex=657904b7&is=65668fb7&hm=162570802303f86709b41e9264028fd9509e9caad3e693399c7fd673adc29340&=&format=webp&quality=lossless");

            user.openPrivateChannel().queue(privateChannel -> {
                privateChannel.sendMessageEmbeds(e.build()).queue();
                this.fetchProfile(privateChannel, user);
                int nextLevel = storyLevels.stream().filter(level -> level > this.lvl).findFirst().orElse(-1);
                int progressPercentage = storyProgress(this.lvl, nextLevel, this.xp);
                privateChannel.sendMessage("**Progress to next story at level " + nextLevel + ":** " + generateProgressBar(progressPercentage)).queue();
                if (storyLevels.contains(this.lvl)) {
                    privateChannel.sendMessage("")
                            .addActionRow(
                                    Button.primary("story" + this.lvl, "Tap here to unlock the next story!"))
                            .queue();

                }

            });

        } else {
            this.xp = totalXP;
        }

        this.save();


    }

    public void fetchProfile(Channel channel, User user) {

        int xpToLevelUp = this.lvl * 100;
        int xpPercentage = (int) ((double) this.xp / xpToLevelUp * 100);

        EmbedBuilder e = new EmbedBuilder()
                .setTitle("<:crystalheart:1168455971958439936> **Profile of " + user.getName() + "**  <:crystalheart:1168455971958439936>")
                .addField("<:arrowup:1179693853280849990> Level", "`" + this.lvl + "`", true)
                .addField("<:bulletpoint:1168454567705456650> Experience", generateProgressBar(xpPercentage), true)
                .addField("<:florialcoin:1108293880971014184> Tokens", "`" + this.tokens + "`", true)
                .setColor(Color.pink)
                .setThumbnail(user.getAvatarUrl())
                .setImage("https://media.discordapp.net/attachments/842010486009626625/1178621643887751269/7f271fce824cbafd5563f8c18c6404ee.gif?ex=6576cff1&is=65645af1&hm=31a4cb4c4e00f3794ef32e0b73f085571c594ca3b353ec321c7d7a001c716626&=");

        if (channel instanceof TextChannel) {
            ((TextChannel)channel).sendMessageEmbeds(e.build()).queue();
        } else if (channel instanceof PrivateChannel) {
            ((PrivateChannel)channel).sendMessageEmbeds(e.build()).queue();
        }
    }

    public String generateProgressBar(int percentage) {

        for (int key : xpBarMappings.keySet()) {
            if (percentage >= key) {return xpBarMappings.get(key) + " **" + percentage + "%**";}
        }

        return "";
    }

    private static int storyProgress(int currentLevel, int targetLevel, int currentXP) {

        double levelDiff = targetLevel - currentLevel;
        int xpNeeded = currentLevel * 100;

        double levelFactor = levelDiff * 0.15;

        double progressPercentage = (((double) currentXP / xpNeeded) * (1 - levelFactor) + (levelFactor)) * 100;

        return (int) progressPercentage;

    }

}

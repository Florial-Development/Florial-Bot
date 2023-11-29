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

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DiscordProfile {

    BotDatabase botDatabase = new BotDatabase();

    @Getter @Setter
    private long uuid;
    @Getter @Setter private int devBlogs;
    @Getter @Setter private int tokens;

    @Getter @Setter private long readWhen;
    @Getter @Setter private int xp;
    @Getter @Setter private int lvl;

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

    public DiscordProfile(long uuid, int devBlogs, int tokens, long readWhen, int xp, int lvl) {
        this.uuid = uuid;
        this.devBlogs = devBlogs;
        this.tokens = tokens;
        this.readWhen = readWhen;
        this.xp = xp;
        this.lvl = lvl;

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

            });

        } else {
            this.xp = totalXP;
        }

        this.save();


    }

    public void gainTokens(int i) {
        this.tokens = this.tokens + i;
        this.save();
    }

    public void fetchProfile(Channel channel, User user) {

        EmbedBuilder e = new EmbedBuilder()
                .setTitle("<:crystalheart:1168455971958439936> **Profile of " + user.getName() + "**  <:crystalheart:1168455971958439936>                     <:heartbox:1168454374914261002>")
                .addField("Level", "`" + this.lvl + "`", true)
                .addField("Experience", generateXPBar(this.xp, this.lvl), true)
                .addField("Tokens", "`" + this.tokens + "`", true)
                .setColor(Color.pink)
                .setThumbnail(user.getAvatarUrl())
                .setImage("https://media.discordapp.net/attachments/842010486009626625/1178621643887751269/7f271fce824cbafd5563f8c18c6404ee.gif?ex=6576cff1&is=65645af1&hm=31a4cb4c4e00f3794ef32e0b73f085571c594ca3b353ec321c7d7a001c716626&=");

        if (channel instanceof TextChannel) {
            ((TextChannel)channel).sendMessageEmbeds(e.build()).queue();
        } else if (channel instanceof PrivateChannel) {
            ((PrivateChannel)channel).sendMessageEmbeds(e.build()).queue();
        }
    }

    private static String generateXPBar(int currentXP, int level) {
        int xpToLevelUp = level * 100;
        int xpPercentage = (int) ((double) currentXP / xpToLevelUp * 100);

        for (int key : xpBarMappings.keySet()) {
            if (xpPercentage >= key) {return xpBarMappings.get(key) + " **" + xpPercentage + "%**";}
        }

        return "";
    }

}

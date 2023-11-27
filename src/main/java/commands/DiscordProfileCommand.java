package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import database.BotDatabase;
import database.DiscordProfile;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@CommandInfo(name = "profile")
public class DiscordProfileCommand extends SlashCommand {

    private static final BotDatabase botDatabase = new BotDatabase();

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


    public DiscordProfileCommand() {
        this.name = "profile";
        this.options = List.of(
                new OptionData(OptionType.USER, "user", "The user to get the profile of", true));

    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        DiscordProfile profile;
        User user = slashCommandEvent.getOption("user").getAsUser();

        try {
            profile = botDatabase.findProfileByUUID(user.getIdLong());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int level = profile.getLvl();

        EmbedBuilder e = new EmbedBuilder();
        e.setTitle("<:crystalheart:1168455971958439936> **Profile of " + user.getName() + "**  <:crystalheart:1168455971958439936>                     <:heartbox:1168454374914261002>");
        e.addField("Level", "`" + level + "`", true);
        e.addField("Experience", generateXPBar(profile.getXp(), level), true);
        e.addField("Tokens", "`" + profile.getTokens() + "`", true);
        e.setColor(Color.pink);
        e.setThumbnail(user.getAvatarUrl());
        e.setImage("https://media.discordapp.net/attachments/842010486009626625/1178621643887751269/7f271fce824cbafd5563f8c18c6404ee.gif?ex=6576cff1&is=65645af1&hm=31a4cb4c4e00f3794ef32e0b73f085571c594ca3b353ec321c7d7a001c716626&=");
        slashCommandEvent.getChannel().sendMessageEmbeds(e.build()).queue();


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

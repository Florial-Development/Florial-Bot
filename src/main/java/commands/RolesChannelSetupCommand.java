package commands;

import bot.FlorialBot;
import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@CommandInfo(name = "rolesetup")
public class RolesChannelSetupCommand extends SlashCommand {

    private static Guild discordServer;

    public RolesChannelSetupCommand() {
        this.name = "rolesetup";
        this.userPermissions = new Permission[]{Permission.MESSAGE_MANAGE};

    }

    public static void init() {

        discordServer = FlorialBot.getDiscordServer();
    }
    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        sendEmbedsWithButtons();

    }

    public void sendEmbedsWithButtons() {

        TextChannel roleChannel = discordServer.getTextChannelById("1168698471948226731");

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(4);

            sendEmbedWithButtons(
                    "ㅤㅤㅤㅤㅤ<:crystalheart:1168455971958439936> **•• ꒰Select your Gender!~꒱ ••** <:crystalheart:1168455971958439936>",
                    List.of("♀ Female", "♂ Male", "? Other"),
                    List.of("role,female,919147510944104468,one,\uD835\uDC20\uD835\uDC1E\uD835\uDC27\uD835\uDC1D\uD835\uDC1E\uD835\uDC2B",
                            "role,male,919147210480963644,one,\uD835\uDC20\uD835\uDC1E\uD835\uDC27\uD835\uDC1D\uD835\uDC1E\uD835\uDC2B",
                            "role,other,919147694109380608,one,\uD835\uDC20\uD835\uDC1E\uD835\uDC27\uD835\uDC1D\uD835\uDC1E\uD835\uDC2B"),
                    roleChannel
            );

        scheduler.schedule(() -> sendEmbedWithButtons(
                "ㅤㅤㅤㅤ<:crystalheart:1168455971958439936> **•• ꒰Select your Pronouns!~꒱ ••** <:crystalheart:1168455971958439936>",
                List.of("She/Her", "He/Him", "They/Them", "Any/all", "Check my bio!"),
                List.of("role,sheher,919153912953602048,any,\uD835\uDC29\uD835\uDC2B\uD835\uDC28\uD835\uDC27\uD835\uDC28\uD835\uDC2E\uD835\uDC27\uD835\uDC2C",
                        "role,hehim,919153933044318238,any,\uD835\uDC29\uD835\uDC2B\uD835\uDC28\uD835\uDC27\uD835\uDC28\uD835\uDC2E\uD835\uDC27\uD835\uDC2C",
                        "role,theythem,919153949720870912,any,\uD835\uDC29\uD835\uDC2B\uD835\uDC28\uD835\uDC27\uD835\uDC28\uD835\uDC2E\uD835\uDC27\uD835\uDC2C",
                        "role,anyall,919153969044013056,any,\uD835\uDC29\uD835\uDC2B\uD835\uDC28\uD835\uDC27\uD835\uDC28\uD835\uDC2E\uD835\uDC27\uD835\uDC2C",
                        "role,checkmybio,919154000899764254,any,\uD835\uDC29\uD835\uDC2B\uD835\uDC28\uD835\uDC27\uD835\uDC28\uD835\uDC2E\uD835\uDC27\uD835\uDC2C"),
                roleChannel
        ), 1, TimeUnit.SECONDS);



        scheduler.schedule(() -> sendEmbedWithButtons(
                "ㅤㅤㅤㅤㅤㅤ<:crystalheart:1168455971958439936> **•• ꒰Select your Age!~꒱ ••** <:crystalheart:1168455971958439936>",
                List.of("13", "14", "15", "16", "17", "18+"),
                List.of("role,13,919150961530925106,one,\uD835\uDC1A\uD835\uDC20\uD835\uDC1E",
                        "role,14,919151140522835988,one,\uD835\uDC1A\uD835\uDC20\uD835\uDC1E",
                        "role,15,919151173901107261,one,\uD835\uDC1A\uD835\uDC20\uD835\uDC1E",
                        "role,16,919151185133449226,one,\uD835\uDC1A\uD835\uDC20\uD835\uDC1E",
                        "role,17,919151195002658837,one,\uD835\uDC1A\uD835\uDC20\uD835\uDC1E",
                        "role,18,919151208550244412,one,\uD835\uDC1A\uD835\uDC20\uD835\uDC1E"),
                roleChannel
        ), 2, TimeUnit.SECONDS);

        scheduler.schedule(() -> sendEmbedWithButtons(
                "ㅤㅤㅤㅤㅤ<:crystalheart:1168455971958439936> **•• ꒰Select your Location!~꒱ ••** <:crystalheart:1168455971958439936>",
                List.of("North America", "Asia", "Europe", "Oceania", "Africa", "United Kingdom", "South America"),
                List.of("role,na,919156317149597737,one,\uD835\uDC25\uD835\uDC28\uD835\uDC1C\uD835\uDC1A\uD835\uDC2D\uD835\uDC22\uD835\uDC28\uD835\uDC27",
                        "role,as,966107067624620103,one,\uD835\uDC25\uD835\uDC28\uD835\uDC1C\uD835\uDC1A\uD835\uDC2D\uD835\uDC22\uD835\uDC28\uD835\uDC27",
                        "role,eu,919156448884318218,one,\uD835\uDC25\uD835\uDC28\uD835\uDC1C\uD835\uDC1A\uD835\uDC2D\uD835\uDC22\uD835\uDC28\uD835\uDC27",
                        "role,oce,919156425396195338,one,\uD835\uDC25\uD835\uDC28\uD835\uDC1C\uD835\uDC1A\uD835\uDC2D\uD835\uDC22\uD835\uDC28\uD835\uDC27",
                        "role,afr,919156497643094066,one,\uD835\uDC25\uD835\uDC28\uD835\uDC1C\uD835\uDC1A\uD835\uDC2D\uD835\uDC22\uD835\uDC28\uD835\uDC27",
                        "role,uk,919156467490250766,one,\uD835\uDC25\uD835\uDC28\uD835\uDC1C\uD835\uDC1A\uD835\uDC2D\uD835\uDC22\uD835\uDC28\uD835\uDC27",
                        "role,sa,919156359520456735,one,\uD835\uDC25\uD835\uDC28\uD835\uDC1C\uD835\uDC1A\uD835\uDC2D\uD835\uDC22\uD835\uDC28\uD835\uDC27"),
                roleChannel
        ), 3, TimeUnit.SECONDS);

        scheduler.schedule(() -> sendEmbedWithButtons(
                "ㅤㅤㅤㅤㅤ<:crystalheart:1168455971958439936> **•• ꒰Select your Likes!~꒱ ••** <:crystalheart:1168455971958439936>",
                List.of("Art", "Gaming/Techie", "Anime", "Outdoors", "Cars", "Fitness/Sports", "Writing", "Coding", "FOOD!!"),
                List.of("role,art,919162536790724638,any,\uD835\uDC25\uD835\uDC22\uD835\uDC24\uD835\uDC1E\uD835\uDC2C",
                        "role,gam,919162548627070996,any,\uD835\uDC25\uD835\uDC22\uD835\uDC24\uD835\uDC1E\uD835\uDC2C",
                        "role,ani,919162470004822048,any,\uD835\uDC25\uD835\uDC22\uD835\uDC24\uD835\uDC1E\uD835\uDC2C",
                        "role,out,919162589701881857,any,\uD835\uDC25\uD835\uDC22\uD835\uDC24\uD835\uDC1E\uD835\uDC2C",
                        "role,car,919162602800701451,any,\uD835\uDC25\uD835\uDC22\uD835\uDC24\uD835\uDC1E\uD835\uDC2C",
                        "role,fit,919162626662080522,any,\uD835\uDC25\uD835\uDC22\uD835\uDC24\uD835\uDC1E\uD835\uDC2C",
                        "role,wri,919162732329201674,any,\uD835\uDC25\uD835\uDC22\uD835\uDC24\uD835\uDC1E\uD835\uDC2C",
                        "role,cod,919163919946358784,any,\uD835\uDC25\uD835\uDC22\uD835\uDC24\uD835\uDC1E\uD835\uDC2C",
                        "role,fod,919162766563090453,any,\uD835\uDC25\uD835\uDC22\uD835\uDC24\uD835\uDC1E\uD835\uDC2C"),
                roleChannel
        ), 4, TimeUnit.SECONDS);

        scheduler.schedule(() -> sendEmbedWithButtons(
                "ㅤㅤㅤㅤㅤ<:crystalheart:1168455971958439936> **•• ꒰Select your Pings!~꒱ ••** <:crystalheart:1168455971958439936>",
                List.of("Devblogs", "Discord", "Announcements", "Question Of The Day", "Revive Chat"),
                List.of("role,dev,919364626003689494,any,\uD835\uDC29\uD835\uDC22\uD835\uDC27\uD835\uDC20\uD835\uDC2C",
                        "role,dis,919364653816111174,any,\uD835\uDC29\uD835\uDC22\uD835\uDC27\uD835\uDC20\uD835\uDC2C",
                        "role,ann,919364693557145700,any,\uD835\uDC29\uD835\uDC22\uD835\uDC27\uD835\uDC20\uD835\uDC2C",
                        "role,qot,957692232855605311,any,\uD835\uDC29\uD835\uDC22\uD835\uDC27\uD835\uDC20\uD835\uDC2C",
                        "role,rev,1053454680308584468,any,\uD835\uDC29\uD835\uDC22\uD835\uDC27\uD835\uDC20\uD835\uDC2C"),
                roleChannel
        ), 5, TimeUnit.SECONDS);

    }

    private static void sendEmbedWithButtons(String title, List<String> buttonLabels, List<String> buttonIDs, TextChannel roleChannel) {

        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(title);
        embedBuilder.setColor(Color.PINK);
        embedBuilder.setImage("https://media.discordapp.net/attachments/842010486009626625/1055353813965475870/bow.png?width=899&height=192");

        Button[] buttons = new Button[buttonLabels.size()];

        for (int i = 0; i < buttonLabels.size(); i++) {
            buttons[i] = Button.primary(buttonIDs.get(i), buttonLabels.get(i));
        }

        roleChannel.sendMessageEmbeds(embedBuilder.build()).addActionRow(Arrays.copyOfRange(buttons, 0, Math.min(5, buttonLabels.size()))).queue();

        if (buttons.length > 5) {roleChannel.sendMessage("").addActionRow(Arrays.copyOfRange(buttons, 5, buttons.length)).queue();}

    }
}


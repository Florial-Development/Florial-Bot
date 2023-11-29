package bot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import commands.*;
import databases.BotDatabase;
import databases.StoryDatabase;
import listeners.*;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlorialBot {

    private static final BotDatabase botDatabase = new BotDatabase();
    private static final StoryDatabase storyDatabase = new StoryDatabase();

    private static final String BOT_TOKEN = "";
    @Getter
    private static JDA discordBot;
    @Getter private static Role trustedRole;
    @Getter private static Role subscribedRole;

    @Getter private static Guild discordServer;
    @Getter private static TextChannel botDMs;
    @Getter private static TextChannel verifyChannel;


    public static void main(String[] args) throws SQLException {

        initializeDiscord();

        storyDatabase.getConnection();
    }


    private static void initializeDiscord() {

        CommandClientBuilder builder = new CommandClientBuilder()
                .setPrefix("/")
                .forceGuildOnly("801913598481268766")
                .setOwnerId("349819317589901323")
                .setCoOwnerIds("366301720109776899")
                .addSlashCommands(new VerificationButtonSendCommand(), new SendCommand(), new WarnCommand(), new MuteCommand(), new RolesChannelSetupCommand(), new TestCommand(), new CreateDevBlogCommand(), new ResetTimerCommand(), new DiscordProfileCommand())
                .setHelpWord(null)
                .setActivity(Activity.watching("Florial"));
        CommandClient commandClient = builder.build();

        JDABuilder discordBotBuilder = JDABuilder.createDefault(BOT_TOKEN,
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.GUILD_MESSAGE_REACTIONS,
                        GatewayIntent.GUILD_VOICE_STATES,
                        GatewayIntent.DIRECT_MESSAGES,
                        GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                        GatewayIntent.GUILD_PRESENCES,
                        GatewayIntent.MESSAGE_CONTENT)
                .addEventListeners(commandClient, new ApplicationFormListener(), new VerificationButtonListener(), new JoinLeaveServerListener(), new DMListener(), new AcceptDenyButtonListener(), new SubscribeButtonListener(), new RoleButtonListener(), new DevBlogButtonListener(), new ExperienceGainListener())
                .setActivity(Activity.watching("Florial"));

        discordBot = discordBotBuilder.build();

        discordServer = discordBot.getGuildById("801913598481268766");


        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            discordServer = discordBot.getGuildById("801913598481268766");
            trustedRole = discordServer.getRoleById("1036874193943396352");
            botDMs = discordBot.getTextChannelById("950565475107098654");
            verifyChannel = discordServer.getTextChannelById("950565475107098654");
            subscribedRole = discordServer.getRoleById("919364626003689494");
            AcceptDenyButtonListener.init();
            SubscribeButtonListener.init();
            RoleButtonListener.init();
            RolesChannelSetupCommand.init();
        }, 1, TimeUnit.SECONDS);

    }
}
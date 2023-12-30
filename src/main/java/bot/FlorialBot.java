package bot;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import commands.*;
import databases.models.DiscordProfile;
import listeners.*;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class FlorialBot {

    private static String BOT_TOKEN;

    @Getter public static String DATABASE;
    @Getter public static String DATABASE_USER;
    @Getter public static String DATABASE_PASSWORD;
    @Getter
    private static JDA discordBot;
    @Getter private static Role trustedRole;
    @Getter private static Role subscribedRole;

    @Getter private static Guild discordServer;
    @Getter private static TextChannel verifyChannel;

    @Getter private static final HashMap<Long, DiscordProfile> activeProfileCache = new HashMap<>();


    public static void main(String[] args) {


        BOT_TOKEN = System.getenv("TOKEN");
        DATABASE = System.getenv("DATABASE");
        DATABASE_USER = System.getenv("DATABASE_USER");
        DATABASE_PASSWORD = System.getenv("DATABASE_PASS");

        Runtime.getRuntime().addShutdownHook(new Thread(FlorialBot::shutdownBot));

        initializeDiscord();

    }


    private static void initializeDiscord() {

        CommandClientBuilder builder = new CommandClientBuilder()
                .setPrefix("/")
                .forceGuildOnly("801913598481268766")
                .setOwnerId("349819317589901323")
                .setCoOwnerIds("366301720109776899")
                .addSlashCommands(new VerificationButtonSendCommand(), new SendCommand(), new WarnCommand(), new MuteCommand(), new RolesChannelSetupCommand(), new TestCommand(), new CreateDevBlogCommand(), new AnnounceCommand(), new DiscordProfileCommand(), new LevelSetCommand(), new XPSetCommand(), new DailyQuestCommand(), new DatabaseSetupCommand())
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
                .addEventListeners(commandClient, new ApplicationFormListener(), new VerificationButtonListener(), new JoinLeaveServerListener(), new DMListener(), new AcceptDenyButtonListener(), new SubscribeButtonListener(), new RoleButtonListener(), new DevBlogButtonListener(), new ProgressGainListener(), new StoryButtonListener())
                .setActivity(Activity.watching("Florial"));

        discordBot = discordBotBuilder.build();

        discordServer = discordBot.getGuildById("801913598481268766");


        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            discordServer = discordBot.getGuildById("801913598481268766");
            trustedRole = discordServer.getRoleById("1036874193943396352");
            verifyChannel = discordServer.getTextChannelById("950565475107098654");
            subscribedRole = discordServer.getRoleById("919364626003689494");
            AcceptDenyButtonListener.init();
            SubscribeButtonListener.init();
            RoleButtonListener.init();
            RolesChannelSetupCommand.init();
            DatabaseSetupCommand.init();
            DMListener.init();
        }, 3, TimeUnit.SECONDS);

    }

    private static void shutdownBot() {
        if (discordBot != null) {
            discordBot.shutdown();
        }
    }

}
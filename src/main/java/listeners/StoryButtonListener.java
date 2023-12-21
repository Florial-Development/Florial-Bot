package listeners;

import commands.RolesChannelSetupCommand;
import databases.StoryDatabase;
import databases.models.Story;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StoryButtonListener extends ListenerAdapter {


    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        String id = event.getComponentId();

        if (id.contains("story")) {

            int storyType = Integer.parseInt(id.replaceAll("story", ""));

            Story story = StoryDatabase.getInstance().getStoryByLevel(storyType);

            EmbedBuilder e = new EmbedBuilder().setTitle("<:crystalheart:1168455971958439936> ** | " + story.getName() + ", Page 1**")
                    .addField("**━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━**", story.getPart_1(), false)
                    .setColor(Color.pink)
                    .setThumbnail("https://media.discordapp.net/attachments/947274867349270529/1179603922248278056/image1.png?ex=657a62c3&is=6567edc3&hm=fcb1e7b4628941b107209f774610bafdd932c2ffcd02eabaab4b1dd24ebc9d7f&=&format=webp&quality=lossless&width=521&height=521")
                    .setImage(story.getImage_1());
            EmbedBuilder e2 = new EmbedBuilder().setTitle("<:crystalheart:1168455971958439936> ** | " + story.getName() + ", Page 2**")
                    .addField("**━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━**", story.getPart_2(), false)
                    .setColor(Color.pink)
                    .setThumbnail("https://media.discordapp.net/attachments/947274867349270529/1179603922248278056/image1.png?ex=657a62c3&is=6567edc3&hm=fcb1e7b4628941b107209f774610bafdd932c2ffcd02eabaab4b1dd24ebc9d7f&=&format=webp&quality=lossless&width=521&height=521")
                    .setImage(story.getImage_2());
            EmbedBuilder e3 = new EmbedBuilder().setTitle("<:crystalheart:1168455971958439936> ** | " + story.getName() + ", Page 3**")
                    .addField("**━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━**", story.getPart_3(), false)
                    .setColor(Color.pink)
                    .setThumbnail("https://media.discordapp.net/attachments/947274867349270529/1179603922248278056/image1.png?ex=657a62c3&is=6567edc3&hm=fcb1e7b4628941b107209f774610bafdd932c2ffcd02eabaab4b1dd24ebc9d7f&=&format=webp&quality=lossless&width=521&height=521")
                    .setImage(story.getImage_3());

            EmbedBuilder e4 = new EmbedBuilder().setTitle("<:crystalheart:1168455971958439936> ** | Keep leveling up to see what happens next time!**")
                    .addField("**━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━**", "", false)
                    .setColor(Color.pink)
                    .setThumbnail("https://media.discordapp.net/attachments/947274867349270529/1179603922248278056/image1.png?ex=657a62c3&is=6567edc3&hm=fcb1e7b4628941b107209f774610bafdd932c2ffcd02eabaab4b1dd24ebc9d7f&=&format=webp&quality=lossless&width=521&height=521")
                    .setImage("https://media.discordapp.net/attachments/564923688621834251/1179691284345143346/image.png?ex=657ab41f&is=65683f1f&hm=59d395f770ca7894990b65bc32258e3161cf220b6478c2bb37d8332fae59599f&=&format=webp&quality=lossless");


            event.replyEmbeds(e.build()).queue();


            ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
            scheduler.schedule(() -> {
                event.getChannel().sendMessageEmbeds(e2.build()).queue();
                event.getChannel().sendMessageEmbeds(e3.build()).queue();
                event.getChannel().sendMessageEmbeds(e4.build()).queue();
            }, 1, TimeUnit.SECONDS);

            event.getMessage().delete().queue();

            System.out.println("" + event.getUser().getName() + " just unlocked a story.");


        }
    }
}

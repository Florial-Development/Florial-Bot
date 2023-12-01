package listeners;

import bot.FlorialBot;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class VerificationButtonListener extends ListenerAdapter {


    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (event.getComponentId().equals("verification")) {

            Guild discord = FlorialBot.getDiscordBot().getGuildById("801913598481268766");

            Role tulip = discord.getRoleById("801913598644846604");
            Role awaiting = discord.getRoleById("1055994303350063208");

            if (event.getMember().getRoles().contains(tulip)) {
                event.reply("You are already verified.").setEphemeral(true).queue();
                return;
            } else if (event.getMember().getRoles().contains(awaiting)) {
                event.reply("You are already in the verification process. If you are stuck and unable to verify, contact a staff member IMMEDIATELY.").setEphemeral(true).queue();
                return;
            }

            discord.addRoleToMember(event.getUser(), awaiting).queue();

            TextInput where = createTextInput("where", "How did you find Florial?", "Be sure to be specific.", TextInputStyle.SHORT, 10, 100);
            TextInput why = createTextInput("why", "Why did you want to join us?", "Be sure to be specific.", TextInputStyle.PARAGRAPH, 45, 1000);
            TextInput did = createTextInput("did", "Did you read our rules? If so, explain one.", "Be sure to explain a rule.", TextInputStyle.PARAGRAPH, 30, 100);

            Modal modal = Modal.create("application", "Verification Application")
                    .addComponents(ActionRow.of(why),ActionRow.of(where),ActionRow.of(did))
                    .build();


            event.replyModal(modal).queue();

        }
    }

    private static TextInput createTextInput(String id, String label, String placeholder, TextInputStyle style, int minLength, int maxLength) {
        return TextInput.create(id, label, style)
                .setPlaceholder(placeholder)
                .setMinLength(minLength)
                .setMaxLength(maxLength)
                .build();
    }
}

package commands;

import com.jagrosh.jdautilities.command.SlashCommand;
import com.jagrosh.jdautilities.command.SlashCommandEvent;
import com.jagrosh.jdautilities.doc.standard.CommandInfo;
import database.BotDatabase;
import database.DiscordProfile;

import java.sql.SQLException;
@CommandInfo(name = "newval")
public class NewDatabaseValueCommand extends SlashCommand {

    private static final BotDatabase botDatabase = new BotDatabase();


    public NewDatabaseValueCommand() {
        this.name = "newval";

    }

    @Override
    protected void execute(SlashCommandEvent slashCommandEvent) {

        botDatabase.createNewDatabaseValue();


    }
}

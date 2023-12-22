package net.satooro.stragnarok.discord.events;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;

public class RegisterCommands extends ListenerAdapter {

    public void onGuildReady(GuildReadyEvent event){
        List<CommandData> commandData = new ArrayList<>();

        OptionData option1 = new OptionData(OptionType.STRING, "codigo", "Insira o código para vincular sua conta", true);
        commandData.add(Commands.slash("vincular", "Use o código para vincular a sua conta")
                .addOptions(option1));
        commandData.add(Commands.slash("playerlist", "Mostra os jogadores online"));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}

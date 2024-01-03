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

        commandData.add(Commands.slash("vincular", "Use o código para vincular a sua conta")
                .addOptions(new OptionData(OptionType.STRING, "codigo", "Insira o código para vincular sua conta", true)));
        commandData.add(Commands.slash("playerlist", "Mostra os jogadores online"));

        commandData.add(Commands.slash("darvip", "Comando para entrega de vips")
                .addOptions(new OptionData(OptionType.USER, "usuario", "Usuário que irá receber", true))
                .addOptions(new OptionData(OptionType.STRING, "vip", "Tipo de vip", true)
                        .addChoice("Loki", "loki")
                        .addChoice("Thor", "thor")
                        .addChoice("Odin", "odin")
                        .addChoice("Valhalla", "valhallha")
                        .addChoice("Ragnarok", "ragnarok"))
                .addOptions(new OptionData(OptionType.INTEGER, "dias", "Dias de vip", true)));


        event.getGuild().updateCommands().addCommands(commandData).queue();
    }
}

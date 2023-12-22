package net.satooro.stragnarok.discord.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.satooro.stragnarok.database.Queries;
import net.satooro.stragnarok.discord.BotManager;
import net.satooro.stragnarok.discord.Embeds;
import net.satooro.stragnarok.utils.Utils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Objects;

public class DCDarVip extends ListenerAdapter {



    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        String author = event.getUser().getAsMention();
        String authorid = event.getUser().getId();
        User cmdauthor = BotManager.getJda().retrieveUserById(authorid).complete();

        if(command.equals("darvip")){
            OptionMapping mapping1 = event.getOption("usuario");
            OptionMapping mapping2 = event.getOption("vip");
            String tipovip = mapping2.getAsString();
            OptionMapping tempo = event.getOption("dias");

            User userslash = event.getOption("usuario").getAsUser();
            String useravatar = userslash.getAvatarUrl();

            String slashid = userslash.getId();
            String query = Queries.getNickAndStatus(slashid);

            String[] splitInfo = splitString(query);

            String player_nick = splitInfo[0];
            String status = splitInfo[1];

            System.out.println(player_nick + status);

            if(Objects.equals(status, "1")){
                event.deferReply(false).setEmbeds(Embeds.entregaVipEmbed(cmdauthor, player_nick, tipovip, userslash, useravatar, tempo.getAsString()).build()).queue();
            }
//            String player_nick, status = Queries.getNickAndStatus(userid);
//            String status = Queries.get
        }
    }

    private static String[] splitString(String combinedString){
        return combinedString.split("\\|");
    }

}

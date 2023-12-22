package net.satooro.stragnarok.discord.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.satooro.stragnarok.discord.Embeds;
import net.satooro.stragnarok.discord.GuildManager;
import net.satooro.stragnarok.minecraft.commands.ComandoComerciante;
import net.satooro.stragnarok.minecraft.events.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DCPlayerList extends ListenerAdapter {


    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        String command = event.getName();
        String user = event.getUser().getAsMention();
        String userid = event.getUser().getId();

        String guildicon = GuildManager.getGuild().getIconUrl();

        if(command.equals("playerlist")){
            List<String> list = new ArrayList<>();
            for(Player p : Bukkit.getOnlinePlayers()){
                list.add(p.getName());
            }
            if(list.size() > 0){
                int quantiamembros = Bukkit.getOnlinePlayers().size();
                event.deferReply(false).setEmbeds(Embeds.onlinePlayersEmbed(getOnlinePlayersList(), quantiamembros, guildicon, ComandoComerciante.comerciante).build()).queue();
//                event.reply("Jogadores: " + getOnlinePlayersList()).queue();
            } else {
                event.reply("Não há nenhum jogador online").queue();
            }

            //event.reply("Jogadores online:  " + Arrays.toString(list.toArray())).setEphemeral(true).queue();
            //event.reply("Os lindos: " + Arrays.asList(Bukkit.getOnlinePlayers().toString())).queue();

        }
    }

    public static String getOnlinePlayersList() {
        StringBuilder playerList = new StringBuilder();
        for (Player p : Bukkit.getOnlinePlayers()){
            playerList.append(p.getName()).append("\n");
        }
        return playerList.toString();
    }


}

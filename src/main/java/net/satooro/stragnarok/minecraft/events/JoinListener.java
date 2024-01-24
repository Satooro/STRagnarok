package net.satooro.stragnarok.minecraft.events;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.satooro.stragnarok.database.Queries;
import net.satooro.stragnarok.discord.BotManager;
import net.satooro.stragnarok.discord.Embeds;
import net.satooro.stragnarok.discord.GuildManager;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Utils;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinListener extends ListenerAdapter implements Listener {

    public static BidiMap<String, String> verificadosOnline = new DualHashBidiMap<>();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        String pUUID = e.getPlayer().getUniqueId().toString();
        String pName = e.getPlayer().getName();

        if(verificadosOnline.containsValue(pUUID)) {

            String userid = verificadosOnline.getKey(pUUID);
            User user = BotManager.getJda().retrieveUserById(userid).complete();
            String avatarurl = user.getAvatarUrl();
            String dcusername = user.getName();

            if(Config.getBoolean("minecraft.discord_embeds.join.enabled")){
                TextChannel canal = GuildManager.getGuild().getTextChannelById(Config.getString("minecraft.discord_embeds.entradas_e_saidas_canal"));
                canal.sendMessageEmbeds(Embeds.joinAndLeaveMessage(dcusername, pName, avatarurl, true).build()).queue();
            }
        } else if(Queries.playerIsVerified(pUUID)){
            String userid = Queries.getUserIdFromUUID(pUUID);
            verificadosOnline.put(userid, pUUID);
            User user = BotManager.getJda().retrieveUserById(userid).complete();
            String avatarurl = user.getAvatarUrl();
            String dcusername = user.getName();

            if(Config.getBoolean("minecraft.discord_embeds.join.enabled")){
                TextChannel canal = GuildManager.getGuild().getTextChannelById(Config.getString("minecraft.discord_embeds.entradas_e_saidas_canal"));
                canal.sendMessageEmbeds(Embeds.joinAndLeaveMessage(dcusername, pName, avatarurl, true).build()).queue();
            }
        } else {
            TextChannel canal = GuildManager.getGuild().getTextChannelById(Config.getString("minecraft.discord_embeds.entradas_e_saidas_canal"));
            canal.sendMessageEmbeds(Embeds.joinAndLeaveNotVinculated(pName, true).build()).queue();
        }
    }

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent e){
        String pUUID = e.getPlayer().getUniqueId().toString();
        String pName = e.getPlayer().getName();

        if(verificadosOnline.containsValue(pUUID)) {
            String userid = verificadosOnline.getKey(pUUID);
            User user = BotManager.getJda().retrieveUserById(userid).complete();
            String avatarurl = user.getAvatarUrl();
            String dcusername = user.getName();

            if(Config.getBoolean("minecraft.discord_embeds.leave.enabled")){
                TextChannel canal = GuildManager.getGuild().getTextChannelById(Config.getString("minecraft.discord_embeds.entradas_e_saidas_canal"));
                canal.sendMessageEmbeds(Embeds.joinAndLeaveMessage(dcusername, pName, avatarurl, false).build()).queue();
            }
        } else {
            TextChannel canal = GuildManager.getGuild().getTextChannelById(Config.getString("minecraft.discord_embeds.entradas_e_saidas_canal"));
            canal.sendMessageEmbeds(Embeds.joinAndLeaveNotVinculated(pName, false).build()).queue();
        }
    }
}

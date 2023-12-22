package net.satooro.stragnarok.minecraft.events;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.satooro.stragnarok.discord.Embeds;
import net.satooro.stragnarok.discord.GuildManager;
import net.satooro.stragnarok.utils.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class StartAndShutdownListener extends ListenerAdapter implements Listener {


    public static void StartAndShutDownListener(boolean iniciando){
        String guildicon = GuildManager.getGuild().getIconUrl();
        System.out.println(guildicon);

        TextChannel entradas_e_saidas = GuildManager.getGuild().getTextChannelById(Config.getString("minecraft.discord_embeds.entradas_e_saidas_canal"));
        if(Config.getBoolean("minecraft.discord_embeds.server_status.startup_and_shutdown_broadcast")){
            entradas_e_saidas.sendMessageEmbeds(Embeds.serverRunAndOff(iniciando, guildicon).build()).queue();
        }

    }

}

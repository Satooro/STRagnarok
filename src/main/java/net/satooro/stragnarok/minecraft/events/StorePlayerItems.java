package net.satooro.stragnarok.minecraft.events;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.satooro.stragnarok.discord.BotManager;
import net.satooro.stragnarok.utils.Serializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class StorePlayerItems implements Listener {


    @EventHandler
    public void storePlayerItems(PlayerDeathEvent e){
        Player player = (                       Player) e.getEntity();
        Player killer = (Player) player.getKiller();
        if(killer == null){
            String pinv = Serializer.itemstackArrayToBase64(player.getInventory());

            TextChannel channel = BotManager.getJda().getTextChannelById("1183671742699413524");
            channel.sendMessage(pinv).queue();

            Bukkit.getConsoleSender().sendMessage(pinv);
        }
    }

}

package net.satooro.stragnarok.minecraft.commands;

import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ComandoLuz implements CommandExecutor, Listener {

    static ArrayList<Player> luz = new ArrayList<Player>();


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player p){
            if(strings.length == 0){
                if(luz.contains(p)){
                    luz.remove(p);
                    Utils.sendPlayerMessage(Config.get().getStringList("minecraft.luz_msgoff").toString(), p);
                    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
                } else {
                    luz.add(p);
                    Utils.sendPlayerMessage(Config.get().getStringList("minecraft.luz_msgon").toString(), p);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 999999999, 1, true, false));
                }
            }
        }
        return false;
    }

    public static void onPlayerLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        if(luz.contains(player)) luz.remove(player);
    }

}

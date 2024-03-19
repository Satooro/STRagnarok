package net.satooro.stragnarok.minecraft.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ComandoRestorePInv implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player player){
            if(player.isOp() || player.hasPermission("stragnarok.cmd.rpi")){
                if(!(strings.length == 0)){
                    Player target = Bukkit.getPlayer(strings[0]);
                    if(Bukkit.getOnlinePlayers().contains(target) && !strings[1].isEmpty()) {

                    }
                }
            }
        }
        return false;
    }
}

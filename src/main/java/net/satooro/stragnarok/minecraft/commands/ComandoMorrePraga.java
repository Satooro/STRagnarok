package net.satooro.stragnarok.minecraft.commands;

import net.satooro.stragnarok.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ComandoMorrePraga implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player p){
            if(p.isOp()){
                morrePraga();
                Utils.sendPlayerMessage("&6A praga foi exterminada!", p);
            }
        } else {
            morrePraga();
            Bukkit.getConsoleSender().sendMessage("§6A praga foi exterminada!");
        }

        return false;
    }


    public static void morrePraga(){
        Player praga = Bukkit.getPlayer("PhoTM");
        assert praga != null;
        if(praga.isOnline()){
            Bukkit.broadcastMessage("§6§lBonk!");
            praga.setHealth(0);
        } else {
            Bukkit.getConsoleSender().sendMessage("§cA praga não está online");
        }
    }
}

package net.satooro.stragnarok.minecraft.commands;

import jdk.jshell.execution.Util;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ComandoComerciante implements CommandExecutor {
    public static String comerciante = "Iniciante";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            if (!(strings.length == 0)) {
                if (strings[0].equalsIgnoreCase("trocar")) {
                    if (strings[1].isEmpty()) {
                        Utils.sendPlayerMessage("&6O comerciante atual é: &f" + comerciante, p);
                    } else if (strings[1].equalsIgnoreCase("Vanilla")) {
                        Utils.sendPlayerMessage("&6Comerciante dos iniciantes definido", p);
                        Utils.sendTitleMessage(comerciante);
                        comerciante = "Vanilla";
                    } else if (strings[1].equalsIgnoreCase("Jacquin")) {
                        Utils.sendPlayerMessage("&6Comerciante dos barquinhos definido", p);
                        Utils.sendTitleMessage(comerciante);
                        comerciante = "Jacquin";
                    } else if(strings[1].equalsIgnoreCase("Industrial")){
                        Utils.sendPlayerMessage("&6Comerciante Industrial definido", p);
                        Utils.sendTitleMessage(comerciante);
                        comerciante = "Industrial";
                    } else if(strings[1].equalsIgnoreCase("Pipa")){
                        Utils.sendPlayerMessage("&6Comerciante Pipa definido", p);
                        Utils.sendTitleMessage(comerciante);
                        comerciante = "Pipa";
                    } else if(strings[1].equalsIgnoreCase("Mágico")){
                        Utils.sendPlayerMessage("&6Comerciante Mágico definido", p);
                        Utils.sendTitleMessage(comerciante);
                        comerciante = "Mágico";
                    } else if(strings[1].equalsIgnoreCase("Fazendeiro")){
                        Utils.sendPlayerMessage("&6Comerciante Fazendeiro definido", p);
                        Utils.sendTitleMessage(comerciante);
                        comerciante = "Fazendeiro";
                    } else if(strings[1].equalsIgnoreCase("Traficante")){
                        Utils.sendPlayerMessage("&6Comerciante Traficante definido", p);
                        Utils.sendTitleMessage(comerciante);
                        comerciante = "Traficante";
                    } else if(strings[1].equalsIgnoreCase("EndGame")){
                        Utils.sendPlayerMessage("&6Comerciante EndGame definido", p);
                        Utils.sendTitleMessage(comerciante);
                        comerciante = "EndGame";
                    } else if(strings[1].equalsIgnoreCase("Bruxa")){
                        Utils.sendPlayerMessage("&6Comerciante Bruxa definido", p);
                        Utils.sendTitleMessage(comerciante);
                        comerciante = "Bruxa";
                    } else {
                        Utils.sendPlayerMessage("&6O comerciante atual é: " + comerciante, p);
                    }
                }
            } else {
                Utils.sendPlayerMessage("&c/admcomerciante trocar <iniciante|barquinho>", p);
            }

        } else if (strings.length == 0) {
            Utils.sendMessageConsole("Olá");
        }
        return false;
    }
}

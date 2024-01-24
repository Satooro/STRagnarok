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
                    } else if (strings[1].equalsIgnoreCase("Iniciante")) {
                        Utils.sendPlayerMessage("&6Comerciante dos iniciantes definido", p);
                        comerciante = "Iniciante";
                    } else if (strings[1].equalsIgnoreCase("Barquinho")) {
                        Utils.sendPlayerMessage("&6Comerciante dos barquinhos definido", p);
                        comerciante = "Barquinho";
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

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
    public static String comerciante = "iniciante";

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {
            if (!(strings.length == 0)) {
                if (strings[0].equalsIgnoreCase("setarcomerciante")) {
                    if(strings[1].length() == 0) {
                        Utils.sendPlayerMessage("&6O comerciante atual é: &f" + comerciante, p);
                    } else switch (strings[1].toLowerCase()) {
                        case "iniciante" -> {
                            Utils.sendPlayerMessage("&6Comerciante dos iniciantes definido", p);
                            comerciante = "iniciante";
                        }
                        case "barquinho" -> {
                            Utils.sendPlayerMessage("&6Comerciante dos barquinhos definido", p);
                            comerciante = "barquinho";
                        }
                        default -> {
                            Utils.sendPlayerMessage("&6O comerciante atual é: " + comerciante, p);
                        }
                    }
                }
            } else {
                Utils.sendPlayerMessage("&c/admcomerciante setarcomerciante <iniciante|barquinho>", p);
            }

        } else if (strings.length == 0) {
            Utils.sendMessageConsole("Olá");
        }
        return false;
    }
}

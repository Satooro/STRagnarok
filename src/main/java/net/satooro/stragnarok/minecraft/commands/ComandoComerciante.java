package net.satooro.stragnarok.minecraft.commands;

import jdk.jshell.execution.Util;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Utils;
import org.bukkit.Bukkit;
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
                        comerciante = "Vanilla";
                        Utils.sendTitleMessage(comerciante);
                    } else if (strings[1].equalsIgnoreCase("Jacquin")) {
                        Utils.sendPlayerMessage("&6Comerciante dos barquinhos definido", p);
                        comerciante = "Jacquin";
                        Utils.sendTitleMessage(comerciante);
                    } else if(strings[1].equalsIgnoreCase("Industrial")){
                        Utils.sendPlayerMessage("&6Comerciante Industrial definido", p);
                        comerciante = "Industrial";
                        Utils.sendTitleMessage(comerciante);
                    } else if(strings[1].equalsIgnoreCase("Pipa")){
                        Utils.sendPlayerMessage("&6Comerciante Pipa definido", p);
                        comerciante = "Pipa";
                        Utils.sendTitleMessage(comerciante);
                    } else if(strings[1].equalsIgnoreCase("Mágico")){
                        Utils.sendPlayerMessage("&6Comerciante Mágico definido", p);
                        comerciante = "Mágico";
                        Utils.sendTitleMessage(comerciante);
                    } else if(strings[1].equalsIgnoreCase("Fazendeiro")){
                        Utils.sendPlayerMessage("&6Comerciante Fazendeiro definido", p);
                        comerciante = "Fazendeiro";
                        Utils.sendTitleMessage(comerciante);
                    } else if(strings[1].equalsIgnoreCase("Traficante")){
                        Utils.sendPlayerMessage("&6Comerciante Traficante definido", p);
                        comerciante = "Traficante";
                        Utils.sendTitleMessage(comerciante);
                    } else if(strings[1].equalsIgnoreCase("EndGame")){
                        Utils.sendPlayerMessage("&6Comerciante EndGame definido", p);
                        comerciante = "EndGame";
                        Utils.sendTitleMessage(comerciante);
                    } else if(strings[1].equalsIgnoreCase("Bruxa")){
                        Utils.sendPlayerMessage("&6Comerciante Bruxa definido", p);
                        comerciante = "Bruxa";
                        Utils.sendTitleMessage(comerciante);
                    } else {
                        Utils.sendPlayerMessage("&6O comerciante atual é: " + comerciante, p);
                    }
                }
            } else {
                Utils.sendPlayerMessage("&c/admcomerciante trocar <vanilla|jacquin|industrialpipa|mágico|fazendeiro|traficante|endgame|bruxa>", p);
            }

        } else if (!(strings.length == 0)) {
            if (strings[0].equalsIgnoreCase("trocar")) {
                if (strings[1].isEmpty()) {
                    Bukkit.getConsoleSender().sendMessage("§c/admcomerciante trocar <vanilla/jacquin/industrial/pipa/mágico/fazendeiro/traficante/endgame/bruxa>");
                } else if (strings[1].equalsIgnoreCase("vanilla")) {
                        setComercianteConsole(strings[1]);
                    } else if (strings[1].equalsIgnoreCase("jacquin")) {
                        setComercianteConsole(strings[1]);
                    } else if (strings[1].equalsIgnoreCase("industrial")) {
                        setComercianteConsole(strings[1]);
                    } else if (strings[1].equalsIgnoreCase("pipa")) {
                        setComercianteConsole(strings[1]);
                    } else if (strings[1].equalsIgnoreCase("mágico")) {
                        setComercianteConsole(strings[1]);
                    } else if (strings[1].equalsIgnoreCase("fazendeiro")) {
                        setComercianteConsole(strings[1]);
                    } else if (strings[1].equalsIgnoreCase("traficante")) {
                        setComercianteConsole(strings[1]);
                    } else if (strings[1].equalsIgnoreCase("endgame")) {
                        setComercianteConsole(strings[1]);
                    } else if (strings[1].equalsIgnoreCase("bruxa")) {
                        setComercianteConsole(strings[1]);
                    } else {
                        Bukkit.getConsoleSender().sendMessage("§6O comerciante atual é: " + comerciante);
                    }
                }
            }
        return false;
    }

    public static void setComercianteConsole(String input){
        comerciante = input;
        Bukkit.getConsoleSender().sendMessage("Comerciante definido para: " + comerciante);
        Utils.sendTitleMessage(comerciante);
    }

}

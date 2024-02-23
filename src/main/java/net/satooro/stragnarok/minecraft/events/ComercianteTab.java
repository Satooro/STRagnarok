package net.satooro.stragnarok.minecraft.events;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComercianteTab implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        List<String> resultados = new ArrayList<>();

        if(strings[0].length() == 1){
            return StringUtil.copyPartialMatches(strings[0], Arrays.asList("trocar"), new ArrayList<>());
        } else if(strings[1].length() == 2){
            return StringUtil.copyPartialMatches(strings[1], Arrays.asList("vanilla", "jacquin", "industrial", "pipa", "mágico", "fazendeiro", "traficante", "endgame", "bruxa"), new ArrayList<>());
        }

        return resultados;
    }
}

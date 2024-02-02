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

public class VincularTab implements TabCompleter {

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        List<String> resultados = new ArrayList<>();

        if(strings.length == 1){
            return StringUtil.copyPartialMatches(strings[0], Arrays.asList("refrescar", "reload"), new ArrayList<>());
        }

        return resultados;
    }
}

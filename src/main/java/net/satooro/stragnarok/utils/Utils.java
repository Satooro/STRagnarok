package net.satooro.sourcevincular.utils;

import net.satooro.sourcevincular.database.Queries;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class Utils {

    public static void sendMessageConsole(String message){
        Bukkit.getConsoleSender().sendMessage(message.replace("&", "§"));
    }

    public static String randomCode(int length) {
        String alfabetoUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alfabetoLowercase = "abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789";
        String simbolos = "-_!?@";
        String alfabetonumerico = alfabetoUppercase + alfabetoUppercase + alfabetoLowercase + numeros + simbolos;
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(alfabetonumerico.length());
            char randomChar = alfabetonumerico.charAt(index);
            sb.append(randomChar);
        }
        return sb.toString();
    }

    public static Boolean playerHasCooldown(String player, Map<String, Long> cooldown) {
        if(cooldown.containsKey(player)){
            return cooldown.get(player) > System.currentTimeMillis();
        }
        return false;
    }

    public static Long getCooldownTimeLeft(String key, Map<String, Long> cooldown){
        return (cooldown.get(key) - System.currentTimeMillis()) / 1000;
    }

    public static String getDataTime(){

        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.atZone(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return dateTime.format(dateTimeFormatter);
    }

    public static String getDataTime(String pattern){

        LocalDateTime dateTime = LocalDateTime.now();
        dateTime.atZone(ZoneId.of("America/Sao_Paulo"));
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);

        return dateTime.format(dateTimeFormatter);
    }

    public static void getRewards(Player p){
        if(!Queries.hasRewards(p.getUniqueId().toString())) {
            if(Config.get().getStringList("rewards.commands").size() > 0)
                Config.get().getStringList("rewards.commands").forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                        command.replace("%player%", (CharSequence) p).replace("&", "§")));
            try {
                Arrays.stream(Serializer.itemStackArrayFromBase64(Config.getString("rewards.items"))).toList().forEach(p.getInventory()::addItem);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}

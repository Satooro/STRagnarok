package net.satooro.stragnarok.minecraft.commands;

import net.satooro.stragnarok.database.Queries;
import net.satooro.stragnarok.discord.events.DCLog;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Logs;
import net.satooro.stragnarok.utils.Utils;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.map.HashedMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Map;

public class ComandoVincular implements CommandExecutor {

    // Cooldown
    Map<String, Long> cooldown = new HashedMap<>();
    // UUIDS dos jogadores para não precisar requisitar do SQL todo momento
    // UUID - KEY
    public static BidiMap<String, String> verifyCode = new DualHashBidiMap<>();
    public static ArrayList<String> jaVerificado = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if(commandSender instanceof Player p){

            String player_uuid = p.getUniqueId().toString();
            String pName = p.getName();

            if(strings.length == 1){
                if(strings[0].equalsIgnoreCase("conta")) {

                    if(jaVerificado.contains(player_uuid)){
                      Utils.sendPlayerMessage("Você já está vinculado", p);
                    } else if (verifyCode.containsValue(player_uuid)){
                        String code = verifyCode.getKey(player_uuid);
                        Utils.sendPlayerMessage("HASHMAP: Seu é o seguinte: " + code, p);
                        return true;

                    } else if(Queries.hasPlayer(player_uuid)){
                        String code = Queries.getCodeFromUUID(player_uuid);
                        if(!(code == null)) {
                            verifyCode.put(code, player_uuid);
//                        Config.sendMessageStringList("messages.vincularconta", p);
                            Utils.sendPlayerMessage("SQL: Seu código é: " + code, p);
                        } else {
                            jaVerificado.add(player_uuid);
                            Utils.sendPlayerMessage("Você já está vinculado", p);
                        }
                        return true;

                    } else {
                        String code = Utils.randomCode(10);
                        verifyCode.put(code, player_uuid);
                        Utils.sendPlayerMessage("NEW: Seu código é: " + code, p);
                        Queries.createVerificationUUID(player_uuid, code, pName);
                        Logs.createLogVerifyMinecraft(pName, code);
                        DCLog.logDiscordNewCode(pName, code);
                    }
                }

                // Reload Config
                if(strings[0].equalsIgnoreCase("reload")){
                    if(p.hasPermission("stragnarok.reload") || p.isOp()){
                        Config.reload();
                        Utils.sendPlayerMessage("Plugin recarregado", p);
                    }
                }
                if(strings[0].equalsIgnoreCase("serializer")){

                }
            } else {
                Config.sendMessageStringList("messages.commands", p);
            }

        }
        return false;
    }
}

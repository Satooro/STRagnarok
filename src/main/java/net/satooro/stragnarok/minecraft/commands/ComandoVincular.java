package net.satooro.stragnarok.minecraft.commands;

import net.luckperms.api.cacheddata.CachedData;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import net.luckperms.api.node.NodeType;
import net.satooro.stragnarok.STRagnarok;
import net.satooro.stragnarok.database.Queries;
import net.satooro.stragnarok.utils.*;
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
import java.util.UUID;

public class ComandoVincular implements CommandExecutor {

    private STRagnarok main;

    public ComandoVincular(STRagnarok main){
        this.main = main;
    }


    // Cooldown
    Map<String, Long> cooldown = new HashedMap<>();
    // UUIDS dos jogadores para não precisar requisitar do SQL todo momento
    // UUID - KEY
    public static BidiMap<String, String> verifyCode = new DualHashBidiMap<>();
    public static ArrayList<String> jaVerificado = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player p) {

            String player_uuid = p.getUniqueId().toString();
            String pName = p.getName();

            if (strings.length >= 1) {

                if(strings[0].equalsIgnoreCase("grupo")){
                    String grupo = getPlayerGroups(UUID.fromString(player_uuid));
                    p.sendMessage(grupo);
                }

                if (strings[0].equalsIgnoreCase("reload")) {
                    if (p.hasPermission("stragnarok.reload") || p.isOp()) {
                        Config.reload();
                        Utils.sendPlayerMessage("Plugin recarregado", p);
                    }
                }
                if(strings[0].equalsIgnoreCase("testar")){
                    if(p.isOp()) {
                        Utils.sendPlayerMessage("Recompensas testadas", p);
                        Utils.getRewards(p);
                    }
                }
                if(strings[0].equalsIgnoreCase("refrescar")){
                    if(Utils.playerHasCooldown(player_uuid, cooldown)){
                        p.sendMessage(Messages.COOLDOWN);
                        return true;
                    } else {

                        cooldown.put(player_uuid, System.currentTimeMillis() + (300 * TimeUnit.SECOND));
                    }
                }
                if(Utils.playerHasCooldown(String.valueOf(p.getUniqueId()), cooldown)){
                    p.sendMessage(Messages.COOLDOWN);
                    return true;
                }
            } else if (jaVerificado.contains(player_uuid)) {
//                Utils.sendPlayerMessage("Você já está vinculado", p);
                Config.sendMessageStringList("messages.commands", p);
            } else if (verifyCode.containsValue(player_uuid)) {
                String code = verifyCode.getKey(player_uuid);
                Utils.sendPlayerMessage(Config.getString("messages.vincular").replace("%codigo%", code), p);
                return true;

            } else if (Queries.hasPlayer(player_uuid)) {
                if(Utils.playerHasCooldown(player_uuid, cooldown)){
                    p.sendMessage(Messages.COOLDOWN);
                    return true;
                }
                String code = Queries.getCodeFromUUID(player_uuid);
                if (!(code == null)) {
                    verifyCode.put(code, player_uuid);
                    Utils.sendPlayerMessage(Config.getString("messages.vincular").replace("%codigo%", code), p);
                    cooldown.put(player_uuid, System.currentTimeMillis() + (10 * TimeUnit.SECOND));
                } else {
                    Config.sendMessageStringList("messages.commands", p);
                    jaVerificado.add(player_uuid);
//                    Utils.sendPlayerMessage(Config.getString("messages.vincular").replace("%codigo%", code), p);
                }

            } else {
                String code = Utils.randomCode(10);
                verifyCode.put(code, player_uuid);
                Utils.sendPlayerMessage(Config.getString("messages.vincular").replace("%codigo%", code), p);
                Queries.createVerificationUUID(player_uuid, code, pName);
                Logs.createLogVerifyMinecraft(pName, code);
                cooldown.put(player_uuid, System.currentTimeMillis() + (10 * TimeUnit.SECOND));
//                        DCLog.logDiscordNewCode(pName, code);

            }

        }
        // Reload Config

        return false;
    }

    public static String getPlayerGroups(UUID uuid){
        User user = STRagnarok.getLuckPerms().getUserManager().loadUser(uuid).join();
        if(user != null){

            CachedData cachedData = (CachedData) user.getCachedData();

            int maiorPeso = Integer.MIN_VALUE;
            String maiorGrupo = "";

            for(Node node : user.getNodes()){
                if(node.getType() == NodeType.INHERITANCE){
                    String groupName = node.getKey();
                    int weight = STRagnarok.getLuckPerms().getGroupManager().getGroup(groupName).getWeight().orElse(0);
                    if(weight > maiorPeso){
                        maiorPeso = weight;
                        maiorGrupo = groupName;

                    }
                }
            }
            return maiorGrupo;
        }
        return "";
    }

}

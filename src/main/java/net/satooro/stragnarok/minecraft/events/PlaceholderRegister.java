package net.satooro.stragnarok.minecraft.events;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.satooro.stragnarok.minecraft.commands.ComandoComerciante;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Utils;
import org.bukkit.OfflinePlayer;

public class PlaceholderRegister extends PlaceholderExpansion {

    public String getAuthor(){
        return "Satooro";
    }
    public String getIdentifier(){
        return "stragnarok";
    }
    public String getVersion(){
        return "1.0";
    }

    public String onRequest(OfflinePlayer player, String params){
        if(params.equals("name")){
            return player == null ? null : player.getName();
        }
        if(params.equalsIgnoreCase("placeholder1")){
            return "Placeholder 1";
        }
        if(params.equalsIgnoreCase("placeholder2")){
            return "Placeholder 2";
        }
        if(params.equals("comerciante_atual")){
            return ComandoComerciante.comerciante;
        }
        if(params.equals("tag_vote")){
            return Config.getString("minecraft.tag_vote");
        }
        if(params.equals("dia")){
            return Utils.getDay();
        }
        return null;
    }

}

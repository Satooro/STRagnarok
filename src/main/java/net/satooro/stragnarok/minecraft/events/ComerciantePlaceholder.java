package net.satooro.stragnarok.minecraft.events;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

public class ComerciantePlaceholder extends PlaceholderExpansion {

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
        return null;
    }

}

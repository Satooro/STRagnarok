package net.satooro.stragnarok.discord;

import net.dv8tion.jda.api.entities.Guild;
import net.satooro.stragnarok.utils.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class GuildManager {

    public static Guild getGuild() {
        return BotManager.getJda().getGuildById(Config.getString("bot.guild_id"));
    }
}

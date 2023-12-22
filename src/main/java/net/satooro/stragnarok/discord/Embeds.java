package net.satooro.stragnarok.discord;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.satooro.stragnarok.STRagnarok;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Utils;

import java.awt.*;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Embeds {

    public static EmbedBuilder createVerififyCorret(String user, String code, String nickname, String thumbnail){
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("**Ragnarok Vincular**");
        embed.setDescription(user + " Contas vinculadas com sucesso!");
        embed.addField("**Conta vinculada:**", "```" + nickname + "```", true);
        embed.addField("**Código utilizado:**", "```"+ code + "```", true);
        embed.setThumbnail(thumbnail);
        embed.setColor(new Color(0, 255, 0));
        return embed;
    }

    public static EmbedBuilder joinAndLeaveMessage(String dcname, String nick, String avatarurl, Boolean join){
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle(join ? Config.getString("minecraft.discord_embeds.join.title").replace("%user%", dcname) : Config.getString("minecraft.discord_embeds.leave.title").replace("%user%", dcname));
        embed.setDescription(join ? Config.getString("minecraft.discord_embeds.join.description").replace("%nick%", nick) : Config.getString("minecraft.discord_embeds.leave.description").replace("%nick%", nick));
        embed.setColor(join ? new Color(3,255,19) : new Color(255, 0 , 0));
        embed.setThumbnail(avatarurl);
        embed.setTimestamp(OffsetDateTime.now());

        return embed;
    }

    public static EmbedBuilder onlinePlayersEmbed(String playerlist, int quantiamembros, String guildicon){
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle("**Informações do servidor**");
        embed.setThumbnail(guildicon);

        embed.addField("Online agora:", "```" + quantiamembros + "```", true);
        embed.addField("Lista de players:", "```" + playerlist + "```", false);

        return embed;
    }

    public static EmbedBuilder serverRunAndOff(Boolean status, String guildicon){
        EmbedBuilder embed = new EmbedBuilder();

        embed.setTitle(status ? Config.getString("minecraft.discord_embeds.server_status.startup_title") : Config.getString("minecraft.discord_embeds.server_status.shutdown_title"));
        embed.setDescription(status ? String.join("\n", Config.get().getStringList("minecraft.discord_embeds.server_status.startup_description")) : String.join("\n", Config.get().getStringList("minecraft.discord_embeds.server_status.shutdown_description")));
        embed.setColor(status ? new Color(3,255,19) : new Color(255, 0 , 0));
        embed.setThumbnail(guildicon);

        return embed;
    }

}

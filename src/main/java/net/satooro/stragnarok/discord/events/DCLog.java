package net.satooro.stragnarok.discord.events;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.satooro.stragnarok.discord.GuildManager;
import net.satooro.stragnarok.utils.Utils;

public class DCLog {

    private final static TextChannel textChannel = GuildManager.getGuild().getTextChannelById("1186734582591144057");

    public static void logDiscordNewCode(String nickname, String codigo){
        textChannel.sendMessage(String.format("[%s] O jogador %s gerou o código para vincular a conta: %s", Utils.getDataTime(), nickname, codigo));
    }


}

package net.satooro.stragnarok.discord;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.satooro.stragnarok.discord.commands.DCPlayerList;
import net.satooro.stragnarok.discord.commands.DCVincular;
import net.satooro.stragnarok.discord.events.RegisterCommands;
import net.satooro.stragnarok.minecraft.events.JoinListener;
import net.satooro.stragnarok.minecraft.events.StartAndShutdownListener;
import net.satooro.stragnarok.utils.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class BotManager {

    private static JDA jda;

    public BotManager() {
        JDABuilder builder = JDABuilder.createLight(Config.getString("bot.token"),
        GatewayIntent.GUILD_MESSAGES,
        GatewayIntent.MESSAGE_CONTENT,
        GatewayIntent.GUILD_MEMBERS).addEventListeners(
                new DCVincular(),
                new RegisterCommands(),
                new DCPlayerList(),
                new JoinListener(),
                new StartAndShutdownListener());

        String tipo = Config.getString("bot.status_type");
        switch (tipo){
            case "watching" -> builder.setActivity(Activity.watching(Config.getString("bot.msg_status")));
            case "listening" -> builder.setActivity(Activity.listening(Config.getString("bot.msg_status")));
            case "competing" -> builder.setActivity(Activity.competing(Config.getString("bot.msg_status")));
            case "playing" -> builder.setActivity(Activity.playing(Config.getString("bot.msg_status")));
            case "streaming" -> builder.setActivity(Activity.streaming(Config.getString("bot.msg_status"), Config.getString("bot.url_streaming")));
            default -> builder.setActivity(Activity.playing("em jogar.ragnarok-brasil.com.br"));
        }

        jda = builder.build();
    }

    public static JDA getJda() {return jda;}
}

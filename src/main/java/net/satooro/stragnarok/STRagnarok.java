package net.satooro.stragnarok;

import net.satooro.stragnarok.database.Database;
import net.satooro.stragnarok.database.Queries;
import net.satooro.stragnarok.discord.BotManager;
import net.satooro.stragnarok.discord.GuildManager;
import net.satooro.stragnarok.minecraft.commands.ComandoVincular;
import net.satooro.stragnarok.minecraft.events.JoinListener;
import net.satooro.stragnarok.minecraft.events.StartAndShutdownListener;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Logs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class STRagnarok extends JavaPlugin {

    private static STRagnarok main = null;
    public static Database database;
    @Override
    public void onEnable() {
        main = this;

        getServer().getPluginManager().registerEvents(new JoinListener(), this);

        Config.setup("config.yml");
        new Logs();
        saveDefaultConfig();
        saveConfig();
        Config.defaultConfigs();
        database = new Database();
//        Init();
        new Queries();
        new BotManager();
        getServer().getPluginManager().registerEvents(new StartAndShutdownListener(), this);
        getCommand("vincular").setExecutor(new ComandoVincular());

//        StartAndShutdownListener.StartAndShutDownListener(true);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
//        StartAndShutdownListener.StartAndShutDownListener(false);
    }
    /*
    public static void Init(){
        new Logs();
        new Queries();
        new BotManager();
        new GuildManager();
        StartAndShutdownListener.StartAndShutDownListener(true);
    }

     */

    public static STRagnarok getMain() {return main;}
}

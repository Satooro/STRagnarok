package net.satooro.stragnarok;

import net.satooro.stragnarok.database.Database;
import net.satooro.stragnarok.database.Queries;
import net.satooro.stragnarok.discord.BotManager;
import net.satooro.stragnarok.minecraft.commands.ComandoVincular;
import net.satooro.stragnarok.minecraft.events.ComerciantePlaceholder;
import net.satooro.stragnarok.minecraft.events.JoinListener;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Logs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.constant.Constable;

public final class STRagnarok extends JavaPlugin {

    private static STRagnarok main = null;
    public static Database database;

    public String comerciante = "Pirocudo";

    @Override
    public void onEnable() {

        Constable comerciante1 = comerciante;

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new ComerciantePlaceholder().register();
            getLogger().info("PlaceHolder expansion registered");
        } else {
            getLogger().info("PlaceholderAPI não foi encontrado. Placeholder expansion not registered.");
        }

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
        getCommand("vincular").setExecutor(new ComandoVincular());

//        StartAndShutdownListener.StartAndShutDownListener(true);
    }

    @Override
    public void onDisable() {
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

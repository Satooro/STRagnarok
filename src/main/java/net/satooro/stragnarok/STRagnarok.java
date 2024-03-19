package net.satooro.stragnarok;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.satooro.stragnarok.database.Database;
import net.satooro.stragnarok.database.Queries;
import net.satooro.stragnarok.discord.BotManager;
import net.satooro.stragnarok.minecraft.commands.*;
import net.satooro.stragnarok.minecraft.events.ComercianteTab;
import net.satooro.stragnarok.minecraft.events.PlaceholderRegister;
import net.satooro.stragnarok.minecraft.events.JoinListener;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Logs;
import net.satooro.stragnarok.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class STRagnarok extends JavaPlugin {

    private static STRagnarok main = null;
    private static LuckPerms luckPerms;
    public static Database database;

    @Override
    public void onEnable() {

        luckPerms = LuckPermsProvider.get();

        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            new PlaceholderRegister().register();
            getLogger().info("PlaceHolder expansion registered");
        } else {
            getLogger().info("PlaceholderAPI não foi encontrado. Placeholder expansion not registered.");
        }

        main = this;

        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new ComandoLuz(), this);

        Config.setup("config.yml");
        new Logs();
        saveDefaultConfig();
        saveConfig();
        Config.defaultConfigs();
        database = new Database();
//        Init();
        new Queries();
        new BotManager();
        getCommand("morrepraga").setExecutor(new ComandoMorrePraga());
        getCommand("luz").setExecutor(new ComandoLuz());
        getCommand("restoreplayerinventory").setExecutor(new ComandoRestorePInv());
        Bukkit.getPluginManager().registerEvents(new ComandoLuz(), this);
        getCommand("vincular").setExecutor(new ComandoVincular(this));
        getCommand("admcomerciante").setExecutor(new ComandoComerciante());
        getCommand("admcomerciante").setTabCompleter(new ComercianteTab());
        Utils.sendMessageConsole(ComandoComerciante.comerciante);

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

    public static LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public static STRagnarok getMain() {return main;}
}

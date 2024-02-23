package net.satooro.stragnarok.utils;

import net.satooro.stragnarok.STRagnarok;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class Config {

    private static File file;
    private static FileConfiguration configuration;

//    public static String FOLDER = STRagnarok.getMain().getDataFolder().getAbsolutePath().replace("\\", "/");

    @SuppressWarnings("all")
    public static void setup(String path){
        file = new File(STRagnarok.getMain().getDataFolder(), path);
        if(!file.exists()){
            try {
                Files.createDirectories(Paths.get(String.valueOf(STRagnarok.getMain().getDataFolder())));
                file.createNewFile();
            }catch (IOException e){
                Bukkit.getConsoleSender().sendMessage("§c§lConfig do DiscordBotJDA não criada.");
            }
        }
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return configuration;
    }

    public static void save() {
        try {
            configuration.save(file);
        }catch (IOException e){
            Bukkit.getConsoleSender().sendMessage("§c§lArquivo não salvo, ERRO");
        }
    }

    public static void defaultConfigs(){
        get().addDefault("auto-update", true);
        get().addDefault("server.name", "SERVIDOR");

        get().addDefault("bot.token", 0);
        get().addDefault("bot.guild_id", "ID");
        get().addDefault("bot.status_type", "streaming");
        get().addDefault("bot.msg_status", "jogar.ragnarok-brasil.com.br");
        get().addDefault("bot.url_streaming", "https://twitch.tv/satooro");
        get().addDefault("bot.verified_role", "ID");

        get().addDefault("mysql.enabled", false);
        get().addDefault("mysql.host", "");
        get().addDefault("mysql.port", 3306);
        get().addDefault("mysql.database", "");
        get().addDefault("mysql.username", "");
        get().addDefault("mysql.password", "");
        get().addDefault("mysql.ssl", "");

        get().addDefault("minecraft.luz_msgon", Arrays.asList(" ", "&8[&e&lLuz&8] &fVocê ativou a luz", " "));
        get().addDefault("minecraft.luz_msgoff", Arrays.asList(" ", "&8[&e&lLuz&8] &fVocê desativou a luz", " "));
        get().addDefault("minecraft.tag_vote", "&8[&e&lVoter&8]");
        get().addDefault("minecraft.comerciante.ativado", true);
        get().addDefault("minecraft.comerciante.feira_atual", "Iniciante");
        get().addDefault("minecraft.comerciante.title", "&e&lAtualização");
        get().addDefault("minecraft.comerciante.subtitle", "&eComerciante: &f%comerciante%");
        get().addDefault("minecraft.discord_embeds.server_status.startup_and_shutdown_broadcast", true);
        get().addDefault("minecraft.discord_embeds.server_status.startup_title", "Servidor online!");
        get().addDefault("minecraft.discord_embeds.server_status.startup_description", Arrays.asList(" ", "O servidor acabou de iniciar, podem jogar!", " "));
        get().addDefault("minecraft.discord_embeds.server_status.shutdown_title", "Servidor desligado");
        get().addDefault("minecraft.discord_embeds.server_status.shutdown_description", Arrays.asList(" ", "O servidor foi desligado ou reiniciado, em caso de problemas busque ajuda da nossa staff!", " "));
        get().addDefault("minecraft.discord_embeds.join.enabled", true);
        get().addDefault("minecraft.discord_embeds.entradas_e_saidas_canal", "ID");
        get().addDefault("minecraft.discord_embeds.join.title", "Bem-vindo %user%");
        get().addDefault("minecraft.discord_embeds.join.description", "» **%nick%** Entrou no servidor");
        get().addDefault("minecraft.discord_embeds.leave.enabled", true);
        get().addDefault("minecraft.discord_embeds.leave.title", "Até mais %user%");
        get().addDefault("minecraft.discord_embeds.leave.description", "» **%nick%** Saiu do Servidor");

        /*
        get().addDefault("discord_embed.title_java", "**IP do Servidor**");
        get().addDefault("discord_embed.description_java", Arrays.asList("Conecte-se com o seguinte ip", "`jogar.ragnarok-brasil.com.br`"));
        get().addDefault("discord_embed.title_bedrock", "");
        get().addDefault("discord_embed.description_bedrock", Arrays.asList(""));
         */
        get().addDefault("messages.vincularsucesso", Arrays.asList(" ", "&aVocê vinculou com sucesso sua conta com a do &9Discord", " "));
        get().addDefault("messages.vinculado", "&bSua conta é vinculada com &5%player%");
        get().addDefault("messages.cooldown", "&cVocê precisa esperar para utilizar o comando novamente");
        get().addDefault("messages.desvincular", "&aSua conta foi desvinculada do net.satooro.stragnarok.discord");
        get().addDefault("messages.vincular", "&cSeu código é &f%codigo%&c. Use &f/vincular <código> no &9Discord");
        get().addDefault("messages.vincularconta", Arrays.asList("Oi", "Tudo bem?"));
        get().addDefault("messages.commands", Arrays.asList("/vincular conta", "/vincular serializer", "/vincular reload"));

        get().addDefault("rewards.items", "");
        get().addDefault("rewards.commands", Arrays.asList("say command", "say testcommand"));
        get().options().copyDefaults(true);
        save();
//        downloadLibs();
    }

    public static void sendMessageStringList(String path, Player p){
        Config.get().getStringList(path).forEach(s -> p.sendMessage(s.replace("&", "§")));
    }
    public static void sendMessageString(String path, Player p){
        p.sendMessage(Objects.requireNonNull(Config.get().getString(path)).replace("&", "§"));
    }

    public static boolean getBoolean(String path){
        return Config.get().getBoolean(path);
    }
    public static String getString(String path){
        return Objects.requireNonNull(Config.get().getString(path)).replace("&", "§");
    }

    public static void reload(){
        configuration = YamlConfiguration.loadConfiguration(file);
    }

    /*
    private static void downloadLibs(){

        if(Files.notExists(Paths.get(IM.PATH))) {
            Bukkit.getConsoleSender().sendMessage(" ");
            Bukkit.getConsoleSender().sendMessage(" ");
            Bukkit.getConsoleSender().sendMessage("§e§lBaixando bibliotecas...");
            Bukkit.getConsoleSender().sendMessage(" ");
            Bukkit.getConsoleSender().sendMessage(" ");
            if(isWindows()){
                Bukkit.getConsoleSender().sendMessage("§bSistema WINDOWS");
            }else {
                downloadFile("https://imagemagick.org/archive/binaries/magick", IM.PATH);
            }

        }
    }

    public static void downloadFile(String urlStr, String file){
        try {
            Files.createDirectories(Paths.get(SourceVincular.getMain().getDataFolder() + "/lib"));
            Files.createDirectories(Paths.get(SourceVincular.getMain().getDataFolder() + "/data"));
            URL url = new URL(urlStr);
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            FileOutputStream fis = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int count = 0;
            while ((count = bis.read(buffer, 0, 1024)) != -1) {
                fis.write(buffer, 0, count);
            }
            fis.close();
            bis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isWindows(){
        return System.getProperty("os.name").split(" ")[0].equalsIgnoreCase("Windows");
    }

     */

}

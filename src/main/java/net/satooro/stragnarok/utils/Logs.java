package net.satooro.stragnarok.utils;

import net.satooro.stragnarok.STRagnarok;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Logs {

    private static File logfile;

    public Logs(){
        try {
            logfile = new File(STRagnarok.getMain().getDataFolder() + System.getProperty("file.separator") + "logs.txt");
            if (logfile.createNewFile()) {
                Utils.sendMessageConsole("§aO arquivo de logs foi criado com sucesso.");
            }
        } catch (IOException e) {
            Utils.sendMessageConsole("§cOcorreu um erro na criação do arquivo de logs.");
        }
    }

    public static void write(String log){
        try {
            FileWriter write = new FileWriter(logfile, StandardCharsets.UTF_8, true);
            write.write(log + "\n");
            write.close();
        } catch (IOException e) {
            System.out.println("§cOcorreu um erro ao escrever no arquivo de logs");
        }
    }

    public static void createLogVerifyMinecraft(String nickname, String code){
        write(String.format("[%s] O jogador %s gerou o código %s", Utils.getDataTime(), nickname, code));
    }

    public static void createLogVerifyDiscord(String nickname, String userID, String code){
        write(String.format("[%s] O jogador %s vinculou com sucesso o código %s ao usuário %s", Utils.getDataTime(), nickname, code, userID));
    }
            /*
    public static void createLogVerifyDiscord(String userID, String code){
        write(String.format("[%s] O usuário %s gerou o código %s", Utils.getDataTime(), userID, code));
    }

    public static void createLogVerifyMinecraft(String nickname, String code, String userID){
        write(String.format("[%s] O jogador %s vinculou com sucesso o código %s no usuário %s", Utils.getDataTime(), nickname, code, userID));
    }

             */

    public static void createLogRewardsMinecraft(String nickname){
        write(String.format("[%s] O jogador %s recebeu as recompensas do vincular", Utils.getDataTime(), nickname));
    }
}

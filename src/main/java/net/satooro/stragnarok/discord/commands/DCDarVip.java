package net.satooro.stragnarok.discord.commands;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.satooro.stragnarok.database.Queries;
import net.satooro.stragnarok.discord.Embeds;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class DCDarVip extends ListenerAdapter {



    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        String author = event.getUser().getAsMention();
        String authorid = event.getUser().getId();

        if(command.equals("darvip")){
            OptionMapping mapping1 = event.getOption("usuario");
            OptionMapping mapping2 = event.getOption("vip");
            String tipovip = mapping2.getAsString();
            OptionMapping tempo = event.getOption("dias");

            User userslash = event.getOption("usuario").getAsUser();
            String userslash1 = userslash.getAsMention();
            String useravatar = userslash.getAvatarUrl();

            String slashid = userslash.getId();
            Boolean userinMySQL = Queries.isUserinDB(slashid);
            if(!userinMySQL){
                event.reply("O usuário " + userslash1 + " não foi encontrado na database").queue();
                return;
            }

            String query = Queries.getNickAndStatus(slashid);

            String[] splitInfo = splitString(query);

            if(splitInfo.length >= 2){
                String player_nick = splitInfo[0];
                String status = splitInfo[1];
                if (Objects.equals(status, "1")) {
                    event.deferReply(false).setEmbeds(Embeds.entregaVipEmbed(author, player_nick, tipovip, userslash1, useravatar, tempo.getAsString()).build()).queue();
                    getServer().dispatchCommand(getServer().getConsoleSender(), "givekey %nick% %vip% %duracao%d"
                            .replace("%nick%", player_nick).replace("%vip%", tipovip).replace("%duracao%", tempo.getAsString()));
                } else {
                    event.deferReply(true).setEmbeds(Embeds.playerNaoVinculadoEmbed(author).build()).queue();
                }
            }
        }
    }

    /*
    public static VipHolder pegarVIP(){
        try{
            RegisteredServiceProvider<VipHolder> rsp = Bukkit.getServer().getServicesManager().getRegistration(VipHolder.class);
            return rsp == null ? null : (VipHolder) rsp.getProvider();
        } catch (Throwable var1){
            return null;
        }
    }
     */

    private static String[] splitString(String combinedString){
        if(combinedString != null){
            return combinedString.split("\\|");
        } else {
            return new String[]{"", ""};
        }
    }

}

package net.satooro.stragnarok.discord.commands;

import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.satooro.stragnarok.database.Queries;
import net.satooro.stragnarok.discord.Embeds;
import net.satooro.stragnarok.discord.events.UserInfo;
import net.satooro.stragnarok.minecraft.commands.ComandoVincular;
import net.satooro.stragnarok.utils.Config;
import net.satooro.stragnarok.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class DCVincular extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        String user = event.getUser().getAsMention();
        String userid = event.getUser().getId();

        if(command.equals("vincular")){
            OptionMapping codigoOption = event.getOption("codigo");
            String codigo = codigoOption.getAsString();

            if(Queries.hasCode(codigo)) {
                String thumbnail = event.getUser().getAvatarUrl();

                UserInfo userInfo = Queries.getNickAndUUIDFromCode(codigo);

                String nickname = userInfo.getNickname();
                String player_uuid = userInfo.getPlayerUuid();

                Utils.sendMessageConsole("SQL Nick> " + nickname + " uuid> " + player_uuid);

                /*
                String nickname = Queries.getNameFromCode(codigo);
                String player_uuid = Queries.getUUIDFromCode(codigo);
                 */

                ComandoVincular.verifyCode.remove(codigo, player_uuid);
                ComandoVincular.jaVerificado.add(player_uuid);


                event.deferReply(true).setEmbeds(Embeds.createVerififyCorret(user, codigo, nickname, thumbnail).build()).queue();

                Queries.setVerificationPlayer(userid, true, nickname);
                /*
                Player target = Bukkit.getPlayerExact(nickname);
                Utils.getRewards(target);
                 */
//                Config.sendMessageStringList("messages.vincularsucesso", p);
            } else {
                event.reply("Código não encontrado").setEphemeral(true).queue();
            }
        }
    }

    public static void addRole(String player){

    }

}

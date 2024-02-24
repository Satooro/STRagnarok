package net.satooro.stragnarok.discord.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectInteraction;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import net.satooro.stragnarok.discord.Embeds;
import org.jetbrains.annotations.NotNull;

public class DCVipInfo extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();
        String author = event.getUser().getAsMention();
        if(command.equals("vipinfo")){
            event.replyEmbeds(Embeds.vipInfoMessage().build()).addActionRow(
                    StringSelectMenu.create("vip-list")
                            .addOption("VipLoki", "viploki", "Preço: R$ 20,00 || Duração: 30 dias")
                            .addOption("VipThor", "vipthor", "Preço: R$ 35,00 || Duração: 30 dias")
                            .addOption("VipOdin", "vipodin", "Preço: R$ 55,00 || Duração: 30 dias")
                            .addOption("VipFenrir", "vipfenrir", "Preço: R$ 80,00 || Duração: 30 dias")
                            .addOption("VipRagnarok", "vipragnarok", "Preço: R$ 350,00 || Duração: 365 dias")
                            .build()
            ).queue();

        }



    }

    /*
    @Override
    public void onStringSelectInteraction(StringSelectInteraction event){
        if(event.getComponentId().equals("vip-list")){
            event.replyEmbeds()
        }
    }
     */
}

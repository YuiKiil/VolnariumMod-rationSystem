package fr.yuuki.vms.managers;

import fr.yuuki.vms.main;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReportManager {

    private main instance = main.getInstance();

    private Player player;
    private Player targetPlayer;
    private String reason;
    private String message;
    private int repID = instance.nextRepID();

    public ReportManager(Player player, Player targetPlayer, String reason, String message){
        this.player = player;
        this.targetPlayer = targetPlayer;
        this.reason = reason;
        this.message = message;

        // TODO Message report
    }

    public void createReport(){
        instance.reports.put(repID, this);
        for(Player players : Bukkit.getOnlinePlayers()){
            if(players.hasPermission("vol.mod.receive")){
                players.sendMessage("§5§lModération §7>> §dNouveau report:");
                players.sendMessage("§dJoueur: §f" + targetPlayer.getName() + "  §dRaison: §f" + reason + "  §dID: §f#" + repID);
                if(this.message != null){
                    players.sendMessage("§dMessage: §f" + this.getMessage());
                }
                TextComponent tp = new TextComponent("§9[Téléportation]  ");
                tp.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/modtp " + targetPlayer.getName()));
                tp.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Téléportation a §a" + targetPlayer.getName()).create()));
                TextComponent punish = new TextComponent("§c[Sanctionner]  ");
                punish.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Not implemented").create()));
                TextComponent del = new TextComponent("§a[Supprimer]");
                del.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Not implemented").create()));
                players.spigot().sendMessage(tp , punish , del);
            }
        }
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public main getInstance() {
        return instance;
    }

    public Player getPlayer() {
        return player;
    }


    public Player getTargetPlayer() {
        return targetPlayer;
    }

    public String getMessage() {
        return message;
    }


    public String getReason() {
        return reason;
    }

    public int getRepID() {
        return repID;
    }
}

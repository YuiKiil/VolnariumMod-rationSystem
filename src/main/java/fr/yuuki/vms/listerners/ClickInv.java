package fr.yuuki.vms.listerners;

import fr.yuuki.vms.managers.ReportManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickInv implements Listener {

    /**
     * TODO Create Class ReportManager(joueurQuiReport, joueurReport, Raison, Priver, PriverName, ID)
     *
     */

    @EventHandler
    public void onClickInv (InventoryClickEvent e){
        if(e.getCurrentItem() == null) return;

        Player player = (Player) e.getWhoClicked();

        switch(e.getCurrentItem().getType()){

            case IRON_SWORD:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§bTriche§8")){
                    e.setCancelled(true);
                    player.closeInventory();
                    String reported = e.getView().getTitle().substring(18);
                    Player target = Bukkit.getPlayer(reported);
                    ReportManager rep = new ReportManager(player, player.getUniqueId(), target, target.getUniqueId(), "Triche", null);
                    rep.createReport();
                    /**
                     * TODO Methode sendMod
                     */
                }
            case RED_DYE:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§bAnti-Afk§8")){
                    e.setCancelled(true);
                    player.closeInventory();
                    String reported = e.getView().getTitle().substring(18);
                    Player target = Bukkit.getPlayer(reported);
                    ReportManager rep = new ReportManager(player, player.getUniqueId(), target, target.getUniqueId(), "AntiAFK", null);
                    rep.createReport();
                    /**
                     * TODO Methode sendMod
                     */
                }
            case BARRIER:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§bBug-Abuse§7")){
                    e.setCancelled(true);
                    player.closeInventory();
                    String reported = e.getView().getTitle().substring(18);
                    Player target = Bukkit.getPlayer(reported);
                    ReportManager rep = new ReportManager(player, player.getUniqueId(), target, target.getUniqueId(), "BugAbuse", null);
                    rep.createReport();
                    /**
                     * TODO Methode sendMod
                     */
                }
            case FEATHER:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§bLanguage§6")){
                    e.setCancelled(true);
                    player.closeInventory();
                    String reported = e.getView().getTitle().substring(18);
                    Player target = Bukkit.getPlayer(reported);
                    ReportManager rep = new ReportManager(player, player.getUniqueId(), target, target.getUniqueId(), "Language", null);
                    rep.createReport();
                    /**
                     * TODO Methode sendMod
                     */
                }
            case BLUE_BANNER:
                if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§bAutre§2")){
                    e.setCancelled(true);
                    player.closeInventory();
                    String reported = e.getView().getTitle().substring(18);
                    Player target = Bukkit.getPlayer(reported);
                    ReportManager rep = new ReportManager(player, player.getUniqueId(), target, target.getUniqueId(), "Autre", null);
                    rep.createReport();
                    /**
                     * TODO Methode sendMod
                     */
                }


            default: break;
        }


    }

    public void sendMod(String name, String reason){
        for(Player players : Bukkit.getOnlinePlayers()){
            if(players.hasPermission("vol.mod.receive")){
                players.sendMessage("§5§lModération §7>> §dNouveau report:");
                players.sendMessage("§dJoueur: " + name + "  §dRaison: " + reason);
                players.sendMessage("§9[Téléportation]  §c[Sanctionner]  §a[Supprimer]");
                /**
                 * TODO message json with tp, sanctionne, refuse (pour delete le report)
                 */
            }
        }
    }
}

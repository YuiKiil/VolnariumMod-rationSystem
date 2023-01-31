package fr.yuuki.vms.listerners;

import fr.yuuki.vms.Utils.ItemBuilder;
import fr.yuuki.vms.commands.Methode;
import fr.yuuki.vms.managers.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class OnRightClick implements Listener {

    @EventHandler
    public void onRightClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        ItemStack it = e.getItem();
        if(it == null) return;

        PlayerManager pm = PlayerManager.getFromPlayer(player);

        if(!PlayerManager.isInMod(player)) return;

        e.setCancelled(true);

        if (it.getType() == BLAZE_POWDER) {
            new Methode().ModManage(player);
            return;
        } else if (it.getType() == GRAY_DYE && it.getItemMeta().getDisplayName().equals("§8Vanish")) {
            pm.setVanished(true);
            return;
        } else if (it.getType() == GREEN_DYE && it.getItemMeta().getDisplayName().equals("§8Vanish") ) {
            pm.setVanished(false);
            return;
        } else if (it.getType() == BARRIER && it.getItemMeta().getDisplayName().equals("§cExit")){
            player.performCommand("mod");
        }
    }


}
